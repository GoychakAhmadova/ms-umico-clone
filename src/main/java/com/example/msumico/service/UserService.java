package com.example.msumico.service;

import com.example.msumico.dao.entity.UserEntity;
import com.example.msumico.dao.repository.UserRepository;
import com.example.msumico.exceptions.NotFoundException;
import com.example.msumico.mapper.UserMapper;
import com.example.msumico.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void createNewUser(UserDto userDto) {
        log.info("Action.createNewUser.started {}", userDto);
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        userRepository.save(userEntity);
        log.info("Action.createNewUser.ended {}", userDto);
    }

    public UserDto getUserDtoById(Long id) {
        log.info("Action.getUserDtoById.started {}", id);
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User with this id is not found"));
        log.info("Action.getUserDtoById.ended {}", id);
        return userMapper.toUserDto(userEntity);
    }

    public List<UserDto> getAllUsers() {
        log.info("Action.getAllUsers.started");
        List<UserEntity> userEntities = userRepository.findAll();
        log.info("Action.getAllUsers.ended");
        return userEntities
                .stream()
                .map(userMapper::toUserDto)
                .toList();

    }

    public List<UserDto> getUsersByBirthday() {
        log.info("Action.getUsersByBirthday.started");
        var userDtos = getAllUsers();
        userDtos = userDtos.stream().filter(item ->
                item.getBirthday().getMonthValue() == LocalDate.now().getMonthValue()
                        && item.getBirthday().getDayOfMonth() == LocalDate.now().getDayOfMonth()
        ).toList();
        log.info("Action.getUsersByBirthday.ended");
        return userDtos;
    }


    public void deleteUser(Long id) {
        log.info("Action.deleteUser.started {}", id);
        UserEntity userEntity = userMapper.toUserEntity(getUserDtoById(id));
        userRepository.delete(userEntity);
        log.info("Action.deleteUser.ended {}", id);
    }

    public void updateUserInfo(Long id, UserDto userDto) {
        log.info("Action.updateUserInfo.started {}", userDto);
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        assert userEntity != null;
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setGender(userDto.getGender());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());
        userEntity.setFin(userDto.getFin());
        userRepository.save(userEntity);
        log.info("Action.updateUserInfo.ended {}", userDto);
    }
}

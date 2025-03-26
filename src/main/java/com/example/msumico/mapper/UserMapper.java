package com.example.msumico.mapper;

import com.example.msumico.dao.entity.UserEntity;
import com.example.msumico.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "cards", ignore = true)
    UserDto toUserDto(UserEntity userEntity);

    @Mapping(target = "cards", ignore = true)
    UserEntity toUserEntity(UserDto userDto);


}

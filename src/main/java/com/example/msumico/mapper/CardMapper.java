package com.example.msumico.mapper;

import com.example.msumico.dao.entity.CardEntity;
import com.example.msumico.dao.entity.UserEntity;
import com.example.msumico.model.CardDto;
import com.example.msumico.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {
    @Mapping(target = "userId", ignore = true)
    CardDto toCardDto(CardEntity cardEntity);

    CardEntity toCardEntity(CardDto cardDto);
}

package com.example.msumico.mapper;

import com.example.msumico.dao.entity.ShopEntity;
import com.example.msumico.model.ShopDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    ShopEntity toShopEntity(ShopDto shopDto);
    ShopDto toShopDto(ShopEntity shopEntity);
}

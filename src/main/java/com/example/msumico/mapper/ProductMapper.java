package com.example.msumico.mapper;

import com.example.msumico.dao.entity.ProductEntity;
import com.example.msumico.model.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper{
    ProductEntity toProductEntity(ProductDto productDto);
    ProductDto toProductDto(ProductEntity productEntity);
}

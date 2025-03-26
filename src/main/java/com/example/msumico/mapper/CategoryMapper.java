package com.example.msumico.mapper;

import com.example.msumico.dao.entity.CategoryEntity;
import com.example.msumico.model.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toCategoryDto(CategoryEntity category);
    CategoryEntity toCategoryEntity(CategoryDto categoryDto);
}

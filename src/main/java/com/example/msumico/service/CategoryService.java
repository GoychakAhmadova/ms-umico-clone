package com.example.msumico.service;

import com.example.msumico.dao.entity.CategoryEntity;
import com.example.msumico.dao.repository.CategoryRepository;
import com.example.msumico.exceptions.NotFoundException;
import com.example.msumico.mapper.CategoryMapper;
import com.example.msumico.model.CategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public void createNewCategory(CategoryDto categoryDto) {
        log.info("Create new category started");
        CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(categoryDto);
        categoryRepository.save(categoryEntity);
        log.info("Create new category finished");
    }

    public List<CategoryDto> getAllCategories() {
        log.info("Get all categories started");
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        log.info("Get all categories finished");
        return categoryEntities
                .stream()
                .map(categoryMapper::toCategoryDto)
                .toList();
    }

    public CategoryDto getCategoryById(Long id) {
        log.info("Get category by id started");
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() ->
                new NotFoundException("Category not found with id: " + id));
        log.info("Get category by id finished");
        return categoryMapper.toCategoryDto(categoryEntity);
    }

    public void deleteCategoryById(Long id) {
        log.info("Delete category by id started");
        categoryRepository.deleteById(id);
        log.info("Delete category by id finished");
    }

    public void updateCategory(Long id, CategoryDto categoryDto) {
        log.info("Update category started");
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Category not found with id: " + id));
        categoryEntity.setCategoryName(categoryDto.getCategoryName());
        categoryRepository.save(categoryEntity);
        log.info("Update category finished");
    }
}

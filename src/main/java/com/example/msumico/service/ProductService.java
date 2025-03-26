package com.example.msumico.service;

import com.example.msumico.dao.entity.CategoryEntity;
import com.example.msumico.dao.entity.ProductEntity;
import com.example.msumico.dao.entity.ShopEntity;
import com.example.msumico.dao.repository.CategoryRepository;
import com.example.msumico.dao.repository.ProductRepository;
import com.example.msumico.dao.repository.ShopRepository;
import com.example.msumico.exceptions.NotFoundException;
import com.example.msumico.mapper.ProductMapper;
import com.example.msumico.model.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.parsers.ReturnTypeParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ShopRepository shopRepository;
    private final CategoryRepository categoryRepository;
    private final ReturnTypeParser returnTypeParser;

    public void createNewProduct(ProductDto productDto) {
        log.info("Adding new product started");
        ProductEntity productEntity = productMapper.toProductEntity(productDto);
        productRepository.save(productEntity);
        log.info("Adding new product completed");
    }

    public ProductDto getProductDtoById(Long id) {
        log.info("Action.getProductDtoById.started {}", id);
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Product with this id is not found"));
        log.info("Action.getProductDtoById.ended {}", id);
        return productMapper.toProductDto(productEntity);
    }

    public Page<ProductDto> getAllProducts(final Pageable pageable) {
        log.info("Action.getAllProducts.started");
        Page<ProductEntity> productEntityList = productRepository.findAll(pageable);
        List<ProductDto> dtos =  productEntityList
                .stream()
                .map(productMapper::toProductDto)
                .toList();

        return new PageImpl<>(dtos, pageable, productEntityList.getTotalElements());
    }

    public void deleteProduct(Long id) {
        log.info("Action.deleteProduct.started {}", id);
        ProductEntity productEntity = productMapper.toProductEntity(getProductDtoById(id));
        productRepository.delete(productEntity);
        log.info("Action.deleteProduct.ended {}", id);
    }

    public void updateProductInfo(Long id, ProductDto productDto) {
        log.info("Action.updateProductInfo.started {}", productDto);
        ProductEntity productEntity = productRepository.findById(id).orElse(null);

        ShopEntity shop = shopRepository.findById(
                productDto.getShopId()).orElseThrow(() ->
                new NotFoundException("Shop with this is not found: " + productDto.getShopId()));
        CategoryEntity category = categoryRepository.findById(
                productDto.getCategoryId()).orElseThrow(() ->
                new NotFoundException("Category with this is not found: " + productDto.getCategoryId()));

        assert productEntity != null;
        productEntity.setStockQuantity(productDto.getStockQuantity());
        productEntity.setProductPrice(productDto.getProductPrice());
        productEntity.setProductName(productDto.getProductName());
        productEntity.setShop(shop);
        productEntity.setCategory(category);
        productRepository.save(productEntity);
        log.info("Action.updateUserInfo.ended {}", productDto);
    }

    public List<ProductDto> getProductsOfShop(Long shopId) {
        log.info("Action.getProductsOfShop.started {}", shopId);
        List<ProductEntity> productsOfShop = productRepository.findAll()
                .stream().filter(product ->
                        product.getShop().getId().equals(shopId)).toList();

        return productsOfShop.stream()
                .map(productMapper::toProductDto)
                .toList();

    }

    public List<ProductDto> getProductsOfCategory(Long categoryId) {
        log.info("Action.getProductsOfCategory.started {}", categoryId);
        List<ProductEntity> productsOfCategory = productRepository.findAll()
                .stream().filter(product ->
                        product.getCategory().getId().equals(categoryId)).toList();

        return productsOfCategory.stream().map(
                productMapper::toProductDto
        ).toList();
    }

    public Long manageStockOfProduct(Long productId) {
        log.info("Action.manageStockOfProduct.started {}", productId);
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product with this id is not found: " + productId));

        return productEntity.getStockQuantity();
    }
}

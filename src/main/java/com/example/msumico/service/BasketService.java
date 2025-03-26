package com.example.msumico.service;

import com.example.msumico.dao.entity.BasketEntity;
import com.example.msumico.dao.entity.BasketItemEntity;
import com.example.msumico.dao.entity.ProductEntity;
import com.example.msumico.dao.repository.BasketItemRepository;
import com.example.msumico.dao.repository.BasketRepository;
import com.example.msumico.dao.repository.ProductRepository;
import com.example.msumico.exceptions.NotFoundException;
import com.example.msumico.mapper.BasketItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketService {

    private final ProductRepository productRepository;
    private final BasketItemMapper basketItemMapper;
    private final BasketItemRepository basketItemRepository;
    private final BasketRepository basketRepository;


    public void addProductToBasket(Long productId, Long basketId, Long quantity) {
        log.info("Adding product to basket started");
        ProductEntity product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product not found"));
        BasketEntity basket = basketRepository.findById(basketId).orElseThrow(
                () -> new NotFoundException("Basket not found"));
        BasketItemEntity basketItem = basketItemMapper.toBasketItemEntity(product, basket, quantity);

        basketItemRepository.save(basketItem);
        log.info("Added product to basket ended");

    }
}

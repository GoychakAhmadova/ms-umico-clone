package com.example.msumico.mapper;

import com.example.msumico.dao.entity.BasketEntity;
import com.example.msumico.dao.entity.BasketItemEntity;
import com.example.msumico.dao.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasketItemMapper {
    default BasketItemEntity toBasketItemEntity(ProductEntity product, BasketEntity basket, Long quantity){
        BasketItemEntity basketItem = new BasketItemEntity();
        basketItem.setProduct(product);
        basketItem.setQuantity(quantity);
        basketItem.setPrice(product.getProductPrice() * quantity);
        basketItem.setBasket(basket);
        return basketItem;
    }

}

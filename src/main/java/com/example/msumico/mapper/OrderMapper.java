package com.example.msumico.mapper;

import com.example.msumico.dao.entity.OrderEntity;
import com.example.msumico.model.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toOrderDto(OrderEntity orders);
    OrderEntity toOrderEntity(OrderDto orderDto);
}

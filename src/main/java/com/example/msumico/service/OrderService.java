package com.example.msumico.service;

import com.example.msumico.dao.entity.CardEntity;
import com.example.msumico.dao.entity.OrderEntity;
import com.example.msumico.dao.entity.ProductEntity;
import com.example.msumico.dao.entity.UserEntity;
import com.example.msumico.dao.enums.OrderStatus;
import com.example.msumico.dao.repository.OrderRepository;
import com.example.msumico.dao.repository.ProductRepository;
import com.example.msumico.dao.repository.UserRepository;
import com.example.msumico.exceptions.InsufficientBalanceException;
import com.example.msumico.exceptions.NotRelevantArgumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void createOrder(List<Long> productIDs, Long userID) {
        log.info("Creating new order is started");
        List<ProductEntity> orderedProducts = productRepository.findAllById(productIDs);
        if (orderedProducts.isEmpty()) {
            throw new NotRelevantArgumentException("No valid products found for the given IDs: " + productIDs);
        }

        Double totalPrice = orderedProducts.stream().mapToDouble(
                productEntity -> productEntity.getProductPrice()
        ).sum();
        OrderEntity orderEntity = new OrderEntity();
       // orderEntity.setProducts(orderedProducts);
        orderEntity.setOrderStatus(OrderStatus.PROCESSING);
        orderEntity.setOrderDate(LocalDateTime.now());
        orderEntity.setUser(userRepository.findById(userID).get());
        orderEntity.setTotalPriceOfOrder(totalPrice);
        orderRepository.save(orderEntity);
    }

    public void payOrderFromBalance(Long orderID, Long userID) {
        OrderEntity orderEntity = orderRepository.findById(orderID).orElseThrow();
        UserEntity userEntity = userRepository.findById(userID).orElseThrow();
        Double totalPrice = orderEntity.getTotalPriceOfOrder();
        if (totalPrice <= userEntity.getBalance()) {
            userEntity.setBalance(userEntity.getBalance() - totalPrice);
        } else {
            throw new InsufficientBalanceException("Insufficient balance for the order");
        }
    }

    public void payOrderFromCard(Long orderID, Long userID, Long cardID) {
        OrderEntity orderEntity = orderRepository.findById(orderID).orElseThrow();
        UserEntity userEntity = userRepository.findById(userID).orElseThrow();
        Double totalPrice = orderEntity.getTotalPriceOfOrder();
        CardEntity relevantCard = (CardEntity) userEntity.getCards().stream().filter(
                card -> card.getId().equals(cardID)
        );
        if (totalPrice <= relevantCard.getBalance()) {
            relevantCard.setBalance(relevantCard.getBalance() - totalPrice);

        } else {
            throw new InsufficientBalanceException("Insufficient card balance for the order");
        }
    }
}

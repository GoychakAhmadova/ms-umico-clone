package com.example.msumico.service;

import com.example.msumico.dao.entity.ShopEntity;
import com.example.msumico.dao.repository.ShopRepository;
import com.example.msumico.exceptions.NotFoundException;
import com.example.msumico.mapper.ShopMapper;
import com.example.msumico.model.ShopDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ShopService {
    private final ShopMapper shopMapper;
    private final ShopRepository shopRepository;

    public void createNewShop(ShopDto shopDto) {
        log.info("Create new shop started");
        ShopEntity shopEntity = shopMapper.toShopEntity(shopDto);
        shopRepository.save(shopEntity);
        log.info("Create new shop finished");
    }

    public List<ShopDto> getAllShops() {
        log.info("Get all shops started");
        List<ShopEntity> shopEntities = shopRepository.findAll();
        log.info("Get all shops finished");
        return shopEntities
                .stream()
                .map(shopMapper::toShopDto)
                .toList();
    }

    public ShopDto getShopById(Long id) {
        log.info("Get shop by id started");
        ShopEntity shopEntity = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found with id: " + id));
        log.info("Get shop by id finished");
        return shopMapper.toShopDto(shopEntity);
    }

    public void deleteShopById(Long id) {
        log.info("Delete shop by id started");
        shopRepository.deleteById(id);
        log.info("Delete shop by id finished");
    }

    public void updateShop(Long id, ShopDto shopDto) {
        log.info("Update shop started");
        ShopEntity shopEntity = shopRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Shop not found with id: " + id));
        shopEntity.setShopName(shopDto.getShopName());
        shopRepository.save(shopEntity);
        log.info("Update shop finished");
    }
}

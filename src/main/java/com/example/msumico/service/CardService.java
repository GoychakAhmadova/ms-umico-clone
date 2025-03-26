package com.example.msumico.service;

import com.example.msumico.dao.entity.CardEntity;
import com.example.msumico.dao.entity.UserEntity;
import com.example.msumico.dao.repository.CardRepository;
import com.example.msumico.dao.repository.UserRepository;
import com.example.msumico.exceptions.NotFoundException;
import com.example.msumico.mapper.CardMapper;
import com.example.msumico.model.CardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserRepository userRepository;

    public void createNewCard(CardDto cardDto) {
        log.info("Action.createNewCard.started {}", cardDto);
        CardEntity cardEntity = cardMapper.toCardEntity(cardDto);
        cardEntity.setUser(userRepository.findById(cardDto.getUserId()).orElse(null));
        cardRepository.save(cardEntity);
        log.info("Action.createNewCard.ended {}", cardDto);
    }

    public CardDto getCardDtoById(Long id) {
        log.info("Action.getCardDtoById.started {}", id);
        CardEntity cardEntity = cardRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Card with this id is not found"));
        log.info("Action.getCardDtoById.ended {}", id);
        return cardMapper.toCardDto(cardEntity);
    }

    public List<CardDto> getAllCardsOfUser(Long userId) {
        log.info("Action.getAllCardsOfUser.started {}", userId);
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        assert userEntity != null;
        List<CardEntity> cardEntities = userEntity.getCards();
        log.info("Action.getAllCardsOfUser.ended {}", userId);
        return cardEntities
                .stream()
                .map(cardMapper::toCardDto).toList();

    }

    public void deleteCard(Long id) {
        log.info("Action.deleteCard.started {}", id);
        CardEntity cardEntity = cardRepository.findById(id).orElse(null);
        assert cardEntity != null;
        cardRepository.delete(cardEntity);
        log.info("Action.deleteCard.ended {}", id);
    }

    public void updateCardInfo(Long id, CardDto cardDto) {
        log.info("Action.updateCardInfo.started {}", cardDto);
        CardEntity cardEntity = cardRepository.findById(id).orElse(null);
        assert cardEntity != null;
        cardEntity.setPan(cardDto.getPan());
        cardEntity.setCvv(cardDto.getCvv());
        cardEntity.setExpiryDate(cardDto.getExpiryDate());
        cardEntity.setBalance(cardDto.getBalance());
        cardRepository.save(cardEntity);
        log.info("Action.updateCardInfo.ended {}", cardDto);
    }

    public void addExistingCardToUser(Long userId, Long cardId) {
        log.info("Action.addExistingCardToUser.started {}, {}", userId, cardId);
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        CardEntity cardEntity = cardRepository.findById(cardId).orElseThrow();
        userEntity.getCards().add(cardEntity);
        userRepository.save(userEntity);
        log.info("Action.addExistingCardToUser.ended {}, {}", userId, cardId);
    }


}

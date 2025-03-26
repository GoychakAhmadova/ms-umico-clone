package com.example.msumico.controller;

import com.example.msumico.model.CardDto;
import com.example.msumico.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewUser(@Valid @RequestBody CardDto cardDto) {
        cardService.createNewCard(cardDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CardDto> getAllCardsOfUser(@RequestHeader Long userId) {
        return cardService.getAllCardsOfUser(userId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CardDto getCardById(@PathVariable Long id) {
        return cardService.getCardDtoById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCard(@PathVariable Long id, @Valid @RequestBody CardDto cardDto) {
        cardService.updateCardInfo(id, cardDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }

    @PutMapping("/{cardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addExistingCardToUser(@RequestHeader Long userId, @PathVariable Long cardId) {
        cardService.addExistingCardToUser(userId, cardId);
    }

}

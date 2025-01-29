package com.bank.service;

import com.bank.domain.CardStatus;
import com.bank.domain.entity.CreditCard;
import com.bank.domain.mapper.CreditCardMapper;
import com.bank.domain.model.CreditCardCreationDto;
import com.bank.domain.model.CreditCardDto;
import com.bank.domain.model.CreditCardRemissionDto;
import com.bank.domain.model.CreditCardUpdateDto;
import com.bank.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardDto create(CreditCardCreationDto creditCardCreationDto) {
        log.info("Creating credit card with data {}", creditCardCreationDto);
        CreditCard creditCardToCreate = CreditCardMapper.toCreditCard(creditCardCreationDto);
        creditCardToCreate.setStatus(CardStatus.BLOCKED);
        CreditCard creditCard = this.creditCardRepository.save(creditCardToCreate);
        log.info("Credit Card saved with success id {}", creditCard.getId());
        return CreditCardMapper.toCreditCard(creditCard);
    }

    public void remission(CreditCardRemissionDto creditCardRemissionDto) {
        log.info("Remission credit card with data {}", creditCardRemissionDto);
        this.creditCardRepository.updateStatus(creditCardRemissionDto.getId(),
                creditCardRemissionDto.getReason(), CardStatus.BLOCKED);
        log.info("Credit Card remissioned with success id {}", creditCardRemissionDto.getId());
    }

    public void updateStatus(Long id, String reason) {
        log.info("Enabling credit card with id {}", id);
        this.creditCardRepository.updateStatus(id,reason, CardStatus.ENABLED);
        log.info("Credit Card enabled with success id {}", id);
    }


    public void updateValidationInfo(CreditCardUpdateDto creditCardUpdateDto) {
        log.info("Update credit card with data {}", creditCardUpdateDto);
        this.creditCardRepository.updateCard(creditCardUpdateDto.getCardId(),
                creditCardUpdateDto.getNextCvv(), creditCardUpdateDto.getExpirationDate());
        log.info("Credit Card updated with success id {}", creditCardUpdateDto.getCardId());
    }

}

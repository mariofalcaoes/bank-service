package com.bank.service;

import com.bank.domain.entity.CreditCardDelivery;
import com.bank.domain.mapper.CreditCardDeliveryMapper;
import com.bank.domain.model.CreditCardDeliveryDto;
import com.bank.repository.CreditCardDeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardDeliveryService {

    private final CreditCardDeliveryRepository creditCardDeliveryRepository;
    private final CreditCardService creditCardService;

    public CreditCardDeliveryDto create(CreditCardDeliveryDto creditCardCreationDto) {
        log.info("Creating credit card delivery with data {}", creditCardCreationDto);
        CreditCardDelivery creditCard = this.creditCardDeliveryRepository.save(CreditCardDeliveryMapper.toCreditCardDelivery(creditCardCreationDto));
        log.info("Credit Card delivery saved with success id {}", creditCard.getId());
        creditCardService.updateStatus(creditCardCreationDto.getTrackingId(), creditCardCreationDto.getReturnReason());
        return CreditCardDeliveryMapper.toCreditCardDelivery(creditCard);
    }



}

package com.uxied.arch.registry.service;

import com.uxied.arch.registry.domain.CreditCard;
import com.uxied.arch.registry.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository cardRepository;

    public List<CreditCard> findByOwnerId(String id) {
        return cardRepository.findCreditCardByOwnerId(id);
    }

}

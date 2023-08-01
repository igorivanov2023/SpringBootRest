package com.rest.service;

import com.rest.model.Bank;
import com.rest.model.Card;
import com.rest.model.Client;
import com.rest.repository.ProcessingRepository;
import org.springframework.stereotype.Service;

@Service
public class ProcessingService {

    private final ProcessingRepository processingRepository;

    public ProcessingService(ProcessingRepository processingRepository) {
        this.processingRepository = processingRepository;
    }

    public Client getClientInfo(Integer id) {
        return processingRepository.getClientInfo(id);
    }

    public Bank getBankInfo(Integer id) {
        return processingRepository.getBankInfo(id);
    }

    public Card getCardInfo(String number) {
        return processingRepository.getCardInfo(number);
    }

    public boolean transfer(String numberFrom, String numberTo, Double count) {
        return processingRepository.transfer(numberFrom, numberTo, count);
    }
}

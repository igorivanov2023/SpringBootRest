package com.rest.controller;

import com.rest.model.Bank;
import com.rest.model.Card;
import com.rest.model.Client;
import com.rest.service.ProcessingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processing")
public class ProcessingController {
    private final ProcessingService processingService;

    public ProcessingController(ProcessingService processingService) {
        this.processingService = processingService;
    }

    @GetMapping("/getClientInfo/{id}")
    public ResponseEntity<Client> getClientInfo(@PathVariable Integer id) {
        Client client = processingService.getClientInfo(id);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getBankInfo/{id}")
    public ResponseEntity<Bank> getBankInfo(@PathVariable Integer id) {
        Bank bank = processingService.getBankInfo(id);
        if (bank != null) {
            return new ResponseEntity<>(bank, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCardInfo/{number}")
    public ResponseEntity<Card> getCardInfo(@PathVariable String number) {
        Card card = processingService.getCardInfo(number);
        if (card != null) {
            return new ResponseEntity<>(card, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/transfer/{numberFrom}/{numberTo}/{count}")
    public ResponseEntity<HttpStatus> transfer(@PathVariable String numberFrom,
                                               @PathVariable String numberTo,
                                               @PathVariable Double count) {
        boolean result = processingService.transfer(numberFrom, numberTo, count);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

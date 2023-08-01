package com.rest.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Card {
    private String number;
    private Integer idOwner;
    private Client owner;
    private Integer idBank;
    private Bank bank;
    private LocalDate valid;
    private Double balance;
}

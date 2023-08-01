package com.rest.repository;

import com.rest.model.Bank;
import com.rest.model.Card;
import com.rest.model.Client;
import com.rest.model.rowMapper.BankRowMapper;
import com.rest.model.rowMapper.CardRowMapper;
import com.rest.model.rowMapper.ClientRowMapper;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class ProcessingRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProcessingRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Client getClientInfo(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        String sql = "SELECT * FROM client WHERE id=:id";
        return jdbcTemplate.query(sql, params, new ClientRowMapper()).stream().findFirst().orElse(null);
    }

    public Bank getBankInfo(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        String sql = "SELECT * FROM bank WHERE id=:id";
        return jdbcTemplate.query(sql, params, new BankRowMapper()).stream().findFirst().orElse(null);
    }

    public Card getCardInfo(String number) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("number", number);
        String sql = "SELECT * FROM card WHERE number=:number";
        Card card = jdbcTemplate.query(sql, params, new CardRowMapper()).stream().findFirst().orElse(null);

        if (card == null) {
            return null;
        } else {
            card.setOwner(getClientInfo(card.getIdOwner()));
            card.setBank(getBankInfo(card.getIdBank()));

            return card;
        }
    }

    @Transactional
    public boolean transfer(String numberFrom, String numberTo, Double count) {
        if (count == 0) {
            return false;
        }

        Card cardFrom = getCardInfo(numberFrom);
        if (cardFrom == null || cardFrom.getBalance() < count) {
            return false;
        }

        Card cardTo = getCardInfo(numberTo);
        if (cardTo == null) {
            return false;
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("numberFrom", numberFrom);
        params.addValue("newBalanceFrom", cardFrom.getBalance() - count);
        String sqlUpdateFrom = "UPDATE card SET balance = :newBalanceFrom WHERE number=:numberFrom";
        jdbcTemplate.update(sqlUpdateFrom, params);

        params.addValue("numberTo", numberTo);
        params.addValue("newBalanceTo", cardTo.getBalance() + count);
        String sqlUpdateTo = "UPDATE card SET balance = :newBalanceTo WHERE number=:numberTo";
        jdbcTemplate.update(sqlUpdateTo, params);

        return true;
    }
}

package com.rest.model.rowMapper;

import com.rest.model.Card;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardRowMapper implements RowMapper<Card> {
    @Override
    public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
        Card card = new Card();

        card.setNumber(rs.getString("number"));
        card.setValid(rs.getDate("valid").toLocalDate());
        card.setBalance(rs.getDouble("balance"));
        card.setIdOwner(rs.getInt("owner"));
        card.setIdBank(rs.getInt("bank"));

        return card;
    }
}

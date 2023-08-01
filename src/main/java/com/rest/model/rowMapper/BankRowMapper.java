package com.rest.model.rowMapper;

import com.rest.model.Bank;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankRowMapper implements RowMapper<Bank> {
    @Override
    public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bank bank = new Bank();

        bank.setId(rs.getInt("id"));
        bank.setDescription(rs.getString("description"));
        bank.setAddress(rs.getString("address"));
        bank.setPhone(rs.getString("phone"));

        return bank;
    }
}

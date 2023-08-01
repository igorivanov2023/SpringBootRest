package com.rest.model.rowMapper;

import com.rest.model.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt("id"));
        client.setFirstName(rs.getString("first_name"));
        client.setLastName(rs.getString("last_name"));

        return  client;
    }
}

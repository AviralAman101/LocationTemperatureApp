package com.etraveli.dao.impl;

import com.etraveli.dao.ProviderDao;
import com.etraveli.model.Provider;
import com.etraveli.model.User;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcProviderDao implements ProviderDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Provider> findAll() {

        return jdbcTemplate.query("select PROVIDER_ID, PROVIDER_NAME, URI from T_PROVIDER",
                new RowMapper<Provider>() {
                    @Override
                    public Provider mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Provider provider = new Provider();
                        provider.setProviderId(rs.getLong("PROVIDER_ID"));
                        provider.setProviderName(rs.getString("PROVIDER_NAME"));
                        provider.setURI(rs.getString("URI"));
                        return provider;
                    }
                });
    }

    @Override
    public Provider findById(Long aLong) {
        throw new UnsupportedOperationException("Find functionality is not present.");
    }

    @Override
    public void create(Provider provider) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Provider provider) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long aLong) {
        throw new UnsupportedOperationException();
    }
}

package com.etraveli.dao.impl;

import com.etraveli.dao.TemperatureDao;
import com.etraveli.model.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class JdbcTemperatureDao implements TemperatureDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void upsert(Temperature temperature) {
        //Since using H2 database here and different databases have different mechanism
        // for upserting, hence followed the universal approach
        try {
            updateTemperature(temperature);
        }catch (Exception ex){
            insertTemperature(temperature);
        }
    }

    private void insertTemperature(Temperature temperature) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt = con.prepareStatement("INSERT INTO T_TEMPERATURE(CITY, STATE, PROVIDER_ID," +
                        "CODE , CREDITS , VISIBILITY_MILES , WIND_MPH, TEMP_C ,TEMP_F ,RELATIVE_HUMIDITY ,WIND_DIR) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, temperature.getCity());
                stmt.setString(2, temperature.getState());
                stmt.setLong(3, temperature.getProviderId());
                stmt.setString(4, temperature.getCode());
                stmt.setString(5, temperature.getCredits());
                stmt.setString(6, temperature.getVisibilityMiles());
                stmt.setString(7, temperature.getWindMph());
                stmt.setLong(8, temperature.getTempC());
                stmt.setLong(9, temperature.getTempF());
                stmt.setString(10, temperature.getRelativeHumidity());
                stmt.setString(11, temperature.getWindDir());
                return stmt;
            }
        });
    }

    private void updateTemperature(Temperature temperature) {
        String updateQuery = "UPDATE T_TEMPERATURE SET CODE = ?, CREDITS = ?, VISIBILITY_MILES=? ," +
                " WIND_MPH=?," +
                "TEMP_C=? ,TEMP_F=? ,RELATIVE_HUMIDITY=? ,WIND_DIR=? WHERE CITY=? AND PROVIDER_ID=? AND STATE=?";
        jdbcTemplate.update(updateQuery,temperature.getCode(),temperature.getCredits(),temperature.getVisibilityMiles()
                ,temperature.getWindMph(),temperature.getTempC(),temperature.getTempF(),temperature.getRelativeHumidity()
                ,temperature.getWindDir(),temperature.getCity(),temperature.getProviderId(),temperature.getState());
    }
}

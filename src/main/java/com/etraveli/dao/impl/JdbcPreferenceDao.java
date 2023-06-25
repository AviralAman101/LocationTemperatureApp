package com.etraveli.dao.impl;

import com.etraveli.dao.PreferenceDao;
import com.etraveli.dao.UserDao;
import com.etraveli.helper.ApplicationCustomException;
import com.etraveli.model.Preference;
import com.etraveli.model.PreferenceID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcPreferenceDao implements PreferenceDao {
    private JdbcTemplate jdbcTemplate;

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Preference> findAll() {
        return jdbcTemplate.query("select CITY,STATE, USER_ID, NOTIFY_CYCLE_MINS,IS_SMS_ACTIVE,IS_MAIL_ACTIVE" +
                        ",IS_APP_NOTIFY_ACTIVE,THRESHOLD from T_PREFERENCE",
                new RowMapper<Preference>() {
                    @Override
                    public Preference mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Preference preference = new Preference();
                        preference.setCity(rs.getString("CITY"));
                        preference.setState(rs.getString("STATE"));
                        preference.setUserId(rs.getLong("USER_ID"));
                        preference.setNotifyCycleMins(rs.getLong("NOTIFY_CYCLE_MINS"));
                        preference.setIsSmsActive(rs.getString("IS_SMS_ACTIVE"));
                        preference.setIsMailActive(rs.getString("IS_MAIL_ACTIVE"));
                        preference.setIsAppNotifyActive(rs.getString("IS_APP_NOTIFY_ACTIVE"));
                        preference.setThresholdInC(rs.getLong("THRESHOLD"));
                        return preference;
                    }
                });
    }

    @Override
    public Preference findById(PreferenceID preferenceID) {
        RowMapper<Preference> rowMapper = new RowMapper<>() {
            @Override
            public Preference mapRow(ResultSet rs, int rowNum) throws SQLException {
                Preference preference = new Preference();
                preference.setCity(preferenceID.getCity());
                preference.setUserId(preferenceID.getUserId());
                preference.setState(preference.getState());
                preference.setNotifyCycleMins(rs.getLong("NOTIFY_CYCLE_MINS"));
                preference.setIsSmsActive(rs.getString("IS_SMS_ACTIVE"));
                preference.setIsMailActive(rs.getString("IS_MAIL_ACTIVE"));
                preference.setIsAppNotifyActive(rs.getString("IS_APP_NOTIFY_ACTIVE"));
                preference.setThresholdInC(rs.getLong("THRESHOLD"));
                return preference;
            }
        };
        Preference preference = jdbcTemplate.queryForObject("select CITY,USER_ID," +
                        " NOTIFY_CYCLE_MINS,IS_SMS_ACTIVE,IS_MAIL_ACTIVE " +
                        ",IS_APP_NOTIFY_ACTIVE, THRESHOLD from T_PREFERENCE where CITY = ? AND USER_ID = ? AND STATE = ?",
                rowMapper, preferenceID.getCity(), preferenceID.getUserId(), preferenceID.getState());
        return preference;
    }

    @Override
    public void create(Preference preference) {
        validatePreference(preference);
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt = con.prepareStatement("INSERT INTO T_PREFERENCE (CITY,USER_ID," +
                        "NOTIFY_CYCLE_MINS,IS_SMS_ACTIVE,IS_MAIL_ACTIVE\n" +
                        ",IS_APP_NOTIFY_ACTIVE, THRESHOLD, STATE) VALUES (?,?,?,?,?,?,?,?);");
                stmt.setString(1, preference.getCity());
                stmt.setLong(2, preference.getUserId());
                stmt.setLong(3, preference.getNotifyCycleMins());
                stmt.setString(4, preference.getIsSmsActive());
                stmt.setString(5, preference.getIsMailActive());
                stmt.setString(6, preference.getIsAppNotifyActive());
                stmt.setLong(7, preference.getThresholdInC());
                stmt.setString(8, preference.getState());
                return stmt;
            }
        });
    }

    @Override
    public void update(Preference preference) {
        validatePreference(preference);
        jdbcTemplate.update("update T_PREFERENCE set " +
                "NOTIFY_CYCLE_MINS = ?, IS_SMS_ACTIVE = ? ,IS_MAIL_ACTIVE = ?, IS_APP_NOTIFY_ACTIVE = ? , " +
                "THRESHOLD = ? where CITY = ? AND STATE = ? AND USER_ID = ?",preference.getNotifyCycleMins(),
                preference.getIsSmsActive(), preference.getIsMailActive(), preference.getIsAppNotifyActive()
                ,preference.getThresholdInC(), preference.getCity(),preference.getState(), preference.getUserId());
    }

    @Override
    public void delete(PreferenceID preference) {
        jdbcTemplate.update("delete from T_PREFERENCE where CITY = ? AND USER_ID = ? AND STATE = ?",
                preference.getCity(), preference.getUserId(), preference.getState());
    }

    private void validatePreference(Preference preference){
        if(Objects.isNull(preference.getUserId()) || Objects.isNull(preference.getCity()))
            throw new ApplicationCustomException("Mandatory values not present in the payload.");
    }

    @Override
    public List<Preference> findAllLocations() {
        RowMapper<Preference> rowMapper = new RowMapper<>() {
            @Override
            public Preference mapRow(ResultSet rs, int rowNum) throws SQLException {
                Preference preference = new Preference();
                preference.setCity(rs.getString("CITY"));
                preference.setState(rs.getString("STATE"));
                return preference;
            }
        };
        return jdbcTemplate.query("select CITY,STATE from T_PREFERENCE GROUP BY CITY, STATE",
                rowMapper);
    }

    @Override
    public void updateLastNotified(String city, String state, Long userId) {
        jdbcTemplate.update("UPDATE T_PREFERENCE SET LAST_NOTIFIED = now() WHERE CITY=? AND STATE=? AND USER_ID=?",
                city,state,userId);
    }
}

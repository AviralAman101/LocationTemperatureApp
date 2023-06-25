package com.etraveli.dao.impl;

import com.etraveli.dao.ExceededTemperatureViewDao;
import com.etraveli.model.ExceededTemperatureUserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcExceededTemperatureViewDaoImpl implements ExceededTemperatureViewDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ExceededTemperatureUserView> listAll() {
        return jdbcTemplate.query("SELECT * FROM EXCEEDED_TEMP_V", new RowMapper<ExceededTemperatureUserView>() {
            @Override
            public ExceededTemperatureUserView mapRow(ResultSet rs, int i) throws SQLException {
                ExceededTemperatureUserView tempView = new ExceededTemperatureUserView();
                tempView.setCity(rs.getString("CITY"));
                tempView.setState(rs.getString("STATE"));
                tempView.setUserId(rs.getLong("USER_ID"));
                tempView.setNotifyInMins(rs.getLong("NOTIFY_CYCLE_MINS"));
                tempView.setIsSmsActive(rs.getString("IS_SMS_ACTIVE"));
                tempView.setIsMailActive(rs.getString("IS_MAIL_ACTIVE"));
                tempView.setIsAppNotifyActive(rs.getString("IS_APP_NOTIFY_ACTIVE"));
                tempView.setThreshold(rs.getLong("THRESHOLD"));
                tempView.setTempC(rs.getLong("TEMP_C"));
                tempView.setTempF(rs.getLong("TEMP_F"));
                return tempView;
            }
        });
    }
}

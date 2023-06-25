package com.etraveli.dao;

import com.etraveli.model.Temperature;

public interface TemperatureDao {
    void upsert(Temperature temperature);
}

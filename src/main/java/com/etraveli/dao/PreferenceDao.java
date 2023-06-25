package com.etraveli.dao;

import com.etraveli.model.Preference;
import com.etraveli.model.PreferenceID;

import java.util.List;

public interface PreferenceDao extends CRUDRepository<Preference, PreferenceID>{
    List<Preference> findAllLocations();

    void updateLastNotified(String city, String state, Integer userId);
}

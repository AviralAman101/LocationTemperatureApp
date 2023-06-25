package com.etraveli.service;

import com.etraveli.model.Preference;
import com.etraveli.model.PreferenceID;
import com.etraveli.model.User;

import java.util.List;

public interface PreferenceService {
    List<Preference> findAll();
    Preference findById(PreferenceID id);
    void create(Preference preference);
    void update(Preference preference);
    void delete(PreferenceID id);
}

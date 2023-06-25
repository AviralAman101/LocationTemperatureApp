package com.etraveli.service;

import com.etraveli.dao.PreferenceDao;
import com.etraveli.dao.UserDao;
import com.etraveli.model.Preference;
import com.etraveli.model.PreferenceID;
import com.etraveli.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PreferenceServiceImpl implements PreferenceService{

    private PreferenceDao preferenceDao;
    @Autowired
    public void setPreferenceDao(PreferenceDao preferenceDao) {
        this.preferenceDao = preferenceDao;
    }
    @Override
    public List<Preference> findAll() {
        return preferenceDao.findAll();
    }

    @Override
    public Preference findById(PreferenceID id) {
        return preferenceDao.findById(id);
    }

    @Override
    public void create(Preference preference) {
        preferenceDao.create(preference);
    }

    @Override
    public void update(Preference preference) {
        preferenceDao.update(preference);
    }

    @Override
    public void delete(PreferenceID id) {
        preferenceDao.delete(id);
    }
}

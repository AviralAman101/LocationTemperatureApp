package com.etraveli.dao;

import java.util.List;

public interface CRUDRepository<T,ID> {
    List<T> findAll();
    T findById(ID id);
    void create(T t);
    void update(T t);
    void delete(ID id);
}

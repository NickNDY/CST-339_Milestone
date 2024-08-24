package com.gcu.data;

import java.util.List;

public interface DataAccess<T> {
    T findById(int id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(T entity);
}

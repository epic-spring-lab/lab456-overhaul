package com.example.lab456.repositories;


public interface CrudDAO<T> {

    Long create(T entity);

    T read(Long id);

    void update(T entity);

    void delete(Long id);

}

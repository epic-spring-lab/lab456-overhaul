package com.example.lab456.repositories;


import java.util.Optional;

public interface CrudDAO<T> {

    Long create(T entity);

    Optional<T> read(Long id);

    void update(T entity);

    void delete(Long id);

}

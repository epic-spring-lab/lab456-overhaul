package com.example.lab456.repositories.jdbc;

import com.example.lab456.entities.CurrencyEntity;
import com.example.lab456.repositories.CurrencyDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JDBCCurrencyDAO implements CurrencyDAO {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long create(CurrencyEntity entity) {
        return jdbcTemplate.queryForObject("INSERT INTO currencies (name, code) VALUES (?, ?) RETURNING id",
                Long.class, entity.getName(), entity.getCode());
    }

    @Override
    public Optional<CurrencyEntity> read(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM currencies WHERE id = ?", CURRENCY_ROW_MAPPER, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }}

    @Override
    public void update(CurrencyEntity entity) {
        jdbcTemplate.update("UPDATE currencies SET name = ?, code = ? WHERE id = ?",
                entity.getName(), entity.getCode(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE  FROM currencies WHERE id = ?", id);
    }




    private static final RowMapper<CurrencyEntity> CURRENCY_ROW_MAPPER = (resultSet, i) -> {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(resultSet.getLong("id"));
        currencyEntity.setName(resultSet.getString("name"));
        currencyEntity.setCode(resultSet.getString("code"));
        return currencyEntity;
    };

    @Override
    public List<CurrencyEntity> filterByName(String name) {
        // return entity if entity name contains name, ignore case
        return jdbcTemplate.query("SELECT * FROM currencies WHERE name ILIKE ?", CURRENCY_ROW_MAPPER,  name + "%");
    }

}

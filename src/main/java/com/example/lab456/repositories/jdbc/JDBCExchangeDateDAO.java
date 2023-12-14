package com.example.lab456.repositories.jdbc;

import com.example.lab456.entities.ExchangeDateEntity;
import com.example.lab456.repositories.ExchangeDateDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JDBCExchangeDateDAO implements ExchangeDateDAO {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long create(ExchangeDateEntity entity) {
        return jdbcTemplate.queryForObject("INSERT INTO exchange_dates (day, month, year) VALUES (?, ?, ?) RETURNING id",
                Long.class, entity.getDay(), entity.getMonth(), entity.getYear());
    }

    @Override
    public ExchangeDateEntity read(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM exchange_dates WHERE id = ?", EXCHANGE_DATE_ROW_MAPPER, id);
    }

    @Override
    public void update(ExchangeDateEntity entity) {
        jdbcTemplate.update("UPDATE exchange_dates SET day = ?, month = ?, year = ? WHERE id = ?",
                entity.getDay(), entity.getMonth(), entity.getYear(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM exchange_dates WHERE id = ?", id);
    }

    @Override
    public List<ExchangeDateEntity> findEvenDatesForYear(int year) {
        return jdbcTemplate.query("SELECT * FROM exchange_dates WHERE year = ? AND day % 2 = 0", EXCHANGE_DATE_ROW_MAPPER, year);
    }

    private static final RowMapper<ExchangeDateEntity> EXCHANGE_DATE_ROW_MAPPER = (resultSet, i) -> {
        ExchangeDateEntity exchangeDateEntity = new ExchangeDateEntity();
        exchangeDateEntity.setId(resultSet.getLong("id"));
        exchangeDateEntity.setDay(resultSet.getInt("day"));
        exchangeDateEntity.setMonth(resultSet.getInt("month"));
        exchangeDateEntity.setYear(resultSet.getInt("year"));
        return exchangeDateEntity;
    };
}

package com.example.lab456.repositories.jdbc;

import com.example.lab456.entities.CurrencyEntity;
import com.example.lab456.entities.ExchangeDateEntity;
import com.example.lab456.entities.ExchangeRateEntity;
import com.example.lab456.repositories.ExchangeRateDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JDBCExchangeRateDAO implements ExchangeRateDAO {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long create(ExchangeRateEntity entity) {
        return jdbcTemplate.queryForObject("INSERT INTO exchange_rates (source_currency_id, target_currency_id, rate, exchange_date_id) VALUES (?, ?, ?, ?) RETURNING id",
                Long.class, entity.getSourceCurrency().getId(), entity.getTargetCurrency().getId(), entity.getRate(), entity.getExchangeDate().getId());
    }

    String query = "SELECT er.id, er.rate, " +
            "sc.id AS source_currency_id, sc.name AS source_currency_name, sc.code AS source_currency_code, " +
            "tc.id AS target_currency_id, tc.name AS target_currency_name, tc.code AS target_currency_code, " +
            "ed.id AS exchange_date_id, ed.day AS exchange_date_day, ed.month AS exchange_date_month, ed.year AS exchange_date_year " +
            "FROM exchange_rates er " +
            "JOIN currencies sc ON er.source_currency_id = sc.id " +
            "JOIN currencies tc ON er.target_currency_id = tc.id " +
            "JOIN exchange_dates ed ON er.exchange_date_id = ed.id";

    @Override
    public Optional<ExchangeRateEntity> read(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query + " WHERE er.id = ?", EXCHANGE_RATE_ROW_MAPPER, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(ExchangeRateEntity entity) {
        jdbcTemplate.update("UPDATE exchange_rates SET source_currency_id = ?, target_currency_id = ?, rate = ?, exchange_date_id = ? WHERE id = ?",
                entity.getSourceCurrency().getId(), entity.getTargetCurrency().getId(), entity.getRate(), entity.getExchangeDate().getId(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM exchange_rates WHERE id = ?", id);
    }

    @Override
    public List<ExchangeRateEntity> filterBySourceWithPagination(int page, int size, String source) {
        return jdbcTemplate.query(query + " WHERE sc.name ILIKE ? LIMIT ? OFFSET ?", EXCHANGE_RATE_ROW_MAPPER, source + "%", size, page * size);
    }

    private static final RowMapper<ExchangeRateEntity> EXCHANGE_RATE_ROW_MAPPER = (resultSet, i) -> {
        ExchangeRateEntity exchangeRateEntity = new ExchangeRateEntity();
        exchangeRateEntity.setId(resultSet.getLong("id"));
        exchangeRateEntity.setRate(resultSet.getDouble("rate"));

        exchangeRateEntity.setSourceCurrency(CurrencyEntity.builder()
                .id(resultSet.getLong("source_currency_id"))
                .name(resultSet.getString("source_currency_name"))
                .code(resultSet.getString("source_currency_code"))
                .build());
        exchangeRateEntity.setTargetCurrency(CurrencyEntity.builder()
                .id(resultSet.getLong("target_currency_id"))
                .name(resultSet.getString("target_currency_name"))
                .code(resultSet.getString("target_currency_code"))
                .build());
        exchangeRateEntity.setExchangeDate(ExchangeDateEntity.builder()
                .id(resultSet.getLong("exchange_date_id"))
                .day(resultSet.getInt("exchange_date_day"))
                .month(resultSet.getInt("exchange_date_month"))
                .year(resultSet.getInt("exchange_date_year"))
                .build());

        return exchangeRateEntity;
    };
}

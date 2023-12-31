package com.example.lab456.repositories.fakes;

import com.example.lab456.dao.ExchangeRateDAO;
import com.example.lab456.repositories.OurExchangeRateRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("fakeExchangeRateRepository")
public class FakeExchangeRateRepository implements OurExchangeRateRepository {

    private static final Set<ExchangeRateDAO> exchangeRates = Set.of(
            ExchangeRateDAO.builder().id(1L).exchangeDateId(1L).sourceCurrencyId(1L).targetCurrencyId(2L).build(),
            ExchangeRateDAO.builder().id(2L).exchangeDateId(1L).sourceCurrencyId(1L).targetCurrencyId(3L).build(),
            ExchangeRateDAO.builder().id(3L).exchangeDateId(1L).sourceCurrencyId(2L).targetCurrencyId(1L).build(),
            ExchangeRateDAO.builder().id(4L).exchangeDateId(1L).sourceCurrencyId(2L).targetCurrencyId(3L).build(),
            ExchangeRateDAO.builder().id(5L).exchangeDateId(1L).sourceCurrencyId(3L).targetCurrencyId(1L).build(),
            ExchangeRateDAO.builder().id(6L).exchangeDateId(1L).sourceCurrencyId(3L).targetCurrencyId(2L).build(),
            ExchangeRateDAO.builder().id(7L).exchangeDateId(2L).sourceCurrencyId(1L).targetCurrencyId(2L).build(),
            ExchangeRateDAO.builder().id(8L).exchangeDateId(2L).sourceCurrencyId(1L).targetCurrencyId(3L).build()
    );

    @Override
    public Long create(ExchangeRateDAO entity) {
        long newId = exchangeRates.size() + 1L;
        ExchangeRateDAO newEntity = ExchangeRateDAO.builder()
                .id(newId)
                .exchangeDateId(entity.getExchangeDateId())
                .sourceCurrencyId(entity.getSourceCurrencyId())
                .targetCurrencyId(entity.getTargetCurrencyId()).build();
        exchangeRates.add(newEntity);
        return newId;
    }

    @Override
    public ExchangeRateDAO read(Long id) {
        return exchangeRates.stream()
                .filter(exchangeRate -> exchangeRate.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(ExchangeRateDAO entity) {
        exchangeRates.stream()
                .filter(exchangeRate -> exchangeRate.getId() == entity.getId())
                .findFirst()
                .ifPresent(exchangeRate -> {
                    exchangeRate.setExchangeDateId(entity.getExchangeDateId());
                    exchangeRate.setSourceCurrencyId(entity.getSourceCurrencyId());
                    exchangeRate.setTargetCurrencyId(entity.getTargetCurrencyId());
                });

    }

    @Override
    public void delete(Long id) {
        exchangeRates.removeIf(exchangeRate -> exchangeRate.getId() == id);
    }
}

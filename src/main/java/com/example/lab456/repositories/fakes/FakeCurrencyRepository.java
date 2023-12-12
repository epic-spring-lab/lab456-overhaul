package com.example.lab456.repositories.fakes;

import com.example.lab456.dao.CurrencyDAO;
import com.example.lab456.repositories.OurCurrencyRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("fakeCurrencyRepository")
public class FakeCurrencyRepository implements OurCurrencyRepository {

    private static final Set<CurrencyDAO> currencies = Set.of(
            CurrencyDAO.builder().id(1L).name("US Dollar").code("USD").build(),
            CurrencyDAO.builder().id(2L).name("Euro").code("EUR").build(),
            CurrencyDAO.builder().id(3L).name("Polish Zloty").code("PLN").build()
    );

    @Override
    public Long create(CurrencyDAO entity) {
        long newId = currencies.size() + 1L;
        CurrencyDAO newEntity = CurrencyDAO.builder()
                .id(newId)
                .name(entity.getName())
                .code(entity.getCode())
                .build();
        currencies.add(newEntity);
        return newId;
    }

    @Override
    public CurrencyDAO read(Long id) {
        return currencies.stream()
                .filter(currency -> currency.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(CurrencyDAO entity) {
        currencies.stream()
                .filter(currency -> currency.getId() == entity.getId())
                .findFirst()
                .ifPresent(currency -> {
                    currency.setName(entity.getName());
                    currency.setCode(entity.getCode());
                });
    }

    @Override
    public void delete(Long id) {
        currencies.removeIf(currency -> currency.getId() == id);
    }
}

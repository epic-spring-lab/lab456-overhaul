package com.example.lab456.repositories.fakes;

import com.example.lab456.dao.ExchangeDateDAO;
import com.example.lab456.repositories.OurExchangeDateRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("fakeExchangeDateRepository")
public class FakeExchangeDateRepository implements OurExchangeDateRepository {

    private static final Set<ExchangeDateDAO> exchangeDates = Set.of(

    );


    @Override
    public Long create(ExchangeDateDAO entity) {
        long newId = exchangeDates.size() + 1L;
        ExchangeDateDAO newEntity = ExchangeDateDAO.builder()
                .id(newId)
                .date(entity.getDate())
                .build();
        exchangeDates.add(newEntity);
        return newId;
    }

    @Override
    public ExchangeDateDAO read(Long id) {
        return exchangeDates.stream()
                .filter(exchangeDate -> exchangeDate.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(ExchangeDateDAO entity) {
        exchangeDates.stream()
                .filter(exchangeDate -> exchangeDate.getId() == entity.getId())
                .findFirst()
                .ifPresent(exchangeDate -> {
                    exchangeDate.setDate(entity.getDate());
                });
    }

    @Override
    public void delete(Long id) {
        exchangeDates.removeIf(exchangeDate -> exchangeDate.getId() == id);
    }
}

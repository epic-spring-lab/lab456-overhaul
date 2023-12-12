package com.example.lab456.services.normal;

import com.example.lab456.dto.ExchangeDateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeDateDTO;
import com.example.lab456.entities.ExchangeDateEntity;
import com.example.lab456.repositories.normal.ExchangeDateRepository;
import com.example.lab456.services.ExchangeDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("normalExchangeDateService")
@RequiredArgsConstructor
public class ExchangeDateServiceImpl implements ExchangeDateService {

    private final ExchangeDateRepository exchangeDateRepository;

    @Override
    public Long create(CrupdateExchangeDateDTO exchangeDateDTO) {
        return exchangeDateRepository.save(ExchangeRateMapper.toEntity(exchangeDateDTO)).getId();
    }

    @Override
    public ExchangeDateDTO get(Long id) {
        return exchangeDateRepository.findById(id)
                .map(ExchangeDateEntity::toDto)
                .orElse(null);
    }

    @Override
    public void update(Long id, CrupdateExchangeDateDTO exchangeDateDTO) {
        exchangeDateRepository.findById(id)
                .ifPresent(exchangeDateEntity -> {
                    exchangeDateEntity.setDay(exchangeDateDTO.getDay());
                    exchangeDateEntity.setMonth(exchangeDateDTO.getMonth());
                    exchangeDateEntity.setYear(exchangeDateDTO.getYear());
                    exchangeDateRepository.save(exchangeDateEntity);
                });
    }

    @Override
    public void delete(Long id) {
        exchangeDateRepository.deleteById(id);
    }

    private static class ExchangeRateMapper {
        private static ExchangeDateEntity toEntity(CrupdateExchangeDateDTO crupdateExchangeDateDTO) {
            return ExchangeDateEntity.builder()
                    .day(crupdateExchangeDateDTO.getDay())
                    .month(crupdateExchangeDateDTO.getMonth())
                    .year(crupdateExchangeDateDTO.getYear())
                    .build();
        }
    }
}

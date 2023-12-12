package com.example.lab456.services.normal;

import com.example.lab456.dto.ExchangeDateDTO;
import com.example.lab456.dto.ExchangeRateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeRateDTO;
import com.example.lab456.entities.CurrencyEntity;
import com.example.lab456.entities.ExchangeDateEntity;
import com.example.lab456.entities.ExchangeRateEntity;
import com.example.lab456.repositories.normal.ExchangeRateRepository;
import com.example.lab456.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("normalExchangeRateService")
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public Long create(CrupdateExchangeRateDTO exchangeRateDTO) {
        return exchangeRateRepository.save(ExchangeRateMapper.toEntity(exchangeRateDTO)).getId();
    }

    @Override
    public ExchangeRateDTO get(Long id) {
        return exchangeRateRepository.findById(id)
                .map(ExchangeRateEntity::toDto)
                .orElse(null);
    }

    @Override
    public void update(Long id, CrupdateExchangeRateDTO exchangeRateDTO) {
        exchangeRateRepository.findById(id)
                .ifPresent(exchangeRateEntity -> {
                    exchangeRateEntity.setRate(exchangeRateDTO.getRate());
                    exchangeRateEntity.setExchangeDate(ExchangeDateEntity.builder().id(exchangeRateDTO.getExchangeDateId()).build());
                    exchangeRateEntity.setSourceCurrency(CurrencyEntity.builder().id(exchangeRateDTO.getSourceCurrencyId()).build());
                    exchangeRateEntity.setTargetCurrency(CurrencyEntity.builder().id(exchangeRateDTO.getTargetCurrencyId()).build());
                    exchangeRateRepository.save(exchangeRateEntity);
                });
    }

    @Override
    public void delete(Long id) {
        exchangeRateRepository.deleteById(id);
    }

    private static class ExchangeRateMapper {
        private static ExchangeRateEntity toEntity(CrupdateExchangeRateDTO crupdateExchangeRateDTO) {
            return ExchangeRateEntity.builder()
                    .rate(crupdateExchangeRateDTO.getRate())
                    .exchangeDate(ExchangeDateEntity.builder().id(crupdateExchangeRateDTO.getExchangeDateId()).build())
                    .sourceCurrency(CurrencyEntity.builder().id(crupdateExchangeRateDTO.getSourceCurrencyId()).build())
                    .targetCurrency(CurrencyEntity.builder().id(crupdateExchangeRateDTO.getTargetCurrencyId()).build())
                    .build();
        }
    }
}

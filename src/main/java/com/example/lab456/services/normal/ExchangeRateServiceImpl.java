package com.example.lab456.services.normal;

import com.example.lab456.dto.ExchangeRateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeRateDTO;
import com.example.lab456.entities.CurrencyEntity;
import com.example.lab456.entities.ExchangeDateEntity;
import com.example.lab456.entities.ExchangeRateEntity;
import com.example.lab456.repositories.normal.CurrencyRepository;
import com.example.lab456.repositories.normal.ExchangeRateRepository;
import com.example.lab456.services.interfaces.CRUDExchangeRateService;
import com.example.lab456.services.interfaces.NormalExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("normalExchangeRateService")
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements CRUDExchangeRateService, NormalExchangeRateService {

    private final CurrencyRepository currencyRepository;

    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<ExchangeRateDTO> getAll() {
        return exchangeRateRepository.findAll()
                .stream()
                .map(ExchangeRateEntity::toDto)
                .toList();
    }

    @Override
    public List<ExchangeRateDTO> getAllTodayExchanges() {
        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        return exchangeRateRepository.findExchangesForDate(day, month, year)
                .stream()
                .map(ExchangeRateEntity::toDto)
                .toList();
    }

    @Override
    public List<ExchangeRateDTO> getAllByCurrencyAndDate(int day, int month, int year, String source, String target) {
        return exchangeRateRepository.findByDateAndSourceCurrency(day, month, year, source, target)
                .stream()
                .map(ExchangeRateEntity::toDto)
                .toList();
    }

    @Override
    public List<ExchangeRateDTO> getAllExchangesByPair(String source, String target) {
        CurrencyEntity sourceCurrency = currencyRepository.findByName(source);
        CurrencyEntity targetCurrency = currencyRepository.findByName(target);
        return exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(sourceCurrency, targetCurrency)
                .stream()
                .map(ExchangeRateEntity::toDto)
                .toList();
    }

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

    @Override
    public void deleteAll() {
        exchangeRateRepository.deleteAll();
    }

    @Override
    public List<ExchangeRateEntity> createAll(List<ExchangeRateEntity> exchanges) {
        return (List<ExchangeRateEntity>) exchangeRateRepository.saveAll(exchanges);
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

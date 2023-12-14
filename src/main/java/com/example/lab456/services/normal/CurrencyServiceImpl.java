package com.example.lab456.services.normal;

import com.example.lab456.dto.CurrencyDTO;
import com.example.lab456.dto.crupdate.CrupdateCurrencyDTO;
import com.example.lab456.entities.CurrencyEntity;
import com.example.lab456.repositories.normal.CurrencyRepository;
import com.example.lab456.services.interfaces.CRUDCurrencyService;
import com.example.lab456.services.interfaces.NormalCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("normalCurrencyService")
public class CurrencyServiceImpl implements CRUDCurrencyService, NormalCurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public Long create(CrupdateCurrencyDTO currencyDTO) {
        CurrencyEntity currencyEntity = CurrencyMapper.toEntity(currencyDTO);
        return currencyRepository.save(currencyEntity).getId();
    }

    @Override
    public CurrencyDTO get(Long id) {
        return currencyRepository.findById(id)
                .map(CurrencyEntity::toDto)
                .orElse(null);
    }

    @Override
    public void update(Long id, CrupdateCurrencyDTO currencyDTO) {
        currencyRepository.findById(id)
                .ifPresent(currencyEntity -> {
                    currencyEntity.setName(currencyDTO.getName());
                    currencyEntity.setCode(currencyDTO.getCode());
                    currencyRepository.save(currencyEntity);
                });
    }

    @Override
    public void delete(Long id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public List<CurrencyEntity> getAll() {
        return currencyRepository.findAll();
    }

    @Override
    public List<CurrencyEntity> createAll(List<CurrencyEntity> currencies) {
        return (List<CurrencyEntity>) currencyRepository.saveAll(currencies);
    }

    private static class CurrencyMapper {
        static CurrencyEntity toEntity(CrupdateCurrencyDTO currencyDTO) {
            return CurrencyEntity.builder()
                    .name(currencyDTO.getName())
                    .code(currencyDTO.getCode())
                    .build();
        }
    }
}

package com.example.lab456.services.normal;

import com.example.lab456.dto.CurrencyDTO;
import com.example.lab456.entities.CurrencyEntity;
import com.example.lab456.repositories.normal.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("normalCurrencyService")
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public Long createCurrency(CurrencyDTO currencyDTO) {
        CurrencyEntity currencyEntity = CurrencyMapper.toEntity(currencyDTO);
        return currencyRepository.save(currencyEntity).getId();
    }

    @Override
    public CurrencyDTO getCurrency(Long id) {
        return currencyRepository.findById(id)
                .map(CurrencyMapper::toDTO)
                .orElse(null);
    }

    @Override
    public void updateCurrency(CurrencyDTO currencyDTO) {
        currencyRepository.findById(currencyDTO.getId())
                .ifPresent(currencyEntity -> {
                    currencyEntity.setName(currencyDTO.getName());
                    currencyEntity.setCode(currencyDTO.getCode());
                    currencyRepository.save(currencyEntity);
                });
    }

    @Override
    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }

    private static class CurrencyMapper {
        static CurrencyEntity toEntity(CurrencyDTO currencyDTO) {
            return CurrencyEntity.builder()
                    .name(currencyDTO.getName())
                    .code(currencyDTO.getCode())
                    .build();
        }

        static CurrencyDTO toDTO(CurrencyEntity currencyEntity) {
            return CurrencyDTO.builder()
                    .name(currencyEntity.getName())
                    .code(currencyEntity.getCode())
                    .build();
        }
    }
}

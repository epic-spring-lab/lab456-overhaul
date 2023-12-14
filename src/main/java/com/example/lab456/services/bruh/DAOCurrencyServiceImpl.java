package com.example.lab456.services.bruh;

import com.example.lab456.dto.CurrencyDTO;
import com.example.lab456.dto.crupdate.CrupdateCurrencyDTO;
import com.example.lab456.entities.CurrencyEntity;
import com.example.lab456.mappers.CurrencyMapper;
import com.example.lab456.repositories.jdbc.JDBCCurrencyDAO;
import com.example.lab456.services.interfaces.CRUDCurrencyService;
import com.example.lab456.services.interfaces.DAOCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("daoCurrencyService")
@RequiredArgsConstructor
public class DAOCurrencyServiceImpl implements DAOCurrencyService, CRUDCurrencyService {

    private final JDBCCurrencyDAO jdbcCurrencyDAO;

    @Override
    public List<CurrencyDTO> filterByName(String name) {
        return jdbcCurrencyDAO.filterByName(name).stream().map(CurrencyEntity::toDto).toList();
    }

    @Override
    public Long create(CrupdateCurrencyDTO currencyDTO) {
        return jdbcCurrencyDAO.create(CurrencyMapper.toEntity(currencyDTO));
    }

    @Override
    public CurrencyDTO get(Long id) {
        return jdbcCurrencyDAO.read(id).toDto();
    }

    @Override
    public void update(Long id, CrupdateCurrencyDTO currencyDTO) {
        CurrencyEntity currencyEntity = CurrencyMapper.toEntity(currencyDTO);
        currencyEntity.setId(id);
        jdbcCurrencyDAO.update(currencyEntity);
    }

    @Override
    public void delete(Long id) {
        jdbcCurrencyDAO.delete(id);
    }
}

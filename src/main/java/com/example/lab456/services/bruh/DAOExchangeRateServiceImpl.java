package com.example.lab456.services.bruh;

import com.example.lab456.dto.ExchangeRateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeRateDTO;
import com.example.lab456.entities.ExchangeRateEntity;
import com.example.lab456.mappers.ExchangeRateMapper;
import com.example.lab456.repositories.jdbc.JDBCExchangeRateDAO;
import com.example.lab456.services.interfaces.CRUDExchangeRateService;
import com.example.lab456.services.interfaces.DAOExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("daoExchangeRateService")
@RequiredArgsConstructor
public class DAOExchangeRateServiceImpl implements DAOExchangeRateService, CRUDExchangeRateService {

    private final JDBCExchangeRateDAO jdbcExchangeRateDAO;

    @Override
    public Long create(CrupdateExchangeRateDTO exchangeRateDTO) {
        return jdbcExchangeRateDAO.create(ExchangeRateMapper.toEntity(exchangeRateDTO));
    }

    @Override
    public ExchangeRateDTO get(Long id) {
        return jdbcExchangeRateDAO.read(id).map(ExchangeRateEntity::toDto).orElse(null);
    }

    @Override
    public void update(Long id, CrupdateExchangeRateDTO exchangeRateDTO) {
        ExchangeRateEntity exchangeRateEntity = ExchangeRateMapper.toEntity(exchangeRateDTO);
        exchangeRateEntity.setId(id);
        jdbcExchangeRateDAO.update(exchangeRateEntity);
    }

    @Override
    public void delete(Long id) {
        jdbcExchangeRateDAO.delete(id);
    }

    @Override
    public List<ExchangeRateDTO> filterBySourceWithPagination(int page, int size, String source) {
        return jdbcExchangeRateDAO.filterBySourceWithPagination(page, size, source).stream().map(ExchangeRateEntity::toDto).toList();
    }
}

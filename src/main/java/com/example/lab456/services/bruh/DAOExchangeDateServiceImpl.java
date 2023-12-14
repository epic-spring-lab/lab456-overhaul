package com.example.lab456.services.bruh;

import com.example.lab456.dto.ExchangeDateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeDateDTO;
import com.example.lab456.entities.ExchangeDateEntity;
import com.example.lab456.mappers.ExchangeDateMapper;
import com.example.lab456.repositories.jdbc.JDBCExchangeDateDAO;
import com.example.lab456.services.interfaces.CRUDExchangeDateService;
import com.example.lab456.services.interfaces.DAOExchangeDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("daoExchangeDateService")
@RequiredArgsConstructor
public class DAOExchangeDateServiceImpl implements DAOExchangeDateService, CRUDExchangeDateService {

    private final JDBCExchangeDateDAO jdbcExchangeDateDAO;

    @Override
    public Long create(CrupdateExchangeDateDTO exchangeDateDTO) {
        return jdbcExchangeDateDAO.create(ExchangeDateMapper.toEntity(exchangeDateDTO));
    }

    @Override
    public ExchangeDateDTO get(Long id) {
        return jdbcExchangeDateDAO.read(id).toDto();
    }

    @Override
    public void update(Long id, CrupdateExchangeDateDTO exchangeDateDTO) {
        ExchangeDateEntity exchangeDateEntity = ExchangeDateMapper.toEntity(exchangeDateDTO);
        exchangeDateEntity.setId(id);
        jdbcExchangeDateDAO.update(exchangeDateEntity);
    }

    @Override
    public void delete(Long id) {
        jdbcExchangeDateDAO.delete(id);
    }

    @Override
    public List<ExchangeDateDTO> findEvenDatesForYear(int year) {
        return jdbcExchangeDateDAO.findEvenDatesForYear(year).stream().map(ExchangeDateEntity::toDto).toList();
    }
}

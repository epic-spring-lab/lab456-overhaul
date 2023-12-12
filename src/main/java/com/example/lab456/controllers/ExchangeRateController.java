package com.example.lab456.controllers;

import com.example.lab456.dto.CurrencyDTO;
import com.example.lab456.dto.ExchangeRateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeRateDTO;
import com.example.lab456.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/exchange-rates")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeRateDTO> get(@PathVariable Long id) {
        ExchangeRateDTO exchangeRateDTO = exchangeRateService.get(id);
        if (exchangeRateDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exchangeRateDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody CrupdateExchangeRateDTO exchangeRateDTO) {
        Long createdId = exchangeRateService.create(exchangeRateDTO);
        return new ResponseEntity<>("Created exchange rate with id: " + createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CrupdateExchangeRateDTO exchangeRateDTO) {
        exchangeRateService.update(id, exchangeRateDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        exchangeRateService.delete(id);
    }


}

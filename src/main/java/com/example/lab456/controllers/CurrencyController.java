package com.example.lab456.controllers;

import com.example.lab456.dto.CurrencyDTO;
import com.example.lab456.services.normal.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping("")
    public ResponseEntity<String> createCurrency(@RequestBody CurrencyDTO currencyDTO) {
        Long createdId = currencyService.createCurrency(currencyDTO);
        return new ResponseEntity<>("Created currency with id: " + createdId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDTO> getCurrency(@PathVariable Long id) {
        CurrencyDTO currencyDTO = currencyService.getCurrency(id);
        return ResponseEntity.ok(currencyDTO);
    }

    @PutMapping("/{id}")
    public void updateCurrency(@PathVariable Long id, @RequestBody CurrencyDTO currencyDTO) {
        currencyDTO.setId(id);
        currencyService.updateCurrency(currencyDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCurrency(@PathVariable Long id) {
        currencyService.deleteCurrency(id);
    }

}

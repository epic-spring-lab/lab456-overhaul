package com.example.lab456.controllers;

import com.example.lab456.dto.CurrencyDTO;
import com.example.lab456.services.CurrencyService;
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
    public ResponseEntity<String> create(@RequestBody CurrencyDTO currencyDTO) {
        Long createdId = currencyService.create(currencyDTO);
        return new ResponseEntity<>("Created currency with id: " + createdId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDTO> get(@PathVariable Long id) {
        CurrencyDTO currencyDTO = currencyService.get(id);
        return ResponseEntity.ok(currencyDTO);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CurrencyDTO currencyDTO) {
        currencyDTO.setId(id);
        currencyService.update(currencyDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        currencyService.delete(id);
    }

}

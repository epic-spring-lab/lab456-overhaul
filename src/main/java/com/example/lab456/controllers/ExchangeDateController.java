package com.example.lab456.controllers;

import com.example.lab456.dto.ExchangeDateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeDateDTO;
import com.example.lab456.services.ExchangeDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/exchange-dates")
@RequiredArgsConstructor
public class ExchangeDateController {

    private final ExchangeDateService exchangeDateService;

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeDateDTO> get(@PathVariable Long id) {
        ExchangeDateDTO exchangeDateDTO = exchangeDateService.get(id);
        if (exchangeDateDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exchangeDateDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody CrupdateExchangeDateDTO exchangeDateDTO) {
        Long createdId = exchangeDateService.create(exchangeDateDTO);
        return new ResponseEntity<>("Created exchange rate with id: " + createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CrupdateExchangeDateDTO exchangeDateDTO) {
        exchangeDateService.update(id, exchangeDateDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        exchangeDateService.delete(id);
    }

}

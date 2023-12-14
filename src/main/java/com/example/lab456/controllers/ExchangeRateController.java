package com.example.lab456.controllers;

import com.example.lab456.dto.CurrencyDTO;
import com.example.lab456.dto.ExchangeRateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeRateDTO;
import com.example.lab456.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exchange-rates")
@RequiredArgsConstructor
@Api(tags = "Exchange Rate Controller", description = "API operations related to exchange rates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get exchange rate by ID", notes = "Retrieves exchange rate information based on the provided ID.")
    public ResponseEntity<ExchangeRateDTO> get(
            @ApiParam(value = "ID of the exchange rate to retrieve", required = true)
            @PathVariable Long id) {
        ExchangeRateDTO exchangeRateDTO = exchangeRateService.get(id);
        if (exchangeRateDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exchangeRateDTO, HttpStatus.OK);
    }

    @GetMapping("")
    @ApiOperation(value = "Get all exchange rates", notes = "Retrieves information about all exchange rates.")
    public ResponseEntity<List<ExchangeRateDTO>> getAll() {
        return new ResponseEntity<>(exchangeRateService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/today")
    @ApiOperation(value = "Get all exchange rates for today", notes = "Retrieves information about all exchange rates for the current day.")
    public ResponseEntity<List<ExchangeRateDTO>> getAllTodayRates() {
        return new ResponseEntity<>(exchangeRateService.getAllTodayExchanges(), HttpStatus.OK);
    }

    @GetMapping("/pair")
    @ApiOperation(value = "Get all exchange rates by currency pair", notes = "Retrieves information about all exchange rates based on the provided currency pair.")
    public ResponseEntity<List<ExchangeRateDTO>> getAllExchangesByPair(
            @ApiParam(value = "Source currency", required = false)
            @RequestParam(required = false, name = "source") String source,
            @ApiParam(value = "Target currency", required = false)
            @RequestParam(required = false, name = "target") String target) {
        return new ResponseEntity<>(exchangeRateService.getAllExchangesByPair(source, target), HttpStatus.OK);
    }

    @GetMapping("/currency")
    @ApiOperation(value = "Get all exchange rates by currency, day, month, and year", notes = "Retrieves information about all exchange rates based on the provided currency, day, month, and year.")
    public ResponseEntity<List<ExchangeRateDTO>> getAllByCurrencyAndDate(
            @ApiParam(value = "Source currency", required = false)
            @RequestParam(required = false, name = "source") String source,
            @ApiParam(value = "Target currency", required = false)
            @RequestParam(required = false, name = "target") String target,
            @ApiParam(value = "Day", required = false)
            @RequestParam(required = false, name = "day") int day,
            @ApiParam(value = "Month", required = false)
            @RequestParam(required = false, name = "month") int month,
            @ApiParam(value = "Year", required = false)
            @RequestParam(required = false, name = "year") int year) {
        return new ResponseEntity<>(exchangeRateService.getAllByCurrencyAndDate(day, month, year, source, target), HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "Create a new exchange rate", notes = "Creates a new exchange rate with the provided data.")
    public ResponseEntity<String> create(
            @ApiParam(value = "Exchange rate data for creation", required = true)
            @RequestBody CrupdateExchangeRateDTO exchangeRateDTO) {
        Long createdId = exchangeRateService.create(exchangeRateDTO);
        return new ResponseEntity<>("Created exchange rate with id: " + createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update exchange rate by ID", notes = "Updates exchange rate information based on the provided ID.")
    public ResponseEntity<Void> update(
            @ApiParam(value = "ID of the exchange rate to update", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated exchange rate data", required = true)
            @RequestBody CrupdateExchangeRateDTO exchangeRateDTO) {
        exchangeRateService.update(id, exchangeRateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete exchange rate by ID", notes = "Deletes exchange rate based on the provided ID.")
    public ResponseEntity<Void> delete(
            @ApiParam(value = "ID of the exchange rate to delete", required = true)
            @PathVariable Long id) {
        exchangeRateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
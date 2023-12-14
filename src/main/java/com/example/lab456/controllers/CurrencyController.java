package com.example.lab456.controllers;

import com.example.lab456.dto.CurrencyDTO;
import com.example.lab456.dto.crupdate.CrupdateCurrencyDTO;
import com.example.lab456.services.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/currencies")
@RequiredArgsConstructor
@Api(tags = "Currency Controller", description = "API operations related to currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping("")
    @ApiOperation(value = "Create a new currency", notes = "Creates a new currency with the provided data.")
    public ResponseEntity<String> create(
            @ApiParam(value = "Currency data for creation", required = true)
            @RequestBody CrupdateCurrencyDTO currencyDTO) {
        Long createdId = currencyService.create(currencyDTO);
        return new ResponseEntity<>("Created currency with id: " + createdId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get currency by ID", notes = "Retrieves currency information based on the provided ID.")
    public ResponseEntity<CurrencyDTO> get(
            @ApiParam(value = "ID of the currency to retrieve", required = true)
            @PathVariable Long id) {
        CurrencyDTO currencyDTO = currencyService.get(id);
        if (currencyDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currencyDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update currency by ID", notes = "Updates currency information based on the provided ID.")
    public ResponseEntity<Void> update(
            @ApiParam(value = "ID of the currency to update", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated currency data", required = true)
            @RequestBody CrupdateCurrencyDTO currencyDTO) {
        currencyService.update(id, currencyDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete currency by ID", notes = "Deletes currency based on the provided ID.")
    public ResponseEntity<Void> delete(
            @ApiParam(value = "ID of the currency to delete", required = true)
            @PathVariable Long id) {
        currencyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
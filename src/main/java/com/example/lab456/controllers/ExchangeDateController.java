package com.example.lab456.controllers;

import com.example.lab456.dto.ExchangeDateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeDateDTO;
import com.example.lab456.services.ExchangeDateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/exchange-dates")
@RequiredArgsConstructor
@Api(tags = "Exchange Date Controller", description = "API operations related to exchange dates")
public class ExchangeDateController {

    private final ExchangeDateService exchangeDateService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get exchange date by ID", notes = "Retrieves exchange date information based on the provided ID.")
    public ResponseEntity<ExchangeDateDTO> get(
            @ApiParam(value = "ID of the exchange date to retrieve", required = true)
            @PathVariable Long id) {
        ExchangeDateDTO exchangeDateDTO = exchangeDateService.get(id);
        if (exchangeDateDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exchangeDateDTO, HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "Create a new exchange date", notes = "Creates a new exchange date with the provided data.")
    public ResponseEntity<String> create(
            @ApiParam(value = "Exchange date data for creation", required = true)
            @RequestBody CrupdateExchangeDateDTO exchangeDateDTO) {
        Long createdId = exchangeDateService.create(exchangeDateDTO);
        return new ResponseEntity<>("Created exchange rate with id: " + createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update exchange date by ID", notes = "Updates exchange date information based on the provided ID.")
    public ResponseEntity<Void> update(
            @ApiParam(value = "ID of the exchange date to update", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated exchange date data", required = true)
            @RequestBody CrupdateExchangeDateDTO exchangeDateDTO) {
        exchangeDateService.update(id, exchangeDateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete exchange date by ID", notes = "Deletes exchange date based on the provided ID.")
    public ResponseEntity<Void> delete(
            @ApiParam(value = "ID of the exchange date to delete", required = true)
            @PathVariable Long id) {
        exchangeDateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
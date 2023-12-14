package com.example.lab456.controllers;

import com.example.lab456.dto.ExchangeRateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeRateDTO;
import com.example.lab456.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/exchange-rates")
@RequiredArgsConstructor
@Tag(name = "Exchange Rate Controller", description = "API operations related to exchange rates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Get exchange rate by ID",
            description = "Retrieves exchange rate information based on the provided ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = ExchangeRateDTO.class))
    )
    public ResponseEntity<ExchangeRateDTO> get(
            @Parameter(
                    description = "ID of the exchange rate to retrieve",
                    required = true
            )
            @PathVariable Long id) {
        ExchangeRateDTO exchangeRateDTO = exchangeRateService.get(id);
        if (exchangeRateDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exchangeRateDTO, HttpStatus.OK);
    }

    @GetMapping("")
    @Operation(
            summary = "Get all exchange rates",
            description = "Retrieves information about all exchange rates."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = ExchangeRateDTO.class))
    )
    public ResponseEntity<List<ExchangeRateDTO>> getAll() {
        return new ResponseEntity<>(exchangeRateService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/today")
    @Operation(
            summary = "Get all exchange rates for today",
            description = "Retrieves information about all exchange rates for the current day."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = ExchangeRateDTO.class))
    )
    public ResponseEntity<List<ExchangeRateDTO>> getAllTodayRates() {
        return new ResponseEntity<>(exchangeRateService.getAllTodayExchanges(), HttpStatus.OK);
    }

    @GetMapping("/pair")
    @Operation(
            summary = "Get all exchange rates by currency pair",
            description = "Retrieves information about all exchange rates based on the provided currency pair."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = ExchangeRateDTO.class))
    )
    public ResponseEntity<List<ExchangeRateDTO>> getAllExchangesByPair(
            @Parameter(
                    description = "Source currency",
                    required = false
            )
            @RequestParam(required = false, name = "source") String source,
            @Parameter(
                    description = "Target currency",
                    required = false
            )
            @RequestParam(required = false, name = "target") String target) {
        return new ResponseEntity<>(exchangeRateService.getAllExchangesByPair(source, target), HttpStatus.OK);
    }

    @GetMapping("/currency")
    @Operation(
            summary = "Get all exchange rates by currency, day, month, and year",
            description = "Retrieves information about all exchange rates based on the provided currency, day, month, and year."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = ExchangeRateDTO.class))
    )
    public ResponseEntity<List<ExchangeRateDTO>> getAllByCurrencyAndDate(
            @Parameter(
                    description = "Source currency",
                    required = false
            )
            @RequestParam(required = false, name = "source") String source,
            @Parameter(
                    description = "Target currency",
                    required = false
            )
            @RequestParam(required = false, name = "target") String target,
            @Parameter(
                    description = "Day",
                    required = false
            )
            @RequestParam(required = false, name = "day") int day,
            @Parameter(
                    description = "Month",
                    required = false
            )
            @RequestParam(required = false, name = "month") int month,
            @Parameter(
                    description = "Year",
                    required = false
            )
            @RequestParam(required = false, name = "year") int year) {
        return new ResponseEntity<>(exchangeRateService.getAllByCurrencyAndDate(day, month, year, source, target), HttpStatus.OK);
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new exchange rate",
            description = "Creates a new exchange rate with the provided data."
    )
    public ResponseEntity<String> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Exchange rate data for creation",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CrupdateExchangeRateDTO.class))
            )
            @RequestBody CrupdateExchangeRateDTO exchangeRateDTO) {
        Long createdId = exchangeRateService.create(exchangeRateDTO);
        return new ResponseEntity<>("Created exchange rate with id: " + createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update exchange rate by ID",
            description = "Updates exchange rate information based on the provided ID."
    )
    public ResponseEntity<Void> update(
            @Parameter(
                    description = "ID of the exchange rate to update",
                    required = true
            )
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated exchange rate data",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CrupdateExchangeRateDTO.class))
            )
            @RequestBody CrupdateExchangeRateDTO exchangeRateDTO) {
        exchangeRateService.update(id, exchangeRateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete exchange rate by ID",
            description = "Deletes exchange rate based on the provided ID."
    )
    public ResponseEntity<Void> delete(
            @Parameter(
                    description = "ID of the exchange rate to delete",
                    required = true
            )
            @PathVariable Long id) {
        exchangeRateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

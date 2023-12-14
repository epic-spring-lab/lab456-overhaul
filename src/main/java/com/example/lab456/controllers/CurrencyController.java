package com.example.lab456.controllers;

import com.example.lab456.dto.CurrencyDTO;
import com.example.lab456.dto.crupdate.CrupdateCurrencyDTO;
import com.example.lab456.services.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/currencies")
@RequiredArgsConstructor
@Tag(name = "Currency Controller", description = "API operations related to currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping("")
    @Operation(
            summary = "Create a new currency",
            description = "Creates a new currency with the provided data."
    )
    public ResponseEntity<String> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Currency data for creation",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CrupdateCurrencyDTO.class))
            )
            @RequestBody CrupdateCurrencyDTO currencyDTO) {
        Long createdId = currencyService.create(currencyDTO);
        return new ResponseEntity<>("Created currency with id: " + createdId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get currency by ID",
            description = "Retrieves currency information based on the provided ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = CurrencyDTO.class))
    )
    public ResponseEntity<CurrencyDTO> get(
            @Parameter(
                    description = "ID of the currency to retrieve",
                    required = true
            )
            @PathVariable Long id) {
        CurrencyDTO currencyDTO = currencyService.get(id);
        if (currencyDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currencyDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update currency by ID",
            description = "Updates currency information based on the provided ID."
    )
    public ResponseEntity<Void> update(
            @Parameter(
                    description = "ID of the currency to update",
                    required = true
            )
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated currency data",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CrupdateCurrencyDTO.class))
            )
            @RequestBody CrupdateCurrencyDTO currencyDTO) {
        currencyService.update(id, currencyDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete currency by ID",
            description = "Deletes currency based on the provided ID."
    )
    public ResponseEntity<Void> delete(
            @Parameter(
                    description = "ID of the currency to delete",
                    required = true
            )
            @PathVariable Long id) {
        currencyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

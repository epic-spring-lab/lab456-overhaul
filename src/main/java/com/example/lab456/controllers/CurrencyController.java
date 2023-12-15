package com.example.lab456.controllers;

import com.example.lab456.dto.CurrencyDTO;
import com.example.lab456.dto.crupdate.CrupdateCurrencyDTO;
import com.example.lab456.entities.CurrencyEntity;
import com.example.lab456.services.interfaces.CRUDCurrencyService;
import com.example.lab456.services.interfaces.DAOCurrencyService;
import com.example.lab456.services.interfaces.NormalCurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/currencies")
@Tag(name = "Currency Controller", description = "API operations related to currencies")
public class CurrencyController {

    private final CRUDCurrencyService currencyService;
    private final DAOCurrencyService daoCurrencyService;

    private final NormalCurrencyService normalCurrencyService;

    @Autowired
    public CurrencyController(@Qualifier("daoCurrencyService") CRUDCurrencyService currencyService,
                              @Qualifier("daoCurrencyService") DAOCurrencyService daoCurrencyService,
                              @Qualifier("normalCurrencyService") NormalCurrencyService normalCurrencyService) {
        this.currencyService = currencyService;
        this.daoCurrencyService = daoCurrencyService;
        this.normalCurrencyService = normalCurrencyService;
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new currency",
            description = "Creates a new currency with the provided data."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Currency created successfully",
            content = @Content(schema = @Schema(implementation = String.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid data provided",
            content = @Content(schema = @Schema(implementation = String.class))
    )
    public ResponseEntity<String> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Currency data for creation",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CrupdateCurrencyDTO.class))
            )
            @RequestBody CrupdateCurrencyDTO currencyDTO) {
        try {
            Long createdId = currencyService.create(currencyDTO);
            return new ResponseEntity<>("Created currency with id: " + createdId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
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
    @ApiResponse(
            responseCode = "404",
            description = "Currency not found",
            content = @Content(schema = @Schema(implementation = String.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid data provided",
            content = @Content(schema = @Schema(implementation = String.class))
    )
    public ResponseEntity<CurrencyDTO> get(
            @Parameter(
                    description = "ID of the currency to retrieve",
                    required = true
            )
            @PathVariable Long id) {
        try {
            CurrencyDTO currencyDTO = currencyService.get(id);
            if (currencyDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(currencyDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    @GetMapping("/filter")
    @Operation(
            summary = "Filter currencies by name",
            description = "Filters currencies based on the provided name."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = CurrencyDTO.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid data provided",
            content = @Content(schema = @Schema(implementation = String.class))
    )
    public ResponseEntity<List<CurrencyDTO>> filterByName(
            @Parameter(
                    description = "Name of the currency to filter",
                    required = true
            )
            @RequestParam String name) {
        if (name == null || name.isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<CurrencyDTO> currencyDTOs = daoCurrencyService.filterByName(name);
        return new ResponseEntity<>(currencyDTOs, HttpStatus.OK);
    }

    @PostMapping("/createAndUpdate")
    @Operation(
            summary = "Create and update currency",
            description = "Create and update currency"
    )
    public ResponseEntity<CurrencyDTO> createAndUpdate(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Currency data for creation",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CrupdateCurrencyDTO.class))
            )
            @RequestBody CrupdateCurrencyDTO currencyDTO) {
        CurrencyDTO createdCurrencyDTO = daoCurrencyService.createAndUpdate(currencyDTO);
        return new ResponseEntity<>(createdCurrencyDTO, HttpStatus.CREATED);
    }

    @PostMapping("/createAll")
    @Transactional
    @Operation(
            summary = "Create list of currencies",
            description = "Create list of currencies"
    )
    public ResponseEntity<List<CrupdateCurrencyDTO>> createAll(@RequestBody List<CrupdateCurrencyDTO> currencyDTOs){
        normalCurrencyService.createAll(currencyDTOs);
        return new ResponseEntity<>(currencyDTOs, HttpStatus.CREATED);
    }


}

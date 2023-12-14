package com.example.lab456.controllers;

import com.example.lab456.dto.ExchangeDateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeDateDTO;
import com.example.lab456.services.interfaces.CRUDExchangeDateService;
import com.example.lab456.services.interfaces.DAOExchangeDateService;
import com.example.lab456.services.interfaces.NormalExchangeDateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exchange-dates")
@Tag(name = "Exchange Date Controller", description = "API operations related to exchange dates")
public class ExchangeDateController {

    private final CRUDExchangeDateService crudExchangeDateService;
    private final DAOExchangeDateService daoExchangeDateService;

    @Autowired
    public ExchangeDateController(
            @Qualifier("normalExchangeDateService") CRUDExchangeDateService crudExchangeDateService,
            @Qualifier("daoExchangeDateService") DAOExchangeDateService daoExchangeDateService) {
        this.crudExchangeDateService = crudExchangeDateService;
        this.daoExchangeDateService = daoExchangeDateService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get exchange date by ID",
            description = "Retrieves exchange date information based on the provided ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = ExchangeDateDTO.class))
    )
    public ResponseEntity<ExchangeDateDTO> get(
            @Parameter(
                    description = "ID of the exchange date to retrieve",
                    required = true
            )
            @PathVariable Long id) {
        ExchangeDateDTO exchangeDateDTO = crudExchangeDateService.get(id);
        if (exchangeDateDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exchangeDateDTO, HttpStatus.OK);
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new exchange date",
            description = "Creates a new exchange date with the provided data."
    )
    public ResponseEntity<String> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Exchange date data for creation",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CrupdateExchangeDateDTO.class))
            )
            @RequestBody CrupdateExchangeDateDTO exchangeDateDTO) {
        Long createdId = crudExchangeDateService.create(exchangeDateDTO);
        return new ResponseEntity<>("Created exchange rate with id: " + createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update exchange date by ID",
            description = "Updates exchange date information based on the provided ID."
    )
    public ResponseEntity<Void> update(
            @Parameter(
                    description = "ID of the exchange date to update",
                    required = true
            )
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated exchange date data",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CrupdateExchangeDateDTO.class))
            )
            @RequestBody CrupdateExchangeDateDTO exchangeDateDTO) {
        crudExchangeDateService.update(id, exchangeDateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete exchange date by ID",
            description = "Deletes exchange date based on the provided ID."
    )
    public ResponseEntity<Void> delete(
            @Parameter(
                    description = "ID of the exchange date to delete",
                    required = true
            )
            @PathVariable Long id) {
        crudExchangeDateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/even-dates")
    @Operation(
            summary = "Get all even dates for a year",
            description = "Retrieves all even dates for a year."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = ExchangeDateDTO.class))
    )
    public ResponseEntity<List<ExchangeDateDTO>> getEvenDates(
            @Parameter(
                    description = "Year of the exchange date to retrieve",
                    required = true
            )
            @RequestParam int year) {
        List<ExchangeDateDTO> exchangeDateDTOs = daoExchangeDateService.findEvenDatesForYear(year);
        if (exchangeDateDTOs == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exchangeDateDTOs, HttpStatus.OK);
    }
}

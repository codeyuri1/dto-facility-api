package com.codeyuri.dtofacility.controller;

import com.codeyuri.dtofacility.dto.InputDTO;
import com.codeyuri.dtofacility.exception.CustomException;
import com.codeyuri.dtofacility.service.DTOGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generate")
public class DTOGeneratorController {

    private final DTOGeneratorService dtoGeneratorService;

    public DTOGeneratorController(DTOGeneratorService dtoGeneratorService) {
        this.dtoGeneratorService = dtoGeneratorService;
    }

    @PostMapping("/dto")
    @Operation(summary = "Gera um DTO Java a partir de um JSON de entrada")
    public ResponseEntity<String> generateDTO(@Valid @RequestBody InputDTO inputDTO) throws CustomException {
        String classContent = dtoGeneratorService.generateDTO(inputDTO.getJson(),
                inputDTO.getClassName() != null ? inputDTO.getClassName() : "MeuDTO");
        return ResponseEntity.ok(classContent);
    }

    @PostMapping("/record")
    @Operation(summary = "Gera um Java Record a partir de um JSON de entrada")
    public ResponseEntity<String> generateRecordDTO(@Valid @RequestBody InputDTO inputDTO) throws CustomException {
        String classContent = dtoGeneratorService.generateRecord(inputDTO.getJson(),
                inputDTO.getClassName() != null ? inputDTO.getClassName() : "MeuRecord");
        return ResponseEntity.ok(classContent);
    }
}

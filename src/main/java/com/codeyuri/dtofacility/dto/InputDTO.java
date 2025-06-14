package com.codeyuri.dtofacility.dto;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InputDTO {

    @Schema(description = "JSON de entrada para gerar o DTO", example = "{\"name\":\"Yuri\",\"idade\":30}")
    @NotBlank(message = "O campo json não pode ser vazio.")
    private JsonNode json;

    @Schema(description = "Nome do Seu DTO", example = "MeuDTO")
    private String className;

}

package com.codeyuri.dtofacility.dto;

import lombok.Data;

@Data
public class ThymeInputDTO {
    private String json;
    private String className;

    public ThymeInputDTO() {
        this.json = "{}"; // Inicializa com um JSON vazio
        this.className = "MeuDTO"; // Nome de classe padrão
    }
}

package com.codeyuri.dtofacility.service;

import com.codeyuri.dtofacility.exception.CustomException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DTOGeneratorServiceTest {

    private DTOGeneratorService service;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        service = new DTOGeneratorService();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGenerateDTO_withValidJson_shouldReturnDTOString() throws Exception {
        String json = "{ \"name\": \"Yuri\", \"idade\": 30 }";
        JsonNode jsonNode = objectMapper.readTree(json);

        String result = service.generateDTO(jsonNode, "UsuarioDTO");

        assertTrue(result.contains("public class UsuarioDTO"));
        assertTrue(result.contains("private String name;"));
        assertTrue(result.contains("private Integer idade;"));
    }

    @Test
    void testGenerateDTO_withInvalidJson_shouldThrowException(){
        JsonNode invalidNode = objectMapper.getNodeFactory().textNode("invalid");

        CustomException exception = assertThrows(CustomException.class, () -> {
            service.generateDTO(invalidNode, "InvalidDTO");
        });

        assertTrue(exception.getMessage().contains("JSON de entrada deve ser um objeto JSON válido"));
    }

    @Test
    void testGenerateRecord_withValidJson_shouldReturnRecordString() throws Exception {
        String json = "{ \"name\": \"Yuri\", \"ativo\": true }";
        JsonNode jsonNode = objectMapper.readTree(json);

        String result = service.generateRecord(jsonNode, "UsuarioRecord");

        assertTrue(result.contains("public record UsuarioRecord"));
        assertTrue(result.contains("String name"));
        assertTrue(result.contains("Boolean ativo"));
    }

    @Test
    void testGenerateRecord_withNestedJson_shouldIncludeInnerRecord() throws Exception {
        String json = "{ \"name\": \"Yuri\", \"endereco\": { \"rua\": \"Av Paulista\", \"numero\": 123 } }";
        JsonNode jsonNode = objectMapper.readTree(json);

        String result = service.generateRecord(jsonNode, "UsuarioRecord");

        assertTrue(result.contains("public static record Endereco"));
        assertTrue(result.contains("String rua"));
        assertTrue(result.contains("Integer numero"));
    }
}

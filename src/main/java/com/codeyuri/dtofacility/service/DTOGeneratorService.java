package com.codeyuri.dtofacility.service;

import com.codeyuri.dtofacility.exception.CustomException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DTOGeneratorService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateDTO(JsonNode jsonInput, String className) throws CustomException {

        if (!(jsonInput instanceof JsonNode objectNode)) {
            throw new CustomException("JSON de entrada deve ser um objeto JSON válido.");
        }

        StringBuilder classBuilder = new StringBuilder();
        classBuilder.append("public class ").append(className).append(" {\n\n");

        buildFieldsAndInnerClasses(objectNode, classBuilder, "");

        classBuilder.append("\n}");

        return classBuilder.toString();
    }

    private void buildFieldsAndInnerClasses(JsonNode objectNode, StringBuilder classBuilder, String indent) {
        Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();

        List<Map.Entry<String, JsonNode>> nestedObjects = new ArrayList<>();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String fieldName = field.getKey();
            JsonNode valueNode = field.getValue();

            if (valueNode.isObject()) {
                nestedObjects.add(field);
                classBuilder.append(indent).append("    private ").append(capitalize(fieldName)).append(" ").append(fieldName).append(";\n");
            } else {
                String fieldType = getJavaType(valueNode);
                classBuilder.append(indent).append("    private ").append(fieldType).append(" ").append(fieldName).append(";\n");
            }
        }

        for (Map.Entry<String, JsonNode> nestedField : nestedObjects) {
            String nestedClassName = capitalize(nestedField.getKey());
            classBuilder.append("\n").append(indent).append("    public static class ").append(nestedClassName).append(" {\n");
            buildFieldsAndInnerClasses((ObjectNode) nestedField.getValue(), classBuilder, indent + "    ");
            classBuilder.append(indent).append("    }\n");
        }
    }

    public String generateRecord(JsonNode jsonInput, String className) throws CustomException {
        if (!(jsonInput instanceof ObjectNode objectNode)) {
            throw new CustomException("JSON de entrada deve ser um objeto JSON válido.");
        }

        StringBuilder classBuilder = new StringBuilder();
        StringBuilder nestedClasses = new StringBuilder();

        classBuilder.append("public record ").append(className).append(" (\n");

        buildRecordFieldsAndNestedClasses(objectNode, classBuilder, nestedClasses, "");

        classBuilder.append("\n) {\n");
        classBuilder.append(nestedClasses);
        classBuilder.append("}");

        return classBuilder.toString();
    }

    private void buildRecordFieldsAndNestedClasses(ObjectNode objectNode, StringBuilder fieldsBuilder, StringBuilder nestedClasses, String indent) {
        Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();

        List<Map.Entry<String, JsonNode>> nestedObjects = new ArrayList<>();
        List<String> fieldLines = new ArrayList<>();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String fieldName = field.getKey();
            JsonNode valueNode = field.getValue();

            if (valueNode.isObject()) {
                nestedObjects.add(field);
                fieldLines.add(indent + capitalize(fieldName) + " " + fieldName);
            } else {
                String fieldType = getJavaType(valueNode);
                fieldLines.add(indent + fieldType + " " + fieldName);
            }
        }

        if (!fieldLines.isEmpty()) {
            fieldsBuilder.append(String.join(",\n" + indent, fieldLines));
        }

        for (Map.Entry<String, JsonNode> nestedField : nestedObjects) {
            String nestedClassName = capitalize(nestedField.getKey());
            StringBuilder nestedFieldsBuilder = new StringBuilder();

            buildRecordFieldsAndNestedClasses((ObjectNode) nestedField.getValue(), nestedFieldsBuilder, new StringBuilder(), indent + "    ");

            nestedClasses.append(indent).append("    public static record ").append(nestedClassName).append(" (\n")
                    .append(nestedFieldsBuilder)
                    .append("\n").append(indent).append("    ) {}\n\n");
        }
    }

    private String getJavaType(JsonNode node) {
        if (node.isInt()) return "Integer";
        if (node.isLong()) return "Long";
        if (node.isBoolean()) return "Boolean";
        if (node.isDouble() || node.isFloat()) return "Double";
        return "String";
    }

    private String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}

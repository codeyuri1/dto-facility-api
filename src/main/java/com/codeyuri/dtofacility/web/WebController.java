package com.codeyuri.dtofacility.web;

import com.codeyuri.dtofacility.dto.InputDTO;
import com.codeyuri.dtofacility.dto.ThymeInputDTO;
import com.codeyuri.dtofacility.exception.CustomException;
import com.codeyuri.dtofacility.service.DTOGeneratorService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class WebController {

    private final DTOGeneratorService dtoGeneratorService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WebController(DTOGeneratorService dtoGeneratorService) {
        this.dtoGeneratorService = dtoGeneratorService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("inputDTO", new ThymeInputDTO());
        return "generate";
    }

    @PostMapping("/generateDTO")
    public String generateDTO(@ModelAttribute @Valid ThymeInputDTO inputDTO, Model model) {
        try {
            JsonNode jsonNode = objectMapper.readTree(inputDTO.getJson());
            String result = dtoGeneratorService.generateDTO(jsonNode.get("json"),
                    inputDTO.getClassName() != null ? inputDTO.getClassName() : "MeuDTO");
            model.addAttribute("result", result);
        } catch (CustomException e) {
            model.addAttribute("error", "Erro de negócio: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "JSON inválido: " + e.getMessage());
        }
        return "generate";
    }

    @PostMapping("/generateRecord")
    public String generateRecordDTO(@ModelAttribute @Valid ThymeInputDTO inputDTO, Model model) {
        try {
            JsonNode jsonNode = objectMapper.readTree(inputDTO.getJson());
            String result = dtoGeneratorService.generateRecord(jsonNode.get("json"),
                    inputDTO.getClassName() != null ? inputDTO.getClassName() : "MeuRecord");
            model.addAttribute("result", result);
        } catch (CustomException e) {
            model.addAttribute("error", "Erro de negócio: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "JSON inválido: " + e.getMessage());
        }
        return "generate";
    }
}

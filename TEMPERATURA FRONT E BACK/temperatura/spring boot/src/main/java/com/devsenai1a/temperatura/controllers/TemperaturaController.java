package com.devsenai1a.temperatura.controllers;


import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*") // permite acesso do frontend
public class TemperaturaController {

    @PostMapping("/converter")
    public Map<String, Object> converter(
            @RequestParam double valor,
            @RequestParam String de,
            @RequestParam String para) {

        Map<String, Object> resposta = new HashMap<>();

        if (de.equals(para)) {
            resposta.put("resultado", valor);
            resposta.put("unidade", unidadeSimbolo(para));
            return resposta;
        }

        double resultado = 0;

        switch (de.toLowerCase()) {
            case "celsius":
                if (para.equals("fahrenheit")) resultado = (valor * 9/5) + 32;
                else if (para.equals("kelvin")) resultado = valor + 273.15;
                break;

            case "fahrenheit":
                if (para.equals("celsius")) resultado = (valor - 32) * 5/9;
                else if (para.equals("kelvin")) resultado = ((valor - 32) * 5/9) + 273.15;
                break;

            case "kelvin":
                if (para.equals("celsius")) resultado = valor - 273.15;
                else if (para.equals("fahrenheit")) resultado = ((valor - 273.15) * 9/5) + 32;
                break;

            default:
                resposta.put("erro", "Unidade inválida");
                return resposta;
        }

        resposta.put("resultado", Math.round(resultado * 100.0) / 100.0);
        resposta.put("unidade", unidadeSimbolo(para));
        return resposta;
    }

    private String unidadeSimbolo(String unidade) {
        return switch (unidade.toLowerCase()) {
            case "celsius" -> "°C";
            case "fahrenheit" -> "°F";
            case "kelvin" -> "K";
            default -> "";
        };
    }
}

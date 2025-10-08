package com.devsenai1a.moeda.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoedaControllers {
	
	@PostMapping("/converter")
	public Map<String, Object> converter(
			@RequestParam double valor,
			@RequestParam String de,
			@RequestParam String para
			){
		
		Map<String, Object> resposta = new HashMap<>();
		
		if (de.equals(para)) {
            resposta.put("resultado", valor);
            resposta.put("unidade", unidadeSimbolo(para));
            return resposta;
        }
		
		   double resultado = 0;
		  

		    switch(de) {
		        case "brl": 
		        if(para.equals("dolar")) resultado = valor/5.35;
		        if(para.equals("euro")) resultado = valor/6.23;
		            break;
		        case "dolar": 
		        if(para.equals("brl")) resultado = valor*5.35;
		        if(para.equals("euro")) resultado = valor/0.86;
		            break;
		        case "euro": 
		        if(para.equals("brl")) resultado = valor*6.23;
		        if(para.equals("dolar")) resultado = valor/1.17;
		            break;
		    }
		    
		  
		    	resposta.put("resultado", String.format("%.2f", resultado));
		    	resposta.put("unidade", unidadeSimbolo(para));
		    
		    
		    return resposta;
	}
		    
		    private String unidadeSimbolo(String unidade) {
		        return switch (unidade.toLowerCase()) {
		            case "brl" -> "R$";
		            case "dolar" -> "US$";
		            case "euro" -> "€";
		            default -> "";
	
		        };
		    
	    }       
}
	


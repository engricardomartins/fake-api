package com.javanautas.fakeapius.apivi.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductsDTO {

	@JsonProperty(value = "id")
	@JsonIgnore
	private String id;
	
	@JsonProperty(value = "entity_id")
	private String entityId;
	
	@JsonProperty(value = "title")
    private String nome;
	
	@JsonProperty(value = "price")
    private BigDecimal preco;
	
	@JsonProperty(value = "category")
	private String categoria;
	
    @JsonProperty(value = "description")
    private String descricao;
    
	@JsonProperty(value = "image")
	private String imagem;
}

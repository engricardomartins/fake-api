package com.javanautas.fakeapius.infrastructure.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.javanautas.fakeapius.apivi.dto.ProductsDTO;

@FeignClient(value = "fake-api", url = "${fake-api.url:#{null}}")
public interface FakeApiClient {
	
	@GetMapping("/products")
	List<ProductsDTO> buscaListaProdutos();
}

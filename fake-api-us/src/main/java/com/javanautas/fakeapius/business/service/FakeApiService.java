package com.javanautas.fakeapius.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javanautas.fakeapius.apivi.dto.ProductsDTO;
import com.javanautas.fakeapius.business.converter.ProdutoConverter;
import com.javanautas.fakeapius.infrastructure.client.FakeApiClient;

@Service
public class FakeApiService {

	private final FakeApiClient cliente;
	private final ProdutoService produtoService;
	private final ProdutoConverter converter;

	public FakeApiService(FakeApiClient cliente, ProdutoService produtoService, ProdutoConverter converter) {
		super();
		this.cliente = cliente;
		this.produtoService = produtoService;
		this.converter = converter;
	}

	public List<ProductsDTO> buscaProdutos() {
		
		try {
			
			List<ProductsDTO> dto = cliente.buscaListaProdutos();
			
			dto.forEach(produto -> {
						Boolean retorno = produtoService.existsPorNome(produto.getNome());
				
						if(retorno .equals(false)) {
							produtoService.salvaProdutos(converter.toEntity(produto));	
						}
					}
			);
			return produtoService.buscaTodosProdutos();
		}
		catch (Exception e) {
			
			throw new RuntimeException("Erro  ao buscar e gravar produtos no banco de dados");
			
		}
	}
}

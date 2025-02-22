package com.javanautas.fakeapius.apivi.dto;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javanautas.fakeapius.business.service.FakeApiService;
import com.javanautas.fakeapius.business.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/produtos")
@Tag(name = "fake-api")public class FakeApiController {

	private final FakeApiService service;
	private final ProdutoService produtoService;
	
	public FakeApiController(FakeApiService service, ProdutoService produtoService) {
		super();
		this.service = service;
		this.produtoService = produtoService;
	}

	
	private static Logger log = LoggerFactory.getLogger(FakeApiController.class);

	
	@Operation(summary = "Buscar produtos da Api e salvar no banco", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")
	})
	@PostMapping("/api")
	public ResponseEntity<List<ProductsDTO>> salvaProdutosApi() {
		
		log.info("*** FakeApiController -  Busca produtos da Api e salvar no banco ***");
		return ResponseEntity.ok(service.buscaProdutos());
	}
	
	
	
	@Operation(summary = "Salvar novos produtos", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao salvar produtos")
	})
	@PostMapping("/")
	public ResponseEntity<ProductsDTO> salvaProdutosApi(@RequestBody ProductsDTO dto) {
		
		log.info("*** FakeApiController - Salvar novos produtos ***");
		return ResponseEntity.ok(produtoService.salvaProdutoDTO(dto));
	}

	
	
	@Operation(summary = "Fazer update do produto", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao atualizar o produto")
	})
	@PutMapping("/")
	public ResponseEntity<ProductsDTO> updateProdutos(@RequestParam ("id") String id, @RequestBody ProductsDTO dto) {
		
		log.info("*** FakeApiController - Fazer update do produto ***");
		System.out.println("FakeApiController - O valor do preço em dto:" + id);
		System.out.println("FakeApiController - O valor do preço em dto:" + dto.getPreco());

		return ResponseEntity.ok(produtoService.updateProduto(id, dto));
	}

	
	
	@Operation(summary = "Fazer delete de um produto", method = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao deletar o produto")
	})
	@DeleteMapping("/")
	public ResponseEntity<Void> deletaProduto(@RequestParam ("nome") String nome) {
		
		log.info("*** FakeApiController - Fazer delete de um produtos ***");
		produtoService.deletaProduto(nome);
		return ResponseEntity.accepted().build();
	}


	
	@Operation(summary = "Busca todos os produtos", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")
	})
	@GetMapping("/api")
	public ResponseEntity<List<ProductsDTO>> buscaTodosProdutos() {
		
		log.info("*** FakeApiController - Busca todos os produtos ***");
		return ResponseEntity.ok(produtoService.buscaTodosProdutos());
	}
	

	
	@Operation(summary = "Busca produtos por nome", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")
	})
	@GetMapping("/{nome}")
	public ResponseEntity<ProductsDTO> buscaProdutoPorNOme(@PathVariable ("nome") String nome) {
		
		log.info("*** FakeApiController - Busca produtos por nome ***");
		return ResponseEntity.ok(produtoService.buscaProdutoPorNome(nome));
	}

}

package com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	//Autowired faz a injeção de dependencia automaticamente
	
	private PostagemRepository postagemRepository;
	
	//função Lambda
	//@PathVariable -> Identificar os parametros na URL // somente para requests
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		
		/*optional é uma classe que faz o encapsulamento
		 *  de um objeto quando esse obj nao pode ser nulo
		 *  evita o null pointer exception*/
		
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
		
		// select * from tb_postagens where id = input;
	}
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() { 
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Postagem> postPostagem (@Valid @RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
		
	}
	
//	@PutMapping
//	public ResponseEntity<Postagem> putPostagem (@Valid @RequestBody Postagem postagem) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
//
//	}
	
	@PutMapping
	public ResponseEntity<?> putPostagem (@Valid @RequestBody Postagem postagem) { 
		return postagemRepository.findById(postagem.getId())
				
				.map(resposta -> {
					postagemRepository.save(postagem);
					return ResponseEntity.status(HttpStatus.ACCEPTED).build();
				})
				.orElse(ResponseEntity.notFound().build());	
	}

	
//	@DeleteMapping("/{id}")
//	public void delete(@PathVariable long id) {
//		postagemRepository.deleteById(id);
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem (@PathVariable Long id) { 
		return postagemRepository.findById(id)
				
				.map(resposta -> {
					postagemRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());	
	}
	
	
}

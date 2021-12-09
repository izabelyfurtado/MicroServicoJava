package br.com.union.ms.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.union.ms.model.Unidade;
import br.com.union.ms.repository.UnidadeRepository;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

	@Autowired
	private UnidadeRepository unidadeRepository;

	@GetMapping
	public List<Unidade> listar() {

		return unidadeRepository.findAll();

	}

	@PostMapping
	public ResponseEntity<Unidade> criar(@Valid @RequestBody Unidade unidade, HttpServletResponse response) {

		Unidade unidadeSalva = unidadeRepository.save(unidade);

		return ResponseEntity.status(HttpStatus.CREATED).body(unidadeSalva);

	}

	@GetMapping("baiano/{id}")
	public ResponseEntity<Unidade> buscarPeloCodigo(@PathVariable Long id) {

		Optional<Unidade> unidade = this.unidadeRepository.findById(id);

		return unidade.isPresent() ? ResponseEntity.ok(unidade.get()) : ResponseEntity.notFound().build();
	}

}

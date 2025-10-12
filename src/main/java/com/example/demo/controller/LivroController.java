package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.service.LivroService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    @Transactional
    public ResponseEntity<CadastroLivroDtoResponse> cadastroLivro(@RequestBody CadastroLivroDtoRequest dto, UriComponentsBuilder uriComponentsBuilder){
        CadastroLivroDtoResponse response = livroService.criarLivro(dto);
        URI adress =  uriComponentsBuilder.path("/livros/{id}").buildAndExpand(dto.getAutor()).toUri();
        return ResponseEntity.created(adress).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ListarLivrosDto>> listar(@PageableDefault(size = 5, sort = {"titulo"})Pageable pageable){
        Page<ListarLivrosDto> page = livroService.listarLivros(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharLivroDto> detalharLivro(@PathVariable Long id){
        DetalharLivroDto response = livroService.detalharLivro(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/autor")
    public ResponseEntity<Page<ListarLivrosDto>> procurarPorAutor(
            @RequestParam String autor,
            @PageableDefault(size = 20, sort = "titulo") Pageable pageable) {

        Page<ListarLivrosDto> page = livroService.procurarAutor(autor, pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<AtualizarLivroDTO> atualizar(@RequestBody AtualizarLivroDTO dto){
        AtualizarLivroDTO response = livroService.atualizarLivro(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        livroService.excluirLivro(id);
        return ResponseEntity.noContent().build();
    }
}

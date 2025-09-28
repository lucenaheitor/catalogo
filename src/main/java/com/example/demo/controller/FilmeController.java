package com.example.demo.controller;


import com.example.demo.dto.CadastroLivroDtoRequest;
import com.example.demo.dto.CadastroLivroDtoResponse;
import com.example.demo.dto.DetalharLivroDto;
import com.example.demo.dto.ListarLivrosDto;
import com.example.demo.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class FilmeController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<CadastroLivroDtoResponse> cadastroLivro(@RequestBody CadastroLivroDtoRequest dto){
        CadastroLivroDtoResponse response = livroService.criarLivro(dto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ListarLivrosDto>> listar(@PageableDefault(size = 5, sort = {"nome"})Pageable pageable){
        Page<ListarLivrosDto> page = livroService.listarLivros(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharLivroDto> detalharLivro(@PathVariable Long id){
        DetalharLivroDto response = livroService.detalharLivro(id);
        return ResponseEntity.ok(response);
    }
}

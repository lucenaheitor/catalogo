package com.example.demo.service;


import com.example.demo.Config.LivroMapper;
import com.example.demo.dto.*;
import com.example.demo.entity.Livro;
import com.example.demo.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public CadastroLivroDtoResponse criarLivro(CadastroLivroDtoRequest dto) {
        Livro livro = livroMapper.toEntity(dto);
        livro = livroRepository.save(livro);
        return livroMapper.toCadastroDto(livro);
    }

    public Page<ListarLivrosDto> listarLivros(Pageable pageable){
        return livroRepository.findAll(pageable)
                .map(livroMapper::toListarDto);
    }

    public DetalharLivroDto detalharLivro(Long id) {
        Livro livro  = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return livroMapper.toDetalharDto(livro);
    }

    public DetalharLivroDto atualizarLivro(Long id, AtualizarLivroDTO dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        livroMapper.updateLivroFromDto(dto, livro);
        livroRepository.save(livro);
        return livroMapper.toDetalharDto(livro);
    }
}

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
        Livro livro = LivroMapper.INSTANCE.toEntity(dto);
        livro = livroRepository.save(livro);
        return LivroMapper.INSTANCE.toCadastroDto(livro);
    }

    public Page<ListarLivrosDto> listarLivros(Pageable pageable){
        return livroRepository.findAll(pageable)
                .map(LivroMapper.INSTANCE::toListarDto);
    }

    public DetalharLivroDto detalharLivro(Long id) {
        Livro livro  = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return LivroMapper.INSTANCE.toDetalharDto(livro);
    }

    public DetalharLivroDto atualizarLivro(Long id, AtualizarLivroDTO dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        LivroMapper.INSTANCE.updateLivroFromDto(dto, livro);
        livro = livroRepository.save(livro);
        return LivroMapper.INSTANCE.toDetalharDto(livro);
    }
}

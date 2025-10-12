package com.example.demo.service;


import com.example.demo.Config.LivroMapper;
import com.example.demo.dto.*;
import com.example.demo.entity.Livro;
import com.example.demo.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public CadastroLivroDtoResponse criarLivro(CadastroLivroDtoRequest dto) {
        Livro livro = livroMapper.toEntity(dto);
        livro = livroRepository.save(livro);
        return LivroMapper.INSTANCE.toCadastroDto(livro);
    }

    public Page<ListarLivrosDto> listarLivros(Pageable pageable){
        return livroRepository.findAll(pageable)
                .map(livroMapper::toListarDto);
    }

    public DetalharLivroDto detalharLivro(Long id) {
        Livro livro  = livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return LivroMapper.INSTANCE.toDetalharDto(livro);
    }

    public Page<ListarLivrosDto> procurarAutor(String autor, Pageable pageable) {
        String aux = (autor == null) ? "" : autor.trim();
        Page<Livro> page = livroRepository.findByAutor(aux, pageable);
        return page.map(livroMapper::toListarDto);
    }

    public AtualizarLivroDTO atualizarLivro(AtualizarLivroDTO dto) {
        Livro livro = livroRepository.getReferenceById(dto.getId());
        livroMapper.updateFromDto(dto, livro);
        livroRepository.save(livro);
        return LivroMapper.INSTANCE.toAtualizarDto(livro);
    }

    public void excluirLivro(Long id){
        livroRepository.deleteById(id);
    }
}

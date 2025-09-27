package com.example.demo.service;


import com.example.demo.Config.MapStructImpl;
import com.example.demo.dto.*;
import com.example.demo.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public CadastroLivroDtoResponse criarLivro(CadastroLivroDtoRequest dto) {
        Livro livro = MapStructImpl.INSTANCE.convertToEntity(dto);
         livro = livroRepository.save(livro);
        return MapStructImpl.INSTANCE.convertToDto(livro);
    }

    public Page<ListarLivrosDto> listarLivros(Pageable pageable){
        return livroRepository.findAll(pageable)
                .map(MapStructImpl.INSTANCE::convertToListarDto);
    }

    public DetalharLivroDto detalharLivro(UUID id) {
        Livro livro  = livroRepository.findAllById(id)
                .orElseThrow(()-> new RuntimeException("Livro não encontrado"));
        return MapStructImpl.INSTANCE.convertToDetalharDto(livro);
    }


}

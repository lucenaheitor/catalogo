package com.example.demo.Config;

import com.example.demo.dto.*;
import com.example.demo.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LivroMapper {

    LivroMapper INSTANCE = Mappers.getMapper(LivroMapper.class);

    Livro toEntity(CadastroLivroDtoRequest dto);
    CadastroLivroDtoResponse toCadastroDto(Livro livro);
    ListarLivrosDto toListarDto(Livro livro);
    DetalharLivroDto toDetalharDto(Livro livro);
    AtualizarLivroDTO toAtualizarDto(Livro livro);

    void updateFromDto(AtualizarLivroDTO dto, @MappingTarget Livro livro);

}

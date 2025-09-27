package com.example.demo.Config;

import com.example.demo.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructImpl {

    MapStructImpl INSTANCE = Mappers.getMapper(MapStructImpl.class);


    Livro convertToEntity(CadastroLivroDtoRequest livroDtoRequest);

    CadastroLivroDtoResponse convertToDto(Livro livro);

    ListarLivrosDto convertToListarDto(Livro livro);

    DetalharLivroDto convertToDetalharDto(Livro livro);

}

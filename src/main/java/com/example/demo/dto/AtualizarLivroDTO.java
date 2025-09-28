package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarLivroDTO {

    private String titulo;
    private String genero;
    private String autor;
    private Integer ano;
    private String sinopse;


}

package br.senai.sc.editoralivros.dto;

import br.senai.sc.editoralivros.model.entities.Autor;
import lombok.Getter;

@Getter
public class LivroDTO {

    private Long isbn;

    private String titulo;

    private Autor autor;

    private Integer qtdPaginas;
}
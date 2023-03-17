package br.senai.sc.editoralivros.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    AGUARDANDO_REVISAO("AGUARDANDO_REVISAO"),
    EM_REVISAO("EM_REVISAO"),
    APROVADO("APROVADO"),
    AGUARDANDO_EDICAO("AGUARDANDO_EDICAO"),
    REPROVADO("REPROVADO"),
    PUBLICADO("Publicado");
    String nome;
}

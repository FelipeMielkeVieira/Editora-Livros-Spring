package br.senai.sc.editoralivros.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_editoras")
@ToString
@EqualsAndHashCode
public class Editora {

    @Id
    @Column(length = 14, nullable = false, unique = true)
    private Long cnpj;

    @Column(nullable = false)
    private String nome;
}

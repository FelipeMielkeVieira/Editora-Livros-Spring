package br.senai.sc.editoralivros.model.entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "tb_pessoa")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class Pessoa {

    @Id
    @Column(length = 11, nullable = false, unique = true)
    private Long cpf;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String sobrenome;

    @Column(length = 150, nullable = false, unique = true)
    private String email;

    @Column(length = 25, nullable = false)
    private String senha;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 15, nullable = false)
    private Genero genero;

}

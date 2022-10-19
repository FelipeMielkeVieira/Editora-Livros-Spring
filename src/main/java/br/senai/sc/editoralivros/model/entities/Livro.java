package br.senai.sc.editoralivros.model.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tb_livros")
public class Livro {

    @Id
    @Column(length = 13, nullable = false, unique = true)
    private Long isbn;

    @Column(length = 50, nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "cpf_autor",nullable = false)
    private Autor autor;

    @Column(nullable = false)
    private Integer qtdPaginas;

    @ManyToOne
    @JoinColumn(name = "cpf_revisor")
    private Revisor revisor;

    @Column(length = 50)
    private Integer pagRevisadas;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "cpnj_editora")
    private Editora editora;
}

//package br.senai.sc.editoralivros.model.entities;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
//@Entity
//@Table(name = "tb_livros")
//public class Livro {
//
//    @Id
//    @Column(length = 13, nullable = false, unique = true)
//    private Integer isbn;
//
//    @Column(length = 50, nullable = false)
//    private String titulo;
//
//    @Column(nullable = false)
//    private Autor autor;
//
//    @Column(nullable = false)
//    private Integer qtdPaginas;
//
//    @Column
//    private Revisor revisor;
//
//    @Column(length = 50, nullable = false)
//    private Integer pagRevisadas;
//
//    @Column(nullable = false)
//    private Status status;
//
//    @Enumerated(value = EnumType.STRING)
//    @Column(nullable = false)
//    private Editora editora;
//}

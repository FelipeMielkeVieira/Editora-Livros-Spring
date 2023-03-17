package br.senai.sc.editoralivros.model.entities;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "tb_livro_autor",
            joinColumns = @JoinColumn(name = "isbn_livro", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "cpf_autor", nullable = false))
    private List<Autor> autores;

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

    @ManyToOne(cascade=CascadeType.ALL)
    private Arquivo arquivo;

    public void setArquivo(MultipartFile file) {
        try {
            this.arquivo = new Arquivo(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}

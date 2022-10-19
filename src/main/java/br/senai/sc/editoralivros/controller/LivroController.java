package br.senai.sc.editoralivros.controller;

import br.senai.sc.editoralivros.dto.LivroDTO;
import br.senai.sc.editoralivros.model.entities.Autor;
import br.senai.sc.editoralivros.model.entities.Livro;
import br.senai.sc.editoralivros.model.entities.Status;
import br.senai.sc.editoralivros.model.service.LivroService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/editoralivros/livro")
public class LivroController {

    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid LivroDTO livroDTO) {

        if (livroService.existsById(livroDTO.getIsbn())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Este ISBN já está cadastrado!");
        }

        Livro livro = new Livro();
        BeanUtils.copyProperties(livroDTO, livro);
        livro.setStatus(Status.AGUARDANDO_REVISAO);
        return ResponseEntity.status(HttpStatus.OK).body(livroService.save(livro));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Object> findById(@PathVariable(value = "isbn") Long isbn) {
        Optional<Livro> livroOptional = livroService.findById(isbn);

        if (livroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum livro com este ISBN!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(livroOptional.get());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Livro>> findByStatus(@PathVariable(value = "status") Status status) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.findByStatus(status));
    }

    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<Livro>> findByAutor(@PathVariable(value = "autor") Autor autor) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.findByAutor(autor));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.findAll());
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "isbn") Long isbn) {

        if (!livroService.existsById(isbn)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum livro com este ISBN!");
        }

        livroService.deleteById(isbn);
        return ResponseEntity.status(HttpStatus.OK).body("Livro deletado!");
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody @Valid LivroDTO livroDTO) {

        if (!livroService.existsById(livroDTO.getIsbn())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi encontrado nenhum livro com este ISBN!");
        }

        Livro livro = livroService.findById(livroDTO.getIsbn()).get();
        BeanUtils.copyProperties(livroDTO, livro);
        return ResponseEntity.status(HttpStatus.OK).body(livroService.save(livro));
    }
}

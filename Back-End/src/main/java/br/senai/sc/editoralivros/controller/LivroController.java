package br.senai.sc.editoralivros.controller;

import br.senai.sc.editoralivros.dto.LivroDTO;
import br.senai.sc.editoralivros.model.entities.Autor;
import br.senai.sc.editoralivros.model.entities.Livro;
import br.senai.sc.editoralivros.model.entities.Status;
import br.senai.sc.editoralivros.model.service.LivroService;
import br.senai.sc.editoralivros.util.LivroUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/editoralivros/livro")
public class LivroController {

    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestParam("livro") String livroJson,
                                       @RequestParam("arquivo") MultipartFile file) {

        LivroUtil util = new LivroUtil();
        Livro livro = util.convertJsonToModel(livroJson);

        if (livroService.existsById(livro.getIsbn())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Este ISBN já está cadastrado!");
        }

        livro.setArquivo(file);
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

    @GetMapping("/page")
    public ResponseEntity<Page<Livro>> findAllPage(
            @PageableDefault(page = 2, size = 18, sort = "isbn", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.findAll(pageable));
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

package br.senai.sc.editoralivros.util;

import br.senai.sc.editoralivros.dto.LivroDTO;
import br.senai.sc.editoralivros.model.entities.Livro;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.Valid;

public class LivroUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Livro convertJsonToModel(String livroJson) {
        LivroDTO livroDTO = convertJsonToDTO(livroJson);
        return convertDtoToModel(livroDTO);
    }

    private LivroDTO convertJsonToDTO(String livroJson) {
        try {
            return this.objectMapper.readValue(livroJson, LivroDTO.class);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private Livro convertDtoToModel(@Valid LivroDTO livroDTO) {
        return this.objectMapper.convertValue(livroDTO, Livro.class);
    }
}

package br.senai.sc.editoralivros.model.factory;

import br.senai.sc.editoralivros.model.entities.Autor;
import br.senai.sc.editoralivros.model.entities.Diretor;
import br.senai.sc.editoralivros.model.entities.Pessoa;
import br.senai.sc.editoralivros.model.entities.Revisor;

public class PessoaFactory {

    public Pessoa getPessoa(String tipoPessoa) {
        switch (tipoPessoa) {
            case "autor":
                return new Autor();
            case "revisor":
                return new Revisor();
            default:
                return new Diretor();
        }
    }
}

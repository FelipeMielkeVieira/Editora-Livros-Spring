package br.senai.sc.editoralivros.model.entities;

public class Editora {

    private String nome;

    public Editora(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static Editora cadastrar(String nome) {
        return new Editora(nome);
    }
}

package br.edu.br.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Pessoa implements Avaliavel{
    protected String nome;
    protected int id;
    protected List<String> avaliacoes = new ArrayList<>();

    public Pessoa(String nome){
        this.nome = nome;
    }
    public Pessoa(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvaliacoes(List<String> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public String toString() {
        return "Pessoa[" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ']';
    }

    @Override
    public void setAvaliacao(String avalicao) {
        avaliacoes.add(avalicao);
    }

    @Override
    public List<String> getAvaliacoes() {
        return avaliacoes;
    }
}
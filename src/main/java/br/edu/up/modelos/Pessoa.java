package br.edu.up.modelos;

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

    public Pessoa(String nome, int id, List<String> avaliacoes) {
        this.nome = nome;
        this.id = id;
        setAvaliacoes(avaliacoes);
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
    public void addAvaliacao(String avalicao) {
        avaliacoes.add(avalicao);
    }

    @Override
    public List<String> getAvaliacoes() {
        return avaliacoes;
    }

    public String getAvaliacoesString(){
        String todasAvaliacoes = "";

        for (int i = 0; i < avaliacoes.size(); i++) {

            if(avaliacoes.size() > i+1){
                todasAvaliacoes += avaliacoes.get(i) +", ";
            }else{
                todasAvaliacoes += avaliacoes.get(i);
            }
        }

        return  todasAvaliacoes;
    }
}
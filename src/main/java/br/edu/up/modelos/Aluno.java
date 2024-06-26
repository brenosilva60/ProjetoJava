package br.edu.up.modelos;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa{
    private TipoDoPlano tipoDoPlano;
    public enum TipoDoPlano{
        Mensal,
        Trimestral,
        Semestral,
        Anual
    }

    public TipoDoPlano getTipoDoPlano() {
        return tipoDoPlano;
    }

    public void setTipoDoPlano(TipoDoPlano tipoDoPlano) {
        this.tipoDoPlano = tipoDoPlano;
    }

    public Aluno(String nome) {
        super(nome);
    }
    public Aluno(String nome, TipoDoPlano tipoDoPlano) {
        super(nome);
        this.tipoDoPlano = tipoDoPlano;
    }
    public Aluno(String nome, int id) {
        super(nome, id);
    }
    public Aluno(String nome, int id, TipoDoPlano tipoDoPlano) {
        super(nome, id);
        this.tipoDoPlano = tipoDoPlano;
    }
    public Aluno(String nome, Integer id, TipoDoPlano tipoDoPlano, List<String> avaliacoes) {
        super(nome, id, avaliacoes);
        this.tipoDoPlano = tipoDoPlano;
    }
    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    @Override
    public String toString() {
        return "Aluno [nome=" + nome + ", id=" + id + ", plano: " + tipoDoPlano.toString() + "]";
    }

    public String toTxt(){
        String avaliacoes = "";

        for (int i = 0; i < this.avaliacoes.size(); i++) {
            if(this.avaliacoes.size() > i+1){
                avaliacoes = this.avaliacoes.get(i) + ", ";
            }else{
                avaliacoes = this.avaliacoes.get(i);
            }
        }

        return id + ";" + nome + ";" + tipoDoPlano.toString() + ";" + avaliacoes;
    }
}
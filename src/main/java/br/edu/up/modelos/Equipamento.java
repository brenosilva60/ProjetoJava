package br.edu.up.modelos;

public class Equipamento {
    private String nome;
    private int id;

    public Equipamento(String nome) {
        this.nome = nome;
    }

    public Equipamento(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Equipamento[" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ']';
    }
}
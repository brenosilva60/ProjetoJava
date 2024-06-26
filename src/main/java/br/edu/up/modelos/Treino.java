package br.edu.up.modelos;

public class Treino{

    private String nome;
    private String descricao;
    private int id;

    public Treino(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Treino(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Treino[" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", id=" + id +
                ']';
    }

    public String toTxt(){
        return id + ";" + nome + ";" + descricao;
    }
}
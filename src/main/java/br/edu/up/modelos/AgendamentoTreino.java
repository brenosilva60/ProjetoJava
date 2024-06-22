package br.edu.up.modelos;


import java.util.Date;

public class AgendamentoTreino implements Avaliavel {
    private Aluno aluno;
    private Treino treino;
    private Date data;
    private Instrutor instrutor;
    private String avaliacao;
    private int id;
    public AgendamentoTreino(Aluno aluno, Treino treino, Date data, Instrutor instrutor) {
        this.aluno = aluno;
        this.treino = treino;
        this.data = data;
        this.instrutor = instrutor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Treino getTreino() {
        return treino;
    }

    public Date getData() {
        return data;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AgendamentoTreino{" +
                "aluno=" + aluno +
                ", treino=" + treino +
                ", data=" + data +
                ", instrutor=" + instrutor +
                ", avaliacao='" + avaliacao + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public String getAvaliacao() {
        return avaliacao;
    }
}

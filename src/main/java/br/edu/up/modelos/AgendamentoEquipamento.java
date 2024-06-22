package br.edu.up.modelos;



import java.util.Date;

public class AgendamentoEquipamento implements Avaliavel {
    private Aluno aluno;
    private Equipamento equipamento;
    private Date data;
    protected String avaliacao;
    private int id;

    public AgendamentoEquipamento(Aluno aluno, Equipamento equipamento, Date data) {
        this.aluno = aluno;
        this.equipamento = equipamento;
        this.data = data;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    public Equipamento getEquipamento() {
        return equipamento;
    }
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "AgendamentoEquipamento[" +
                "aluno=" + aluno +
                ", equipamento=" + equipamento +
                ", data=" + data +
                ", avaliacao='" + avaliacao + '\'' +
                ", id=" + id +
                ']';
    }

    @Override
    public void setAvaliacao(String avalicao) {
        this.avaliacao = avalicao;
    }
    @Override
    public String getAvaliacao() {
        return avaliacao;
    }
}

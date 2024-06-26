package br.edu.up.modelos;

import br.edu.up.controles.AlunoController;
import br.edu.up.controles.EquipamentoController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Agendamento implements Avaliavel{
    private Integer id;
    private Aluno aluno;
    private LocalDateTime dataCriacaoAgendamento;
    private LocalDateTime dataDoTreino;
    protected Treino treino;
    protected List<Equipamento> equipamentos = new ArrayList<>();
    private String avaliacao;

    public void setId(Integer id) {
        this.id = id;
    }

    public Agendamento(Aluno aluno, LocalDateTime dataDoTreino, Treino treino){
        this.aluno = aluno;
        this.dataCriacaoAgendamento = LocalDateTime.now();
        this.dataDoTreino = dataDoTreino;
    }

    public Agendamento(int id, Aluno aluno, LocalDateTime dataDoTreino){
        this.id = id;
        this.aluno = aluno;
        this.dataCriacaoAgendamento = LocalDateTime.now();
        this.dataDoTreino = dataDoTreino;
    }

    public Agendamento(int id, Aluno aluno, LocalDateTime dataDoTreino, Treino treino){
        this.id = id;
        this.aluno = aluno;
        this.dataCriacaoAgendamento = LocalDateTime.now();
        this.dataDoTreino = dataDoTreino;
    }

    public Agendamento(Aluno aluno, LocalDateTime dataDoTreino, Treino treino, List<Equipamento> equipamentos){
        this.aluno = aluno;
        this.dataCriacaoAgendamento = LocalDateTime.now();
        this.dataDoTreino = dataDoTreino;
        this.treino = treino;
        this.equipamentos = equipamentos;
    }

    public Agendamento(Integer id, Aluno aluno, LocalDateTime dataCriacaoAgendamento, LocalDateTime dataDoTreino, Treino treino, List<Equipamento> equipamentos, String avaliacao){
        this.id = id;
        this.dataCriacaoAgendamento = dataCriacaoAgendamento;
        this.aluno = aluno;
        this.dataDoTreino = dataDoTreino;
        this.treino = treino;
        this.equipamentos = equipamentos;
        this.avaliacao = avaliacao;
    }

    public Integer getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public LocalDateTime getDataDoTreino() {
        return dataDoTreino;
    }

    public void setDataDoTreino(LocalDateTime dataDoTreino) {
        this.dataDoTreino = dataDoTreino;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreinos(Treino treino) {
        this.treino = treino;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    @Override
    public void addAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }
    @Override
    public String getAvaliacao() {
        return avaliacao;
    }

    @Override
    public String toString() {
        String equipamentos = "";

        for(Equipamento equipamento : this.equipamentos){
            equipamentos += "\t\t" + equipamento.getNome() + ",\n";
        }

        return "Agendamento{\n" +
                "\tid: " + id + "\n" +
                "\taluno: " + aluno.getNome() + "\n" +
                "\tdata Criacao Agendamento: " + dataCriacaoAgendamento  + "\n" +
                "\tdata Do Treino: " + dataDoTreino + "\n" +
                "\ttreino: " + treino.getNome()  + "\n" +
                "\tdescricao: " + treino.getDescricao() + "\n" +
                "\tavaliacao: " + avaliacao + "\n" +
                "\tequipamentos:[\n" + equipamentos + "\t]"  + "\n" +
                '}';
    }

    public String toTxt(){
        String equipamentos = "";

        for(Equipamento equipamento : this.equipamentos){
            equipamentos += equipamento.getId() + ",";
        }

        return id + ";" +
                aluno.getId() + ";" +
                dataCriacaoAgendamento.toString() + ";" +
                dataDoTreino.toString() + ";" +
                treino.getId() + ";" +
                avaliacao + ";" +
                equipamentos;

    }
}

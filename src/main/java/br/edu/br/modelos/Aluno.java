package br.edu.br.modelos;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa{

    private List<AgendamentoTreino> historicoTreinos = new ArrayList<>();
    private List<AgendamentoEquipamento> historicoEquipamentos = new ArrayList<>();

    public Aluno(String nome, int id) {
        super(nome, id);
    }

    public Aluno(String nome) {
        super(nome);
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

    public List<AgendamentoTreino> getHistoricoTreinos() {
        return historicoTreinos;
    }

    public List<AgendamentoEquipamento> getHistoricoEquipamentos() {
        return historicoEquipamentos;
    }

    public void adicionarTreino(AgendamentoTreino agendamentoTreino) {
        historicoTreinos.add(agendamentoTreino);
    }

    public void adicionarEquipamento(AgendamentoEquipamento agendamentoEquipamento) {
        historicoEquipamentos.add(agendamentoEquipamento);
    }

    @Override
    public String toString() {
        return "Aluno [nome=" + nome + ", id=" + id + "]";
    }
}
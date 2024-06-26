package br.edu.up.controles;

import br.edu.up.DAO.AgendamentoDAO;
import br.edu.up.DAO.AlunoDAO;
import br.edu.up.modelos.Agendamento;
import br.edu.up.modelos.Aluno;
import br.edu.up.modelos.Equipamento;
import br.edu.up.modelos.Treino;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendamentoController {
    private static final Logger logger = LogManager.getLogger();
    private AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
    List<Agendamento> agendamentos = new ArrayList<>();
    private int id;

    public AgendamentoController(){
        id = 1;

        this.agendamentos = agendamentoDAO.getAgendamentos();

        for (Agendamento agendamento : this.agendamentos) {
            if (agendamento.getId() >= id) {
                id = agendamento.getId() + 1;
            }
        }
    }
    public int getUltimoId(){
        return id-1;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
        agendamentoDAO.gravarArquivo();
    }


    public boolean agendar(Agendamento agendamento){
        agendamento.setId(id++);

        if(agendamento.getDataDoTreino().isAfter(LocalDateTime.now())){
            agendamentos.add(agendamento);
            agendamentoDAO.gravarArquivo();
            return true;
        }

        return false;
    }

    public boolean cancelarAgendamento(int idAgendamento){
        for(Agendamento agendamento : agendamentos){
            if(agendamento.getId() == idAgendamento){
                agendamentos.remove(agendamento);
                agendamentoDAO.gravarArquivo();
                return true;
            }
        }

        return false;
    }
}

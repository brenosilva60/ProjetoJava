package br.edu.up.controles;

import br.edu.up.DAO.AlunoDAO;
import br.edu.up.modelos.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlunoController {
    private static final Logger logger = LogManager.getLogger();
    private AlunoDAO alunoDAO = new AlunoDAO();
    private List<Aluno> alunos = new ArrayList<>();
    private int alunoId;

    public AlunoController(){
        alunoId = 1;
        this.alunos = alunoDAO.getAlunos();

        for (Aluno aluno : this.alunos) {
            if (aluno.getId() >= alunoId) {
                alunoId = aluno.getId() + 1;
            }
        }
    }

    public int adicionar(Aluno aluno) {
        aluno.setId(alunoId);

        alunos.add(aluno);
        alunoDAO.gravarArquivo();
        logger.info("Aluno adicionado: " + aluno.toString());

        return  alunoId++;
    }
    public List<Aluno> listar() {
        return new ArrayList<>(alunos);
    }

    public Aluno pesquisar(int id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                return aluno;
            }
        }

        return null;
    }

    public List<Aluno> pesquisar(String nome) {
        List<Aluno> alunosPesquisa = new ArrayList<>();

        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nome)) {
                alunosPesquisa.add(aluno);

            }
        }

        return alunosPesquisa;
    }

    public Aluno alterar(int id, Aluno alunoAlterado){
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId() == id) {
                alunos.set(i, alunoAlterado);
                alunoDAO.gravarArquivo();
                logger.info("Aluno alterado: " + alunoAlterado.toString());

                return alunos.get(i);
            }
        }

        logger.error("Erro! Aluno não alterado: " + alunoAlterado.toString());

        return null;
    }

    public boolean remover(int id) {
        Aluno aluno = pesquisar(id);

        if (aluno != null) {
            if(alunos.remove(aluno)){
                logger.info("Aluno removido: " + aluno.toString());
                alunoDAO.gravarArquivo();
                return true;
            }else{
                logger.error("FALHA ao remover aluno (id): " + id);
                return false;
            }
        } else {
            return false;
        }
    }

    public List<Agendamento> getAgendamentos(int idAluno) {
        AgendamentoController agendamentoController = new AgendamentoController ();
        Aluno aluno = pesquisar(idAluno);

        if (aluno != null) {
            List<Agendamento> agendamentosDoAluno = new ArrayList<>();

            for(Agendamento agendamento : agendamentoController.getAgendamentos()){
                if(agendamento.getAluno().getId() == idAluno){
                    agendamentosDoAluno.add(agendamento);
                }
            }

            return agendamentosDoAluno;
        } else {
            return null;
        }
    }
}

package br.edu.up.controles;


import br.edu.up.modelos.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SistemaAcademia {
    private List<Aluno>  alunos = new ArrayList<>();
    private int alunoId;
    private List<Treino> treinos = new ArrayList<>();
    private int treinoId;
    private List<Equipamento> equipamentos = new ArrayList<>();
    private int equipamentoId;
    private List<Instrutor> instrutores = new ArrayList<>();
    private int instrutorId;
    private static final Logger logger = LogManager.getLogger();

    public SistemaAcademia(){
        alunoId = 1;
        treinoId = 1;
        equipamentoId = 1;
        instrutorId = 1;


    }

    public int adicionarAluno(Aluno aluno) {
        aluno.setId(alunoId);
        alunoId++;

        alunos.add(aluno);
        logger.info("Aluno adicionado: " + aluno.toString());

        return  alunoId-1;
    }

    public int adicionarTreino(Treino treino) {
        treino.setId(treinoId);
        treinoId++;

        treinos.add(treino);
        logger.info("Treino adicionado: " + treino.toString());

        return  treinoId-1;
    }

    public int adicionarEquipamento(Equipamento equipamento) {
        equipamento.setId(equipamentoId);
        equipamentoId++;

        equipamentos.add(equipamento);
        logger.info("Equipamento adicionado: " + equipamento.toString());

        return  equipamentoId-1;
    }

    public int adicionarInstrutor(Instrutor instrutor) {
        instrutor.setId(instrutorId);
        instrutorId++;

        instrutores.add(instrutor);
        logger.info("Instrutor adicionado: " + instrutor.toString());

        return  instrutorId-1;
    }

    public void removerAluno(int id) {
        Aluno aluno = pesquisarAluno(id);

        if (aluno != null) {
            if(alunos.remove(aluno)){
                logger.info("Aluno removido: " + aluno.toString());
            }else{
                logger.error("FALHA ao remover aluno (id): " + id);
            }
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public void removerTreino(String nome) {
        Treino treino = pesquisarTreino(nome);

        if (treino != null) {
            if(treinos.remove(treino)){
                logger.info("Treino removido: " + treino.toString());
            }else{
                logger.error("FALHA ao remover treino: " + nome);
            }
        } else {
            System.out.println("Treino não encontrado.");
        }
    }

    public void removerEquipamento(int id) {
        Equipamento equipamento = pesquisarEquipamento(id);

        if (equipamento != null) {
            if(equipamentos.remove(equipamento)){
                logger.info("Equipamento removido: " + equipamento.toString());
            }else{
                logger.error("FALHA ao remover equipamento (id): " + id);
            }
        } else {
            System.out.println("Equipamento não encontrado.");
        }
    }

    public void removerInstrutor(int id) {
        Instrutor instrutor = pesquisarInstrutor(id);

        if (instrutor != null) {
            if(instrutores.remove(instrutor)){
                logger.info("Instrutor removido: " + instrutor.toString());
            }else{
                logger.error("FALHA ao remover equipamento (id): " + id);
            }
        } else {
            System.out.println("Instrutor não encontrado.");
        }
    }

    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos);
    }

    public List<Treino> listarTreinos() {
        return new ArrayList<>(treinos);
    }

    public List<Equipamento> listarEquipamentos() {
        return new ArrayList<>(equipamentos);
    }

    public List<Instrutor> listarInstrutores() {
        return new ArrayList<>(instrutores);
    }

    public Aluno pesquisarAluno(int id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                return aluno;
            }
        }
        return null;
    }

    public Treino pesquisarTreino(String nome) {
        for (Treino treino : treinos) {
            if (treino.getNome().equalsIgnoreCase(nome)) {
                return treino;
            }
        }

        return null;
    }

    public Equipamento pesquisarEquipamento(int id) {
        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getId() == id) {
                return equipamento;
            }
        }

        return null;
    }

    public Instrutor pesquisarInstrutor(int id) {
        for (Instrutor instrutor : instrutores) {
            if (instrutor.getId() == id) {
                return instrutor;
            }
        }

        return null;
    }

    public void agendarTreino(int idAluno, String nomeTreino, Date data, int idInstrutor) {
        Aluno aluno = pesquisarAluno(idAluno);
        Treino treino = pesquisarTreino(nomeTreino);
        Instrutor instrutor = pesquisarInstrutor(idInstrutor);

        if (aluno != null && treino != null && instrutor != null) {
            if (data.after(new Date())) {
                aluno.adicionarTreino(new AgendamentoTreino(aluno, treino, data, instrutor));
                logger.info("Treino agendado para o aluno: " + aluno.getNome() + " - " + treino.getNome() + " - " + data + " - Instrutor: " + instrutor.getNome());
                System.out.println("Treino agendado com sucesso para o aluno: " + aluno.getNome());
            } else {
                System.out.println("Data inválida. O treino deve ser agendado para o futuro.");
            }
        } else {
            System.out.println("Aluno, treino ou instrutor não encontrado.");
        }
    }

    public void reservarEquipamento(int idAluno, int idEquipamento, Date data) {
        Aluno aluno = pesquisarAluno(idAluno);
        Equipamento equipamento = pesquisarEquipamento(idEquipamento);
        if (aluno != null && equipamento != null) {
            aluno.adicionarEquipamento(new AgendamentoEquipamento(aluno, equipamento, data));
            logger.info("Equipamento reservado para o aluno: " + aluno.getNome() + " - " + equipamento.getNome() + " - " + data);
            System.out.println("Equipamento reservado com sucesso para o aluno: " + aluno.getNome());
        } else {
            System.out.println("Aluno ou equipamento não encontrado.");
        }
    }

    public void verificarHistorico(int idAluno) {
        Aluno aluno = pesquisarAluno(idAluno);
        if (aluno != null) {
            System.out.println("Histórico de treinos do aluno " + aluno.getNome() + ":");
            for (AgendamentoTreino treino : aluno.getHistoricoTreinos()) {
                System.out.println(treino);
            }
            System.out.println("Histórico de equipamentos reservados pelo aluno " + aluno.getNome() + ":");
            for (AgendamentoEquipamento equipamento : aluno.getHistoricoEquipamentos()) {
                System.out.println(equipamento);
            }
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public void salvarEFechar() {
        // Implementar lógica para salvar os dados e fechar o programa
        // Por exemplo, salvar os dados em arquivos serializados
    }


}

package br.edu.up.controles;

import br.edu.up.DAO.AlunoDAO;
import br.edu.up.DAO.EquipamentoDao;
import br.edu.up.modelos.Aluno;
import br.edu.up.modelos.Equipamento;
import br.edu.up.modelos.Instrutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EquipamentoController {
    private static final Logger logger = LogManager.getLogger();
    private EquipamentoDao equipamentoDao = new EquipamentoDao();
    private List<Equipamento> equipamentos = new ArrayList<>();
    private int equipamentoId;

    public EquipamentoController(){
        equipamentoId = 1;

        this.equipamentos = equipamentoDao.getEquipamento();

        for (Equipamento equipamento : this.equipamentos) {
            if (equipamento.getId() >= equipamentoId) {
                equipamentoId = equipamento.getId() + 1;
            }
        }
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public int adicionar(Equipamento equipamento) {
        equipamento.setId(equipamentoId);
        equipamentoId++;

        equipamentos.add(equipamento);
        equipamentoDao.gravarArquivo();
        logger.info("Equipamento adicionado: " + equipamento.toString());

        return  equipamentoId-1;
    }

    public List<Equipamento> listar() {
        return new ArrayList<>(equipamentos);
    }

    public Equipamento pesquisar(int id) {
        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getId() == id) {
                return equipamento;
            }
        }

        return null;
    }

    public Equipamento alterar(int id, Equipamento equipamentoAlterado){
        for (int i = 0; i < equipamentos.size(); i++) {
            if (equipamentos.get(i).getId() == id) {
                equipamentos.set(i, equipamentoAlterado);
                equipamentoDao.gravarArquivo();

                return equipamentos.get(i);
            }
        }

        return null;
    }

    public boolean remover(int id) {
        Equipamento equipamento = pesquisar(id);

        if (equipamento != null) {
            if(equipamentos.remove(equipamento)){
                equipamentoDao.gravarArquivo();
                logger.info("Equipamento removido: " + equipamento.toString());

                return true;
            }else{
                logger.error("FALHA ao remover equipamento (id): " + id);

                return false;
            }
        } else {
            return false;
        }
    }
}

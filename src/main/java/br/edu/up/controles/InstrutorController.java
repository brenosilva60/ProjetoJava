package br.edu.up.controles;

import br.edu.up.DAO.InstrutorDAO;
import br.edu.up.modelos.Instrutor;
import br.edu.up.modelos.Treino;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class InstrutorController {
    private static final Logger logger = LogManager.getLogger();
    private InstrutorDAO instrutorDAO = new InstrutorDAO();
    private List<Instrutor> instrutores = new ArrayList<>();
    private int instrutorId;

    public InstrutorController(){
        instrutorId = 1;

        this.instrutores = instrutorDAO.getInstrutores();

        for (Instrutor instrutor : this.instrutores) {
            if (instrutor.getId() >= instrutorId) {
                instrutorId = instrutor.getId() + 1;
            }
        }
    }

    public int adicionar(Instrutor instrutor) {
        instrutor.setId(instrutorId++);

        instrutores.add(instrutor);
        instrutorDAO.gravarArquivo();
        logger.info("Instrutor adicionado: " + instrutor.toString());

        return  instrutorId-1;
    }

    public List<Instrutor> listar() {
        return new ArrayList<>(instrutores);
    }

    public Instrutor pesquisar(int id) {
        for (Instrutor instrutor : instrutores) {
            if (instrutor.getId() == id) {
                return instrutor;
            }
        }

        return null;
    }

    public Instrutor alterar(int id, Instrutor instrutorAlterado){
        for (int i = 0; i < instrutores.size(); i++) {
            if (instrutores.get(i).getId() == id) {
                instrutores.set(i, instrutorAlterado);
                instrutorDAO.gravarArquivo();

                return instrutores.get(i);
            }
        }

        return null;
    }

    public boolean remover(int id) {
        Instrutor instrutor = pesquisar(id);

        if (instrutor != null) {
            if(instrutores.remove(instrutor)){
                instrutorDAO.gravarArquivo();
                logger.info("Instrutor removido: " + instrutor.toString());

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

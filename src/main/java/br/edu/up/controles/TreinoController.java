package br.edu.up.controles;

import br.edu.up.DAO.TreinosDAO;
import br.edu.up.modelos.Treino;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TreinoController {
    private static final Logger logger = LogManager.getLogger();
    private TreinosDAO treinosDAO = new TreinosDAO();
    private List<Treino> treinos = new ArrayList<>();
    private int treinoId;

    public TreinoController(){
        treinoId = 1;

        this.treinos = treinosDAO.getTreinos();

        for (Treino treino : this.treinos) {
            if (treino.getId() >= treinoId) {
                treinoId = treino.getId() + 1;
            }
        }
    }

    public int adicionar(Treino treino) {
        treino.setId(treinoId);

        treinos.add(treino);
        logger.info("Treino adicionado: " + treino.toString());
        treinosDAO.gravarArquivo();

        return  treinoId++;
    }

    public List<Treino> listar() {
        return new ArrayList<>(treinos);
    }

    public Treino pesquisar(int id) {
        for (Treino treino : treinos) {
            if (treino.getId() == id) {
                return treino;
            }
        }

        return null;
    }

    public Treino pesquisar(String nome) {
        for (Treino treino : treinos) {
            if (treino.getNome().equalsIgnoreCase(nome)) {
                return treino;
            }
        }

        return null;
    }

    public Treino alterar(int id, Treino treinoAlterado){
        for (int i = 0; i < treinos.size(); i++) {
            if (treinos.get(i).getId() == id) {
                treinos.set(i, treinoAlterado);
                treinosDAO.gravarArquivo();
                logger.info("Treino alterado: " + treinos.get(i));

                return treinos.get(i);
            }
        }

        return null;
    }

    public boolean remover(Treino treino) {

        if(treinos.remove(treino)){
            logger.info("Treino removido: " + treino.toString());
            treinosDAO.gravarArquivo();

            return  true;
        }else{
            logger.error("FALHA ao remover treino: " + treino.getNome());
            return false;
        }
    }
}

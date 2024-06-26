package br.edu.up.modelos;

import java.util.List;

public interface Avaliavel {
    public void addAvaliacao(String avaliacao);
    public default List<String> getAvaliacoes(){
        return null;
    };
    public default String getAvaliacao(){
        return null;
    }
}

package br.edu.br.modelos;

import java.util.List;

public interface Avaliavel {
    public void setAvaliacao(String avaliacao);
    public default List<String> getAvaliacoes(){
        return null;
    };
    public default String getAvaliacao(){
        return null;
    }
}

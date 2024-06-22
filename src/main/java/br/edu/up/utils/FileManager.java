package br.edu.up.utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public void registrarOperacao(String operacao, String nomeArquivo) {
        try {
            FileWriter writer = new FileWriter(nomeArquivo, true);
            writer.write(operacao + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

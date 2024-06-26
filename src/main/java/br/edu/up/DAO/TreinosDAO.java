package br.edu.up.DAO;

import br.edu.up.modelos.Equipamento;
import br.edu.up.modelos.Treino;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreinosDAO {
    private String header = "id;nome;descricao";
    private String nomeDoArquivo;
    List<Treino> treinos = new ArrayList<>();

    public TreinosDAO() {
        String path = new File("").getAbsolutePath() + "\\src\\main\\java\\br\\edu\\up\\DAO\\dados\\";
        nomeDoArquivo = path + "treinos.txt";
    }

    public List<Treino> getTreinos() {

        try {
            File arquivoLeitura = new File(nomeDoArquivo);
            Scanner leitor = new Scanner(arquivoLeitura);

            if (leitor.hasNextLine()) {
                header = leitor.nextLine();
            }


            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");

                Integer id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String descricao = dados[2];

                Treino treino = new Treino(id, nome, descricao);

                treinos.add(treino);
            }

            leitor.close();
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado (Treino)!");
        }

        return treinos;
    }

    public void gravarArquivo() {
        try {
            FileWriter arquivoGravar = new FileWriter(nomeDoArquivo);
            PrintWriter gravador = new PrintWriter(arquivoGravar);
            gravador.println(header);

            for (Treino treino : treinos) {
                gravador.println(treino.toTxt());
            }

            gravador.close();
        } catch (IOException e) {
            System.out.println("Não foi possível gravar o arquivo!");
        }
    }
}

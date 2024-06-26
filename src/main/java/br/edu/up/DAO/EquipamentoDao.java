package br.edu.up.DAO;

import br.edu.up.modelos.Aluno;
import br.edu.up.modelos.Equipamento;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EquipamentoDao {
    private String header = "id;nome";
    private String nomeDoArquivo;
    List<Equipamento> equipamentos = new ArrayList<>();

    public EquipamentoDao() {
        String path = new File("").getAbsolutePath() + "\\src\\main\\java\\br\\edu\\up\\DAO\\dados\\";
        nomeDoArquivo = path + "equipamento.txt";
    }

    public List<Equipamento> getEquipamento() {

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

                Equipamento equipamento = new Equipamento(nome, id);

                equipamentos.add(equipamento);
            }

            leitor.close();
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado (Equipamento)!");
        }

        return equipamentos;
    }

    public void gravarArquivo() {
        try {
            FileWriter arquivoGravar = new FileWriter(nomeDoArquivo);
            PrintWriter gravador = new PrintWriter(arquivoGravar);
            gravador.println(header);

            for (Equipamento equipamento : equipamentos) {
                gravador.println(equipamento.toTxt());
            }

            gravador.close();
        } catch (IOException e) {
            System.out.println("Não foi possível gravar o arquivo!");
        }
    }
}

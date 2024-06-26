package br.edu.up.DAO;

import br.edu.up.modelos.Equipamento;
import br.edu.up.modelos.Instrutor;
import br.edu.up.modelos.Treino;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InstrutorDAO {
    private String header = "id;nome;avaliacoes;salario;email;dataAdmissao";
    private String nomeDoArquivo;
    List<Instrutor> instrutores = new ArrayList<>();

    public InstrutorDAO() {
        String path = new File("").getAbsolutePath() + "\\src\\main\\java\\br\\edu\\up\\DAO\\dados\\";
        nomeDoArquivo = path + "instrutores.txt";
    }

    public List<Instrutor> getInstrutores() {

        try {
            File arquivoLeitura = new File(nomeDoArquivo);
            Scanner leitor = new Scanner(arquivoLeitura);

            if (leitor.hasNextLine()) {
                header = leitor.nextLine();
            }


            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");

                //"id;nome;avaliacoes;salario;email;dataAdmissao"

                Integer id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                List<String> avaliacoes = List.of(dados[2].split(","));
                Double salario = Double.parseDouble(dados[3]);
                String email = dados[4];
                LocalDateTime dataAdmissao = LocalDateTime.parse(dados[5]);

                Instrutor instrutor = new Instrutor(id, nome,avaliacoes, salario, email, dataAdmissao);

                instrutores.add(instrutor);
            }

            leitor.close();
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado (instrutores.txt)!");
        }

        return instrutores;
    }

    public void gravarArquivo() {
        try {
            FileWriter arquivoGravar = new FileWriter(nomeDoArquivo);
            PrintWriter gravador = new PrintWriter(arquivoGravar);
            gravador.println(header);

            for (Instrutor instrutor : instrutores) {
                gravador.println(instrutor.toTxt());
            }

            gravador.close();
        } catch (IOException e) {
            System.out.println("Não foi possível gravar o arquivo!");
        }
    }
}

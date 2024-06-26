package br.edu.up.DAO;

import br.edu.up.modelos.Aluno;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AlunoDAO {
    private String header = "id;nome;tipoDoPlano;avaliacoes";
    private String nomeDoArquivo;
    List<Aluno> alunos = new ArrayList<>();

    public AlunoDAO() {
        String path = new File("").getAbsolutePath() + "\\src\\main\\java\\br\\edu\\up\\DAO\\dados\\";
        nomeDoArquivo = path + "alunos.txt";
    }

    public List<Aluno> getAlunos() {

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
                Aluno.TipoDoPlano tipoDoPlano = Aluno.TipoDoPlano.valueOf(dados[2]);
                List<String> avaliacoes = new ArrayList<>();

                if(dados.length == 4){
                    avaliacoes = List.of(dados[3].split(","));
                }

                Aluno aluno = new Aluno(nome, id, tipoDoPlano, avaliacoes);

                alunos.add(aluno);
            }

            leitor.close();
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado (Aluno)!");
        }

        return alunos;
    }

    public void gravarArquivo() {
        try {
            FileWriter arquivoGravar = new FileWriter(nomeDoArquivo);
            PrintWriter gravador = new PrintWriter(arquivoGravar);
            gravador.println(header);

            for (Aluno aluno : alunos) {
                gravador.println(aluno.toTxt());
            }

            gravador.close();
        } catch (IOException e) {
            System.out.println("Não foi possível gravar o arquivo!");
        }
    }
}

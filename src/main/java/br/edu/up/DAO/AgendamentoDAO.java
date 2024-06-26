package br.edu.up.DAO;

import br.edu.up.controles.AlunoController;
import br.edu.up.controles.EquipamentoController;
import br.edu.up.controles.TreinoController;
import br.edu.up.modelos.Agendamento;
import br.edu.up.modelos.Aluno;
import br.edu.up.modelos.Equipamento;
import br.edu.up.modelos.Treino;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgendamentoDAO {
    private String header = "id;idAluno;dataCriacaoAgendamento;dataDoTreino;idTreino;avaliacao;idEquipamentos";
    private String nomeDoArquivo;
    List<Agendamento> agendamentos = new ArrayList<>();

    public AgendamentoDAO() {
        String path = new File("").getAbsolutePath() + "\\src\\main\\java\\br\\edu\\up\\DAO\\dados\\";

        nomeDoArquivo = path + "agendamentos.txt";
    }

    public List<Agendamento> getAgendamentos() {

        try {
            File arquivoLeitura = new File(nomeDoArquivo);
            Scanner leitor = new Scanner(arquivoLeitura);

            if (leitor.hasNextLine()) {
                header = leitor.nextLine();
            }

            while (leitor.hasNextLine()) {
                EquipamentoController equipamentoController = new EquipamentoController();
                AlunoController alunoController = new AlunoController();
                TreinoController treinoController = new TreinoController();

                String linha = leitor.nextLine();
                String[] dados = linha.split(";");

                Integer id = Integer.parseInt(dados[0]);

                Integer idAluno = Integer.parseInt(dados[1]);
                Aluno aluno = alunoController.pesquisar(idAluno);

                LocalDateTime dataCriacaoAgendamento = LocalDateTime.parse(dados[2]);
                LocalDateTime dataDoTreino = LocalDateTime.parse(dados[3]);

                Integer idTreino = Integer.parseInt(dados[4]);
                Treino treino = treinoController.pesquisar(idTreino);

                String avaliacao = dados[5];

                List<Equipamento> equipamentos = new ArrayList<>();

                if(dados.length == 7){
                    String[] equipamentosArray = dados[6].split(",");

                    for (int i = 0; i < equipamentosArray.length; i++) {
                        Integer idEquipamento = Integer.parseInt(equipamentosArray[i]);
                        Equipamento equipamento = equipamentoController.pesquisar(idEquipamento);

                        equipamentos.add(equipamento);
                    }
                }

                Agendamento agendamento = new Agendamento(id, aluno, dataCriacaoAgendamento, dataDoTreino, treino, equipamentos, avaliacao);

                agendamentos.add(agendamento);
            }

            leitor.close();
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado (Agendamento)!");
        }

        return agendamentos;
    }

    public void gravarArquivo() {
        try {
            FileWriter arquivoGravar = new FileWriter(nomeDoArquivo);
            PrintWriter gravador = new PrintWriter(arquivoGravar);
            gravador.println(header);

            for (Agendamento agendamento : agendamentos) {
                gravador.println(agendamento.toTxt());
            }

            gravador.close();
        } catch (IOException e) {
            System.out.println("Não foi possível gravar o arquivo!");
        }
    }
}

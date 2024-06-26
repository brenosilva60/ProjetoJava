package br.edu.up.views.agendamento;

import br.edu.up.controles.*;
import br.edu.up.modelos.Agendamento;
import br.edu.up.modelos.Aluno;
import br.edu.up.modelos.Equipamento;
import br.edu.up.modelos.Treino;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgendamentoCriacaoView {
    AgendamentoController agendamentoController = new AgendamentoController();
    AlunoController alunoController = new AlunoController();
    EquipamentoController equipamentoController = new EquipamentoController();
    TreinoController treinoController = new TreinoController();
    private Scanner scanner = new Scanner(System.in);

    public void mostrar(){
        if(treinoController.listar().isEmpty() && alunoController.listar().isEmpty() && equipamentoController.listar().isEmpty()){
            System.out.println("Adicione alunos, treinos ou equipamentos antes de agendar!");
        }else{
            Aluno aluno = escolherAluno();
            Treino treino = escolherTreino();

            LocalDate data = escolherData();
            LocalTime horario = escolherHorario();
            LocalDateTime dataHorarario = LocalDateTime.of(data, horario);

            List<Equipamento> equipamentos = escolherEquipamentos();

            Agendamento agendamento = new Agendamento(aluno, dataHorarario, treino, equipamentos);
            agendamentoController.agendar(agendamento);

            System.out.println("Agendado com sucesso!");
        }
    }

    public Treino escolherTreino() {
        List<Treino> treinos = treinoController.listar();

        do {
            int contador = 1;
            System.out.println("Treino Disponiveis:");

            for (Treino treino : treinos) {
                System.out.println((contador++) + "- " + treino.getNome());
            }

            System.out.println("Digite qual treino deseja:");
            Integer treinoIndex = scanner.nextInt();
            scanner.nextLine();

            Treino treino =  null;

            if(treinoIndex >= 1 && treinoIndex <= treinoController.listar().size()){
                treino = treinoController.listar().get(treinoIndex-1);
            }

            if(treino == null){
                System.out.println("Digitado incorretamente! Digite novamente:");
                System.out.println("Pressione enter pra continuar...");
                scanner.nextLine();
            }else{
                return  treino;
            }
        }while(true);
    }

    public Aluno escolherAluno(){
        do{
            System.out.println("Digite o id do aluno:");
            Integer id = scanner.nextInt();

            Aluno aluno = alunoController.pesquisar(id);

            if(aluno == null){
                System.out.println("Nenhum aluno econtrado com esse id! Digite novamente");
                System.out.println("Pressione enter pra continuar...");
                scanner.nextLine();
            }else{
                return  aluno;
            }
        }while(true);
    }

    public LocalDate escolherData(){
        do {
            System.out.println("Digite o dia que você deseja agendar (dd/mm/aaaa):");
            String[] data =  (scanner.nextLine()).split("/");
            try{
                if(data.length == 3){
                    int dia = Integer.parseInt(data[0]);
                    int mes = Integer.parseInt(data[1]);
                    int ano = Integer.parseInt(data[2]);

                    LocalDate dataEscolhida = LocalDate.of(ano, mes, dia);

                    if(dataEscolhida.isAfter(LocalDate.now())){
                        return dataEscolhida;
                    }else{
                        erroData("Você só pode reservar apenas um dia depois!");
                    }
                }else{
                    erroData("Formato da data inválido! (dd/mm/aaaa)");
                }
            }catch (Exception e){
                erroData("Formato ou data inválida! (dd/mm/aaaa)");
            }

        }while(true);
    }

    public void erroData(String erro){
        System.out.println(erro);
        System.out.println("Pressione enter pra continuar...");
        scanner.nextLine();
    }

    public LocalTime escolherHorario(){
        do {
            System.out.println("Digite o horario que você deseja agendar (HH:mm):");
            String[] data =  (scanner.nextLine()).split(":");
            try{
                if(data.length == 2 ){
                    int hora = Integer.parseInt(data[0]);
                    int minuto = Integer.parseInt(data[1]);

                    if(hora <= 24 && minuto <= 59){
                        LocalTime horarioEscolhido = LocalTime.of(hora, minuto);

                        return horarioEscolhido;
                    }else{
                        erroHorario();
                    }
                }else{
                    erroHorario();
                }
            }catch (Exception e){
                erroHorario();
            }

        }while(true);
    }

    public void erroHorario(){
        System.out.println("Formato do horário inválido! (hh:mm)");
        System.out.println("Pressione enter pra continuar...");
        scanner.nextLine();
    }

    public List<Equipamento> escolherEquipamentos(){

        List<Equipamento> equipamentosDisponiveisPraEscolher = equipamentoController.getEquipamentos();
        List<Equipamento> equipamentosVaiUsar = new ArrayList<>();

        boolean adicionarMais = true;
        do{
            int contador = 1;
            System.out.println("Equipamento Disponiveis:");

            for (Equipamento equipamento : equipamentosDisponiveisPraEscolher) {
                System.out.println((contador++) + "- " + equipamento);
            }

            System.out.println("Digite qual equipamento deseja:");
            Integer equipamentoIndex = scanner.nextInt();
            scanner.nextLine();

            Equipamento equipamento = null;


            if(equipamentoIndex >= 1 && equipamentoIndex <= equipamentosDisponiveisPraEscolher.size()){
                equipamento = equipamentosDisponiveisPraEscolher.get(equipamentoIndex-1);
            }

            if(equipamento == null){
                System.out.println("Digitado incorretamente! Digite novamente:");
                System.out.println("Pressione enter pra continuar...");
                scanner.nextLine();
            }else{
                equipamentosVaiUsar.add(equipamento);
                equipamentosDisponiveisPraEscolher.remove(equipamento);

                if(equipamentosDisponiveisPraEscolher.size() > 0){
                    boolean respostaIncorreta = true;
                    do {
                        System.out.println("Deseja reservar mais um equipamento? (s/n)");
                        String resposta = scanner.next();

                        if(resposta.equalsIgnoreCase("s")){
                            respostaIncorreta = false;
                        }else if(resposta.equalsIgnoreCase("n")){
                            respostaIncorreta = false;
                            adicionarMais = false;
                        }else{
                            System.out.println("Digitado incorretamente! (s/n)");
                            System.out.println("Pressione enter pra continuar...");
                            scanner.nextLine();
                        }
                    }while (respostaIncorreta);
                }else{
                    break;
                }
            }
        }while (adicionarMais);

        return equipamentosVaiUsar;
    }
}
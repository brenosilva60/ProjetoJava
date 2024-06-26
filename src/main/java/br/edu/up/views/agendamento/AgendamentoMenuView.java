package br.edu.up.views.agendamento;

import br.edu.up.controles.AgendamentoController;
import br.edu.up.controles.AlunoController;
import br.edu.up.modelos.Agendamento;
import br.edu.up.modelos.Aluno;
import br.edu.up.views.agendamento.AgendamentoCriacaoView;
import br.edu.up.views.cruds.AlunoView;
import br.edu.up.views.cruds.EquipamentoView;
import br.edu.up.views.cruds.InstrutorView;
import br.edu.up.views.cruds.TreinoView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgendamentoMenuView {
    Scanner scanner = new Scanner(System.in);
    AgendamentoCriacaoView agendamentoCriacaoView = new AgendamentoCriacaoView();
    AlunoController alunoController = new AlunoController();
    AgendamentoController agendamentoController = new AgendamentoController();

    public void mostrar(){
        int opcao = 0;

        do {
            System.out.println("\nSelecione uma opção:");
            System.out.println("1. Fazer um agendamento");
            System.out.println("2. Listar Agendamentos");
            System.out.println("3. Pesquisar agendametos de um aluno");
            System.out.println("4. Cancelar agendameto");
            System.out.println("5. Sair");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    agendamentoCriacaoView.mostrar();
                    agendamentoController = new AgendamentoController();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    pesquisar();
                    break;
                case 4:
                    cancelar();
                    agendamentoController = new AgendamentoController();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 5);
    }

    public void listar(){
        AgendamentoController agendamentoController = new AgendamentoController();
        int contador = 1;

        for(Agendamento agendamento : agendamentoController.getAgendamentos()){
            System.out.println(contador + "- " + agendamento.toString()+"\n");
            contador++;
        }

        if(contador == 1){
            System.out.println("Não foi encontrado nenhum agendamento!");
        }
    }

    public void pesquisar(){
        System.out.println("Digite o id do aluno:");
        Integer id = scanner.nextInt();

        Aluno aluno = alunoController.pesquisar(id);

        if(aluno == null){
            System.out.println("Nenhum aluno econtrado com esse id!");
        }else{
            AgendamentoController agendamentoController = new AgendamentoController();


            List<Agendamento> listaDeAgendamentoAluno = new ArrayList<>();
            int contador = 1;
            String agendamentos = "";

            for(Agendamento agendamento : agendamentoController.getAgendamentos()){
                if(agendamento.getAluno().getId() == id){
                    agendamentos += contador + "- " + agendamento.toString()+"\n";
                    listaDeAgendamentoAluno.add(agendamento);
                    contador++;
                }
            }

            if(contador == 1){
                System.out.println("Não foi encontrado nenhum agendamento com esse aluno!");
            }else{
                System.out.println("Lista de agendamentos do Aluno " + aluno.getNome() + ":");
                System.out.println(agendamentos);
            }
        }
    }

    public void cancelar(){
        System.out.println("Digite o id de qual agendamento deseja cancelar:");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        Agendamento agendamentoCancelar = agendamentoController.pesquisar(id);

        if(agendamentoCancelar != null){
            if(agendamentoCancelar.getDataDoTreino().isAfter(LocalDateTime.now())){
                agendamentoController.cancelarAgendamento(id);

                System.out.println("Cancelado com sucesso!");
            }else{
                System.out.println("Não é possivel cancelar um agendamento passado!");
            }
        }else{
            System.out.println("Não foi encontrado nenhum agendamento com esse id!");
        }
    }
}

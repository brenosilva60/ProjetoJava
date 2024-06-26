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
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    pesquisar();
                    break;
                case 4:
                    cancelar();
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
    }

    public void pesquisar(){
        System.out.println("Digite o id do aluno:");
        Integer id = scanner.nextInt();

        Aluno aluno = alunoController.pesquisar(id);

        if(aluno == null){
            System.out.println("Nenhum aluno econtrado com esse id!");
        }else{
            AgendamentoController agendamentoController = new AgendamentoController();
            System.out.println("Lista de agendamentos do Aluno " + aluno.getNome() + ":");

            List<Agendamento> listaDeAgendamentoAluno = new ArrayList<>();
            int contador = 1;
            for(Agendamento agendamento : agendamentoController.getAgendamentos()){
                if(agendamento.getAluno().getId() == id){
                    System.out.println(contador + "- " + agendamento.toString()+"\n");
                    listaDeAgendamentoAluno.add(agendamento);
                    contador++;
                }
            }
        }
    }

    public void cancelar(){
        List<Agendamento> listaDeAgendamentoAluno = new ArrayList<>();

        System.out.println("Digite o id do aluno:");
        Integer id = scanner.nextInt();

        Aluno aluno = alunoController.pesquisar(id);

        if(aluno == null){
            System.out.println("Nenhum aluno econtrado com esse id!");
        }else{
            AgendamentoController agendamentoController = new AgendamentoController();
            System.out.println("Lista de agendamentos do Aluno " + aluno.getNome() + ":");


            int contador = 1;
            for(Agendamento agendamento : agendamentoController.getAgendamentos()){
                if(agendamento.getAluno().getId() == id && agendamento.getDataDoTreino().isAfter(LocalDateTime.now())){
                    System.out.println(contador + "- " + agendamento.toString()+"\n");
                    listaDeAgendamentoAluno.add(agendamento);
                    contador++;
                }
            }
        }

        System.out.println("Digite qual agendamento deseja cancelar:");
        Integer opcao = scanner.nextInt();
        scanner.nextLine();

        Agendamento agendamentoCancelar = listaDeAgendamentoAluno.get(opcao-1);

        agendamentoController.cancelarAgendamento(agendamentoCancelar.getId());

        System.out.println("Cancelado com sucesso!");
    }
}

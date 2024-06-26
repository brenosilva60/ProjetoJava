package br.edu.up.views;

import br.edu.up.views.agendamento.AgendamentoMenuView;
import br.edu.up.views.cruds.AlunoView;
import br.edu.up.views.cruds.EquipamentoView;
import br.edu.up.views.cruds.InstrutorView;
import br.edu.up.views.cruds.TreinoView;

import java.util.Scanner;

public class MenuPrincipal {
    Scanner scanner = new Scanner(System.in);

    AlunoView alunoView = new AlunoView();
    TreinoView treinoView = new TreinoView();
    EquipamentoView equipamentoView = new EquipamentoView();
    InstrutorView instrutorView = new InstrutorView();
    AgendamentoMenuView agendamentoMenuView = new AgendamentoMenuView();

    public void mostrar(){
        int opcao = 0;

        do {
            System.out.println("\nSelecione uma opção:");
            System.out.println("1. Alunos");
            System.out.println("2. Treinos");
            System.out.println("3. Equipamentos");
            System.out.println("4. Instrutores");
            System.out.println("5. Agendar");
            System.out.println("6. Sair");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    alunoView.mostrar();
                    break;
                case 2:
                    treinoView.mostrar();
                    break;
                case 3:
                    equipamentoView.mostrar();
                    break;
                case 4:
                    instrutorView.mostrar();
                    break;
                case 5:
                    agendamentoMenuView.mostrar();
                    break;
                case 6:
                    System.out.println("Fechando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 6);
    }
}
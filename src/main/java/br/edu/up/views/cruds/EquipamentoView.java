package br.edu.up.views.cruds;

import br.edu.up.controles.EquipamentoController;
import br.edu.up.modelos.Aluno;
import br.edu.up.modelos.Equipamento;

import java.util.List;
import java.util.Scanner;

public class EquipamentoView {
    Scanner scanner = new Scanner(System.in);
    EquipamentoController equipamentoController = new EquipamentoController();


    public void mostrar() {
        int opcao = 0;

        do {
            System.out.println("\nMenu Equipamento:");
            System.out.println("1. Adicionar Equipamento");
            System.out.println("2. Listar Equipamento");
            System.out.println("3. Pesquisar Equipamento");
            System.out.println("4. Alterar Equipamento");
            System.out.println("5. Remover Equipamento");
            System.out.println("6. Voltar");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    pesquisar();
                    break;
                case 4:
                    alterar();
                    break;
                case 5:
                    remover();
                case 6:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 6);
    }

    public void adicionar() {
        System.out.print("Digite o nome do equipamento: ");
        String nome = scanner.nextLine();

        int id = equipamentoController.adicionar(new Equipamento(nome));

        System.out.println("O id do equipamento é: " + id);
    }

    public void listar() {
        List<Equipamento> equipamentos = equipamentoController.listar();
        if (equipamentos.isEmpty()) {
            System.out.println("Nenhum equipamento cadastrado.");
        } else {
            System.out.println("Lista de Equipamentos:");
            for (Equipamento equipamento : equipamentos) {
                System.out.println(equipamento);
            }
        }
    }

    public  void pesquisar() {
        System.out.print("Digite o ID do equipamento que deseja pesquisar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Equipamento equipamento = equipamentoController.pesquisar(id);
        if (equipamento != null) {
            System.out.println("Equipamento encontrado: " + equipamento);
        } else {
            System.out.println("Equipamento não encontrado.");
        }
    }

    public void alterar() {
        System.out.print("Digite o ID do equipamento que deseja alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Equipamento equipamento = equipamentoController.pesquisar(id);

        if (equipamento != null) {
            System.out.print("Digite o novo nome do equipamento: ");
            String nome = scanner.nextLine();
            equipamento.setNome(nome);

            equipamentoController.alterar(id, equipamento);

            System.out.println("Equipamento alterado com sucesso!");
        } else {
            System.out.println("Equipamento não encontrado.");
        }
    }

    public void remover() {
        System.out.print("Digite o ID do equipamento que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        equipamentoController.remover(id);
    }
}

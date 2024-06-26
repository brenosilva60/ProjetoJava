package br.edu.up.views.cruds;

import br.edu.up.controles.InstrutorController;
import br.edu.up.modelos.Equipamento;
import br.edu.up.modelos.Instrutor;

import java.util.List;
import java.util.Scanner;

public class InstrutorView {
    Scanner scanner = new Scanner(System.in);
    InstrutorController instrutorController = new InstrutorController();

    public void mostrar() {
        int opcao = 0;
        do {
            System.out.println("\nMenu Instrutor:");
            System.out.println("1. Adicionar Instrutor");
            System.out.println("2. Listar Instrutor");
            System.out.println("3. Pesquisar Instrutor");
            System.out.println("4. Alterar Instrutor");
            System.out.println("5. Remover Instrutor");
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
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 6);
    }

    public void adicionar() {
        System.out.print("Digite o nome do instrutor: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o salário do instrutor: ");
        Double salario = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o email do instrutor: ");
        String email = scanner.nextLine();

        instrutorController.adicionar(new Instrutor(nome, salario, email));
    }

    public void listar() {
        List<Instrutor> instrutores = instrutorController.listar();

        if (instrutores.isEmpty()) {
            System.out.println("Nenhum instrutor cadastrado.");
        } else {
            System.out.println("Lista de Instrutores:");

            for (Instrutor instrutor : instrutores) {
                System.out.println(instrutor);
            }
        }
    }

    public void pesquisar() {
        System.out.print("Digite o ID do instrutor que deseja pesquisar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Instrutor instrutor = instrutorController.pesquisar(id);

        if (instrutor != null) {
            System.out.println("Instrutor encontrado: " + instrutor);
        } else {
            System.out.println("Instrutor não encontrado.");
        }
    }

    public void alterar() {
        System.out.print("Digite o ID do Instrutor que deseja alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Instrutor instrutor = instrutorController.pesquisar(id);

        if (instrutor != null) {
            System.out.print("Digite o nome do instrutor: ");
            String nome = scanner.nextLine();
            instrutor.setNome(nome);

            System.out.print("Digite o salário do instrutor: ");
            Double salario = scanner.nextDouble();
            scanner.nextLine();
            instrutor.setSalario(salario);

            System.out.print("Digite o email do instrutor: ");
            String email = scanner.nextLine();
            instrutor.setEmail(email);

            instrutorController.alterar(id, instrutor);

            System.out.println("Instrutor alterado com sucesso!");
        } else {
            System.out.println("Instrutor não encontrado.");
        }
    }

    public void remover() {
        System.out.print("Digite o ID do instrutor que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        instrutorController.remover(id);
    }
}

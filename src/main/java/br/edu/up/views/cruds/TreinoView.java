package br.edu.up.views.cruds;

import br.edu.up.controles.TreinoController;
import br.edu.up.modelos.Instrutor;
import br.edu.up.modelos.Treino;

import java.util.List;
import java.util.Scanner;

public class TreinoView {
    Scanner scanner = new Scanner(System.in);
    TreinoController treinoController = new TreinoController();


    public void mostrar() {
        int opcao;
        do {
            System.out.println("\nMenu Treino:");
            System.out.println("1. Adicionar Treino");
            System.out.println("2. Listar Treino");
            System.out.println("3. Pesquisar Treino");
            System.out.println("4. Alterar Treino");
            System.out.println("5. Remover Treino");
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
        System.out.print("Digite o nome do treino: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a descrição do treino: ");
        String descricao = scanner.nextLine();

        treinoController.adicionar(new Treino(nome, descricao));
    }



    public void listar() {
        List<Treino> treinos = treinoController.listar();

        if (treinos.isEmpty()) {
            System.out.println("Nenhum treino cadastrado.");
        } else {
            System.out.println("Lista de Treinos:");
            for (Treino treino : treinos) {
                System.out.println(treino);
            }
        }
    }

    public void pesquisar() {
        System.out.print("Digite o ID do treino que deseja pesquisar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Treino treino = treinoController.pesquisar(id);

        if (treino != null) {
            System.out.println("Treino encontrado: " + treino);
        } else {
            System.out.println("Treino não encontrado.");
        }
    }

    public void alterar() {
        System.out.print("Digite o ID do treino que deseja alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Treino treino = treinoController.pesquisar(id);

        if (treino != null) {
            System.out.print("Digite o novo nome do treino: ");
            String nome = scanner.nextLine();
            treino.setNome(nome);

            System.out.print("Digite a nova descrição do treino: ");
            String descricao = scanner.nextLine();
            treino.setDescricao(descricao);

            treinoController.alterar(id, treino);

            System.out.println("Treino alterado com sucesso!");
        } else {
            System.out.println("Treino não encontrado.");
        }
    }

    public void remover() { System.out.print("Digite o ID do treino que deseja alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Treino treino = treinoController.pesquisar(id);

        if(treinoController.remover(treino)){
            System.out.println("Removido com sucesso");
        }else{
            System.out.println("Falha na remoção!");
        }

    }
}
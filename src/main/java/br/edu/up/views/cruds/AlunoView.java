package br.edu.up.views.cruds;

import br.edu.up.controles.AlunoController;
import br.edu.up.modelos.Aluno;
import br.edu.up.modelos.Aluno.TipoDoPlano;

import java.util.List;
import java.util.Scanner;

public class AlunoView {
    private Scanner scanner = new Scanner(System.in);
    private AlunoController alunoController = new AlunoController();

    public void mostrar() {
        int opcao = 0;

        do {
            System.out.println("\nMenu Aluno:");
            System.out.println("1. Adicionar Aluno");
            System.out.println("2. Listar Alunos");
            System.out.println("3. Pesquisar Aluno");
            System.out.println("4. Alterar Aluno");
            System.out.println("5. Remover Aluno");
            System.out.println("6. Voltar");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adcionar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    pequisar();
                    break;
                case 4:
                    altarar();
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

    public void adcionar() {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        TipoDoPlano tipoDoPlano = escolherPlano();

        int id = alunoController.adicionar(new Aluno(nome, tipoDoPlano));

        System.out.println("O id do aluno é: " + id);
    }

    public TipoDoPlano escolherPlano(){
        TipoDoPlano tipoDoPlano = null;
        boolean repetir = true;

        do{
            System.out.println("\nTipos de planos:\n");
            int contador = 1;

            for(TipoDoPlano tipo: TipoDoPlano.values()) {
                System.out.println(contador + "- " + tipo.toString());
                contador++;
            }

            System.out.println("Digite o tipo do Plano que deseja:");
            Integer escolhaPlano = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaPlano){
                case 1:
                    tipoDoPlano = TipoDoPlano.Mensal;
                    repetir = false;
                    break;
                case 2:
                    tipoDoPlano = TipoDoPlano.Trimestral;
                    repetir = false;
                    break;
                case 3:
                    tipoDoPlano = TipoDoPlano.Semestral;
                    repetir = false;
                    break;
                case 4:
                    tipoDoPlano = TipoDoPlano.Anual;
                    repetir = false;
                    break;
                default:
                    System.out.println("Erro! tipo digitado invalido! Digite novamente!");
                    System.out.println("Pressione ENTER pra continuar...");
                    scanner.nextLine();
                    break;
            }
        }while (repetir);

        return tipoDoPlano;
    }

    public void listar() {
        List<Aluno> alunos = alunoController.listar();

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            System.out.println("Lista de Alunos:");
            for (Aluno aluno : alunos) {
                System.out.println(aluno);
            }
        }
    }

    public void pequisar() {
        System.out.print("Digite o ID do aluno que deseja pesquisar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Aluno aluno = alunoController.pesquisar(id);

        if (aluno != null) {
            System.out.println("Aluno encontrado: " + aluno);
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public void altarar() {
        System.out.print("Digite o ID do aluno que deseja alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Aluno aluno = alunoController.pesquisar(id);

        if (aluno != null) {
            System.out.print("Digite o novo nome do aluno: ");
            String nome = scanner.nextLine();
            aluno.setNome(nome);

            TipoDoPlano tipoDoPlano = escolherPlano();
            aluno.setTipoDoPlano(tipoDoPlano);

           alunoController.alterar(aluno.getId(), aluno);

            System.out.println("Aluno alterado com sucesso!");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public void remover() {
        System.out.print("Digite o ID do aluno que deseja remover: ");
        int id = scanner.nextInt();

        scanner.nextLine();
        alunoController.remover(id);
    }

}
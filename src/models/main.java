
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Aluno> alunos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GerenciadorArquivo.carregarAlunos(alunos);

        int opcao;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Lançar notas");
            System.out.println("3. Lançar N3");
            System.out.println("4. Mostrar todos os alunos");
            System.out.println("5. Mostrar alunos reprovados");
            System.out.println("6. Salvar dados");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> lancarNotas();
                case 3 -> lancarN3();
                case 4 -> mostrarAlunos();
                case 5 -> mostrarAlunosReprovados();
                case 6 -> GerenciadorArquivo.salvarAlunos(alunos);
                case 7 -> System.out.println("Saindo do programa...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 7);
    }

    static void cadastrarAluno() {
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();
        System.out.print("Professor da disciplina: ");
        String professor = scanner.nextLine();
        alunos.add(new Aluno(nome, matricula, professor));
        System.out.println("Aluno cadastrado com sucesso!");
    }

    static void lancarNotas() {
        System.out.print("Informe a matricula do aluno: ");
        String matricula = scanner.nextLine();
        Aluno aluno = buscarAluno(matricula);
        if (aluno != null) {
            System.out.print("Nota N1 (0-10): ");
            aluno.n1 = validarNota(scanner.nextDouble());
            System.out.print("Nota da prova N2 (0-10): ");
            aluno.n2Prova = validarNota(scanner.nextDouble());
            System.out.print("Nota do trabalho N2 (0-10): ");
            aluno.n2Trabalho = validarNota(scanner.nextDouble());
            scanner.nextLine();
            aluno.calcularMedia();
            System.out.println("Notas lançadas e média calculada!");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    static void lancarN3() {
        System.out.print("Informe a matricula do aluno para N3: ");
        String matricula = scanner.nextLine();
        Aluno aluno = buscarAluno(matricula);
        if (aluno != null && aluno.status.equals("Prova Final")) {
            System.out.print("Nota da N3 (0-10): ");
            aluno.n3 = validarNota(scanner.nextDouble());
            scanner.nextLine();
            aluno.calcularNotaFinalComN3();
            System.out.println("Nota N3 lançada e status atualizado!");
        } else {
            System.out.println("Aluno não encontrado ou não precisa de N3.");
        }
    }

    static void mostrarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (Aluno aluno : alunos) {
            System.out.println("---");
            System.out.println(aluno);
        }
    }

    static void mostrarAlunosReprovados() {
        boolean encontrou = false;
        for (Aluno aluno : alunos) {
            if (aluno.status.contains("Reprovado")) {
                System.out.println("---");
                System.out.println(aluno);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum aluno reprovado encontrado.");
        }
    }

    static Aluno buscarAluno(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.matricula.equalsIgnoreCase(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    static double validarNota(double nota) {
        while (nota < 0 || nota > 10) {
            System.out.print("Nota inválida! Digite um valor entre 0 e 10: ");
            nota = scanner.nextDouble();
        }
        return nota;
    }
}

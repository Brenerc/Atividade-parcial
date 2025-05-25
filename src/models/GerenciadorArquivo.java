
import java.io.*;
import java.util.ArrayList;

public class GerenciadorArquivo {
    private static final String ARQUIVO = "alunos.txt";

    public static void salvarAlunos(ArrayList<Aluno> alunos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Aluno aluno : alunos) {
                writer.println(aluno.nome + ";" + 
                              aluno.matricula + ";" + 
                              aluno.professor + ";" + 
                              aluno.n1 + ";" + 
                              aluno.n2Prova + ";" + 
                              aluno.n2Trabalho + ";" + 
                              aluno.n3 + ";" + 
                              aluno.media + ";" + 
                              aluno.status);
            }
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static void carregarAlunos(ArrayList<Aluno> alunos) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Aluno aluno = new Aluno(dados[0], dados[1], dados[2]);
                aluno.n1 = Double.parseDouble(dados[3]);
                aluno.n2Prova = Double.parseDouble(dados[4]);
                aluno.n2Trabalho = Double.parseDouble(dados[5]);
                aluno.n3 = Double.parseDouble(dados[6]);
                aluno.media = Double.parseDouble(dados[7]);
                aluno.status = dados[8];
                alunos.add(aluno);
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Iniciando com lista vazia.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }
}

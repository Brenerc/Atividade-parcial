public class Aluno {
    String nome;
    String matricula;
    double n1;
    double n2Prova;
    double n2Trabalho;
    double n3;
    double media;
    String status;
    String professor;

    public Aluno(String nome, String matricula, String professor) {
        this.nome = nome;
        this.matricula = matricula;
        this.professor = professor;
        this.n1 = 0;
        this.n2Prova = 0;
        this.n2Trabalho = 0;
        this.n3 = -1;
        this.media = 0;
        this.status = "Notas não lançadas";
    }

    public void calcularMedia() {
        double n2 = (n2Prova + n2Trabalho) / 2;
        this.media = (n1 + n2) / 2;

        if (media >= 6) {
            status = "Aprovado";
        } else if (media >= 4) {
            status = "Prova Final";
        } else {
            status = "Reprovado";
        }
    }

    public void calcularNotaFinalComN3() {
        if (n3 >= 0) {
            double finalMedia = (media + n3) / 2;
            if (finalMedia >= 5) {
                status = "Aprovado na N3";
            } else {
                status = "Reprovado na N3";
            }
        }
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                "\nMatricula: " + matricula +
                "\nProfessor: " + professor +
                "\nM1: " + n1 +
                "\nN2 Prova: " + n2Prova +
                "\nN2 Trabalho: " + n2Trabalho +
                "\nN3: " + (n3 >= 0 ? n3 : "Não lançada") +
                "\nMédia: " + String.format("%.2f", media) +
                "\nStatus: " + status + "\n";
    }
}

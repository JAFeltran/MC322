package mc322.lab05a;

public class PecaPeao {
    char estado;
    // guarda se a peca representa uma casa vazia ('-'), esta presente ('P' ou
    // 'B' dependendo da cor)
    int i, j;
    // guardam a posicao da peca na matriz do tabuleiro

    public PecaPeao(char estado, int i, int j) {
        this.estado = estado;
        this.i = i;
        this.j = j;
    }

    public boolean verificarMovimento(char alvo, int iFinal, int jFinal) {
        if (alvo == '-') {
            if (iFinal == i + 1 && (jFinal == j + 1 || jFinal == j - 1)) {
                estado = '-';
                return true;
            } else if ((iFinal == i + 2 || iFinal == i - 2) && (jFinal == j + 2 || jFinal == j - 2)) {
                estado = '-';
                return true;
            }
        }

        return false;
    }
}
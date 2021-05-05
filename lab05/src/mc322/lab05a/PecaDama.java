package mc322.lab05a;

public class PecaDama {
    char estado;
    // guarda se a peca representa uma casa vazia ('-'), esta presente ('P' ou
    // 'B' dependendo da cor)
    int i, j;
    // guardam a posicao da peca na matriz do tabuleiro

    public PecaDama(char estado, int i, int j) {
        this.estado = estado;
        this.i = i;
        this.j = j;
    }

    public boolean verificarMovimento(char alvo, int iFinal, int jFinal) {
        if (alvo == '-') {
            for (int k = 1; k < 8; k++) {
                if ((iFinal == i + k || iFinal == i - k) && (jFinal == j + k || jFinal == j - k)) {
                    estado = '-';
                    return true;
                }
            }
        }

        return false;
    }
}

package mc322.lab04;

public class PecaRestaUm {
    char estado;
    // guarda se a peca foi comida ('-'), esta presente ('P') ou nao eh parte do
    // tabuleiro (' ')
    boolean vizinhos;
    // guarda se a peca tem pelo menos um vizinho (sempre falsa para estados '-' ou
    // (' ')

    public PecaRestaUm(char estado, boolean vizinhos) {
        this.estado = estado;
        this.vizinhos = vizinhos;
    }

    public void remover() {
        estado = '-';
        vizinhos = false;
    }
}
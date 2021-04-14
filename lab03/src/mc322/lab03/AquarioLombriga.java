package mc322.lab03;

public class AquarioLombriga {
    int tam_aquario;
    int cabeca;
    int fim;

    public AquarioLombriga(int tam_aquario, int cabeca, int fim) {
        verificarTamanho();

        this.tam_aquario = tam_aquario;
        this.cabeca = cabeca;
        this.fim = fim;
    }

    public void verificarTamanho() {
        int tam_lombriga;

        if (fim < cabeca) {
            tam_lombriga = cabeca - fim + 1;
        } else {
            tam_lombriga = fim - cabeca + 1;
        }

        if (tam_aquario < tam_lombriga) {
            tam_aquario = tam_lombriga;
        }
    }

    public void crescer() {
        if (fim != 1 && fim < cabeca) {
            fim--;
        } else if (fim != tam_aquario && fim > cabeca) {
            fim++;
        }
    }

    public void virar() {
        int k;

        k = cabeca;
        cabeca = fim;
        fim = k;
    }

    public void mover() {
        if (fim < cabeca) {
            if (cabeca == tam_aquario) {
                virar();
            } else {
                cabeca++;
                fim++;
            }

        } else {
            if (cabeca == 1) {
                virar();
            } else {
                cabeca--;
                fim--;
            }
        }
    }

    public String apresenta() {
        String estado = "";

        for (int i = 1; i <= tam_aquario; i++) {
            if (i == cabeca) {
                estado = estado + "0";
            } else if ((i >= fim && i < cabeca) || (i <= fim && i > cabeca)) {
                estado = estado + "@";
            } else {
                estado = estado + "#";
            }
        }

        return estado;
    }
}
package mc322.lab05a;

public class TabuleiroA {
    PecaPeao peoes[][];
    PecaDama damas[][];
    // matrizes onde a primeira coordenada representa as linhas (numeros) e a
    // segunda representa as colunas (letras)
    // para essa representacao, os numeros de 8 a 1, assim como as letras de "a" a
    // "h", variam de 0 a 7
    char sobreposicao[][];
    // segue a representacao anterior, mas representa a sobreposicao dos dois
    // tabuleiros

    public TabuleiroA() {
        inicializarPeoes();
        inicializarDamas();
        sobreposicao = new char[8][8];

        sobrepor();
    }

    public void inicializarPeoes() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 || i == 2) {
                    if (j % 2 != 0) {
                        peoes[i][j] = new PecaPeao('p', i, j);
                    } else {
                        peoes[i][j] = new PecaPeao('-', i, j);
                    }
                } else if (i == 1) {
                    if (j % 2 == 0) {
                        peoes[i][j] = new PecaPeao('p', i, j);
                    } else {
                        peoes[i][j] = new PecaPeao('-', i, j);
                    }
                } else if (i == 5 || i == 7) {
                    if (j % 2 == 0) {
                        peoes[i][j] = new PecaPeao('b', i, j);
                    } else {
                        peoes[i][j] = new PecaPeao('-', i, j);
                    }
                } else if (i == 6) {
                    if (j % 2 != 0) {
                        peoes[i][j] = new PecaPeao('b', i, j);
                    } else {
                        peoes[i][j] = new PecaPeao('-', i, j);
                    }
                } else {
                    peoes[i][j] = new PecaPeao('-', i, j);
                }
            }
        }
    }

    public void inicializarDamas() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                damas[i][j] = new PecaDama('-', i, j);
            }
        }
    }

    public void sobrepor() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (peoes[i][j].estado == '-' && damas[i][j].estado != '-') {
                    sobreposicao[i][j] = damas[i][j].estado;
                } else {
                    sobreposicao[i][j] = peoes[i][j].estado;
                }
            }
        }
    }

    public String executarComando(String comando) {
        int iInicial, iFinal, jInicial, jFinal;

        iInicial = Character.getNumericValue(comando.charAt(1)) - 1;
        iFinal = Character.getNumericValue(comando.charAt(4)) - 1;

        jInicial = comando.charAt(0) - 97;
        jFinal = comando.charAt(3) - 97;
        // como o valor ASCII de 'a' é 97, para obter o correspondende numerico das
        // letras minusculas, seguindo o padrao 'a' = 0, deve-se subtrair 97

        if (sobreposicao[iInicial][jInicial] == 'P' || sobreposicao[iInicial][jInicial] == 'B') {
            moverDama(iInicial, iFinal, jInicial, jFinal);
        } else if (sobreposicao[iInicial][jInicial] == 'p' || sobreposicao[iInicial][jInicial] == 'b') {
            moverPeao(iInicial, iFinal, jInicial, jFinal);
        } else {
            System.out.println("Movimento invalido, nao ha peca na posicao inicial");

            return ("Movimento invalido");
        }

        sobrepor();

        return obterSaida(iInicial, iFinal, jInicial, jFinal, false);
    }

    public void moverPeao(int iInicial, int iFinal, int jInicial, int jFinal) {
        if (peoes[iInicial][jInicial].verificarMovimento(sobreposicao[iFinal][jFinal], iFinal, jFinal) == true) {
            if (verificarCaptura(iInicial, iFinal, jInicial, jFinal) == true) {
                capturar(iInicial, iFinal, jInicial, jFinal);
            }

            peoes[iFinal][jFinal].estado = sobreposicao[iInicial][jInicial];
        }
    }

    public void moverDama(int iInicial, int iFinal, int jInicial, int jFinal) {
        if (damas[iInicial][jInicial].verificarMovimento(sobreposicao[iFinal][jFinal], iFinal, jFinal) == true) {
            if (verificarCaptura(iInicial, iFinal, jInicial, jFinal) == true) {
                capturar(iInicial, iFinal, jInicial, jFinal);
            }

            damas[iFinal][jFinal].estado = sobreposicao[iInicial][jInicial];
        }
    }

    public boolean verificarCaptura(int iInicial, int iFinal, int jInicial, int jFinal) {
        if (iFinal > iInicial + 1 || iFinal < iInicial - 1) {
            if (jFinal > jInicial + 1 || jFinal < jInicial - 1) {
                return true;
            }
        }

        return false;
    }

    public void capturar(int iInicial, int iFinal, int jInicial, int jFinal) {
        // nao verifica se ta capturando da cor oposta

        for (int k = 1; k < 8; k++) {
            if (iFinal == iInicial + k) {
                if (jFinal == jInicial + k) {
                    peoes[iInicial + (k - 1)][jFinal + (k - 1)].estado = '-';
                    damas[iInicial + (k - 1)][jFinal + (k - 1)].estado = '-';
                } else if (jFinal == jInicial - k) {
                    peoes[iInicial + (k - 1)][jFinal - (k - 1)].estado = '-';
                    damas[iInicial + (k - 1)][jFinal - (k - 1)].estado = '-';
                }
            } else {
                if (jFinal == jInicial + k) {
                    peoes[iInicial - (k - 1)][jFinal + (k - 1)].estado = '-';
                    damas[iInicial - (k - 1)][jFinal + (k - 1)].estado = '-';
                } else if (jFinal == jInicial - k) {
                    peoes[iInicial - (k - 1)][jFinal - (k - 1)].estado = '-';
                    damas[iInicial - (k - 1)][jFinal - (k - 1)].estado = '-';
                }
            }
        }
    }

    public String obterSaida(int iInicial, int iFinal, int jInicial, int jFinal, boolean inicial) {
        String retorno = "";

        if (inicial == true) {
            System.out.println("Tabuleiro inicial:");
        } else {
            char j;

            j = (char) (jInicial + 97);

            System.out.println("Source: " + j + (iInicial + 1));

            j = (char) (jFinal + 97);

            System.out.println("Target: " + j + (iFinal + 1));
        }

        imprimirTabuleiro();

        for (int m = 0; m < 7; m++) {
            for (int n = 0; n < 7; n++) {
                retorno = retorno + sobreposicao[m][n];
            }

            retorno = retorno + "\n";
        }

        return retorno;
    }

    public void imprimirTabuleiro() {
        for (int m = 0; m < 8; m++) {
            System.out.print((8 - m));

            for (int n = 0; n < 8; n++) {
                System.out.print(" " + sobreposicao[m][n]);
            }

            System.out.println("");
        }

        System.out.println("  a b c d e f g h");
    }
}
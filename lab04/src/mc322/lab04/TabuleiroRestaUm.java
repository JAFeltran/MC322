package mc322.lab04;

public class TabuleiroRestaUm {
    PecaRestaUm matriz[][];
    // uma matriz onde a primeira coordenada representa as linhas (numeros) e a
    // segunda representa as colunas (letras)
    // para essa representacao, os numeros de 7 a 1, assim como as letras de "a" a
    // "g", variam de 0 a 6

    public TabuleiroRestaUm() {
        matriz = new PecaRestaUm[7][7];
        preencherTabuleiro();
    }

    // cria pecas para todos os pontos do tabuleiro, atribuindo o estado correto
    // para cada posicao
    public void preencherTabuleiro() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (i < 2) {
                    if (j < 2 || j > 4) {
                        matriz[i][j] = new PecaRestaUm(' ', false);
                    } else {
                        matriz[i][j] = new PecaRestaUm('P', true);
                    }
                } else if (i > 4) {
                    if (j < 2 || j > 4) {
                        matriz[i][j] = new PecaRestaUm(' ', false);
                    } else {
                        matriz[i][j] = new PecaRestaUm('P', true);
                    }
                } else if (i == 3 && j == 3) {
                    matriz[i][j] = new PecaRestaUm('-', false);
                } else {
                    matriz[i][j] = new PecaRestaUm('P', true);
                }
            }
        }
    }

    // executa o comando obtido do arquivo csv no formato "coordenadas
    // iniciais:coordenadas finais", verificando se o movimento eh valido e movendo
    // a peca
    public String executarComando(String comando) {
        int iInicial, iFinal, jInicial, jFinal;

        iInicial = Character.getNumericValue(comando.charAt(1)) - 1;
        iFinal = Character.getNumericValue(comando.charAt(4)) - 1;

        jInicial = comando.charAt(0) - 97;
        jFinal = comando.charAt(3) - 97;
        // como o valor ASCII de 'a' é 97, para obter o correspondende numerico das
        // letras minusculas seguindo o padrao 'a' = 0, deve-se subtrair 97

        if (verificarValidade(iInicial, iFinal, jInicial, jFinal) == true) {
            moverPeca(iInicial, iFinal, jInicial, jFinal);

            return obterSaida(iInicial, iFinal, jInicial, jFinal, false);
        } else {
            return "Movimento invalido";
        }
    }

    // move a peca da posicao inicial para final, "comendo" a peca entre essas
    // posicoes
    public void moverPeca(int iInicial, int iFinal, int jInicial, int jFinal) {

        matriz[iInicial][jInicial].remover();

        matriz[iFinal][jFinal].estado = 'P';
        verificarVizinhos(iFinal, jFinal);

        comerPeca(iInicial, iFinal, jInicial, jFinal);
    }

    // verifica se o comando dado respeita as regras de funcionamento do jogo,
    // retornando true caso sim e false caso nao
    public boolean verificarValidade(int iInicial, int iFinal, int jInicial, int jFinal) {
        if (matriz[iInicial][jInicial].estado != 'P') {
            System.out.println("Movimento invalido, nao ha peca na posicao inicial");
            return false;
        } else if (matriz[iInicial][jInicial].vizinhos == false) {
            System.out.println("Movimento invalido, peca na posicao inicial sem vizinhos");
            return false;
        } else if (matriz[iFinal][jFinal].estado != '-') {
            System.out.println("Movimento invalido, alvo nao vazio");
            return false;
        } else if (iInicial == iFinal) {
            if (jFinal - jInicial != 2 && jFinal - jInicial != -2) {
                System.out.println("Movimento invalido, so eh possivel pular uma peca por vez");
                return false;
            }
        } else if (jInicial == jFinal) {
            if (iFinal - iInicial != 2 && iFinal - iInicial != -2) {
                System.out.println("Movimento invalido, so eh possivel pular uma peca por vez");
                return false;
            }
        } else {
            System.out.println("Movimento em direcao invalida");
            return false;
        }

        return true;
    }

    // verifica se o peca na posicao ij tem alguma outra peca adjacente (somente nas
    // direcoes verticais e horizontais)
    public void verificarVizinhos(int i, int j) {
        if (matriz[i + 1][j].estado == 'P' || matriz[i - 1][j].estado == 'P') {
            matriz[i][j].vizinhos = true;
        } else if (matriz[i][j + 1].estado == 'P' || matriz[i][j - 1].estado == 'P') {
            matriz[i][j].vizinhos = true;
        } else {
            matriz[i][j].vizinhos = false;
        }
    }

    // atualiza o estado da peca "comida" durante o movimento da posicao inicial
    // para a final
    public void comerPeca(int iInicial, int iFinal, int jInicial, int jFinal) {
        if (iInicial == iFinal) {
            if (jFinal > jInicial) {
                matriz[iInicial][jInicial + 1].remover();
            } else if (jFinal < jInicial) {
                matriz[iInicial][jInicial - 1].remover();
            }
        } else if (jInicial == jFinal) {
            if (iFinal > iInicial) {
                matriz[iInicial + 1][jInicial].remover();
            } else if (iFinal < iInicial) {
                matriz[iInicial - 1][jInicial].remover();
            }
        }
    }

    // retorna uma string representando o tabuleiro apos o movimento da posicao
    // inicial para a final, assim como chama a funcao para imprimir o estado do
    // tabuleiro apos esse movimento. A variavel "primeiro" registra se deve ser
    // imprimido o estado inicial do tabuleiro, caso ela seja verdadeira, as outras
    // variaveis devem ser 0
    public String obterSaida(int iInicial, int iFinal, int jInicial, int jFinal, boolean primeiro) {
        String tabuleiro = "";

        if (primeiro == true) {
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
                tabuleiro = tabuleiro + matriz[m][n].estado;
            }

            tabuleiro = tabuleiro + "\n";
        }

        return tabuleiro;
    }

    // percorre a matriz e imprime o estado atual do tabuleiro, incluindo a legenda
    // das posicoes
    public void imprimirTabuleiro() {
        for (int m = 0; m < 7; m++) {
            System.out.print((7 - m));

            for (int n = 0; n < 7; n++) {
                System.out.print(" " + matriz[m][n].estado);
            }

            System.out.println("");
        }

        System.out.println("  a b c d e f g");
    }
}
package mc322.lab05b;

import java.lang.Math;

public class Tabuleiro
{
    private Peca matriz[][];
    // a primeira coordenada representa as linhas (numeros) e a segunda representa
    // as colunas (letras) para essa representacao, os numeros de 8 a 1, assim como
    // as letras de "a" a "h", variam de 0 a 7
    private boolean erro;
    // caso o ultimo movimento seja invalido, esse valor deve ser true

    public Tabuleiro()
    {
        matriz = new Peca[8][8];
        erro = false;

        iniciarMatriz();
    }

    // coloca as pecas em seus devidos lugares iniciais na matriz, respeitando as regras do jogo
    public void iniciarMatriz()
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (i == 0 || i == 2)
                {
                    if (j % 2 != 0)
                    {
                        matriz[i][j] = new Peao(i, j, 1, 'p');
                    }
                }
                else if (i == 1)
                {
                    if (j % 2 == 0)
                    {
                        matriz[i][j] = new Peao(i, j, 1, 'p');
                    }
                }
                else if (i == 5 || i == 7)
                {
                    if (j % 2 == 0)
                    {
                        matriz[i][j] = new Peao(i, j, 0, 'b');
                    }
                }
                else if (i == 6)
                {
                    if (j % 2 != 0)
                    {
                        matriz[i][j] = new Peao(i, j, 0, 'b');
                    }
                }
            }
        }
    }

    // executa o comando passado como parametro, retornando o estado do tabuleiro apos sua execucao
    public String executarComando(String comando, boolean ultimo)
    {
        int iInicial, iFinal, jInicial, jFinal;
        String estado = "";
        boolean valido;

        if (comando != null)
        {
            iInicial = 8 - Character.getNumericValue(comando.charAt(1));
            iFinal = 8 - Character.getNumericValue(comando.charAt(4));

            jInicial = comando.charAt(0) - 97;
            jFinal = comando.charAt(3) - 97;
            // como o valor ASCII de 'a' é 97, para obter o correspondende numerico das
            // letras minusculas, seguindo o padrao 'a' = 0, deve-se subtrair 97

            valido = solicitaMovimento(iInicial, iFinal, jInicial, jFinal);

            if (!valido && ultimo)
            {
                erro = true;
            }

            System.out.println("Source: " + comando.charAt(0) + comando.charAt(1));
            System.out.println("Target: " + comando.charAt(3) + comando.charAt(4));
        }
        else
        // caso comando seja nulo, retorna e imprime o estado inicial do tabuleiro
        {
            System.out.println("Tabuleiro inicial:");
        }

        imprimirTabuleiro();

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (matriz[i][j] == null)
                {
                    estado += "-";
                }
                else
                {
                    estado += matriz[i][j].getRepresentacao();
                }
            }

            estado += "\n";
        }

        return  estado;
    }

    // faz algumas verificacoes iniciais da validade do movimento e pede para a peca
    // fazer as outras. Retorna true se o movimento for valido e false caso contrario
    public boolean solicitaMovimento(int iInicial, int iFinal, int jInicial, int jFinal)
    {
        Peca trajeto[];
        boolean verificacoes[];
        // verificacoes[0] guarda se o movimento eh valido, e verificacoes[1] guarda se
        // houve captura no movimento

        if (iInicial < 8 && jInicial < 8 && iFinal < 8 && jFinal < 8)
        // o movimento deve iniciar e terminar dentro do tabuleiro
        {
            if (verificarDiagonal(iInicial, iFinal, jInicial, jFinal))
            {
                trajeto = obterTrajeto(iInicial, iFinal, jInicial, jFinal);

                verificacoes = matriz[iInicial][jInicial].verificarMovimento(trajeto, iFinal, jFinal);

                if (verificacoes[0])
                {
                    moverPeca(iInicial, iFinal, jInicial, jFinal, trajeto, verificacoes[1]);
                    return true;
                }
            }
            else
            {
                System.out.println("Movimento invalido!");
            }
        }
        else
        {
            System.out.println("Movimento invalido!");
        }

        return false;
    }

    // verifica se o movimento eh diagonal, retornando true caso e false caso nao
    public boolean verificarDiagonal(int iInicial, int iFinal, int jInicial, int jFinal)
    {
        int iDif, jDif;

        iDif = Math.abs(iFinal - iInicial);
        jDif = Math.abs(jFinal - jInicial);
        // tanto o i quanto o j podem aumentar ou diminuir, logo eh necessario analisar seu valor
        // absoluto, que deve ser igual

        if (iDif == jDif && iDif != 0)
        {
            return true;
        }

        return false;
    }

    // retorna um vetor contendo as pecas que fazem parte do trajeto, na ordem em que aparecem
    public Peca[] obterTrajeto(int iInicial, int iFinal, int jInicial, int jFinal)
    {
        Peca trajeto[];
        int len;
        int m = iInicial, n = jInicial;
        int k = 0;
        boolean iCrescente, jCrescente, parar = false;

        if (iFinal > iInicial)
        {
            len = iFinal - iInicial + 1;

            iCrescente = true;
        }
        else
        {
            len = iInicial - iFinal + 1;

            iCrescente = false;
        }

        if (jFinal > jInicial)
        {
            jCrescente = true;
        }
        else
        {
            jCrescente = false;
        }

        trajeto = new Peca[len];

        while (!parar)
        {
            if (m == iFinal && n == jFinal)
            {
                parar = true;
            }

            trajeto[k] = matriz[m][n];

            if (iCrescente)
            {
                m++;
            }
            else
            {
                m--;
            }

            if (jCrescente)
            {
                n++;
            }
            else
            {
                n--;
            }

            k++;
        }

        return trajeto;
    }

    // atualiza o tabuleiro, levando a peca da posicao inicial a final e removendo uma possivel peca
    // que tenha sido capturada durante o movimento
    public void moverPeca(int iInicial, int iFinal, int jInicial, int jFinal, Peca trajeto[], boolean capturou)
    {
        matriz[iFinal][jFinal] = trajeto[0];
        matriz[iInicial][jInicial] = null;

        promover(iFinal, jFinal);

        if (capturou)
        {
            capturar(trajeto);
        }
    }

    // caso um peao chegue no lado oposto do tabuleiro, ele eh promovida se tornando uma dama
    public void promover(int m, int n)
    {
        int i, j;

        if (matriz[m][n].getCor() == 1 && matriz[m][n].getI() == 7)
        {
            i = matriz[m][n].getI();
            j = matriz[m][n].getJ();

            matriz[m][n] = new Dama(i, j, 1, 'P');
        }
        else if (matriz[m][n].getCor() == 0 && matriz[m][n].getI() == 0)
        {
            i = matriz[m][n].getI();
            j = matriz[m][n].getJ();

            matriz[m][n] = new Dama(i, j, 0, 'B');
        }
    }

    // remove do tabuleiro uma peca capturada durante o movimento
    public void capturar(Peca trajeto[])
    {
        int len = trajeto.length;
        int iCapturado, jCapturado;

        for (int k = 1; k < len - 1; k++)
        {
            if (trajeto[k] != null)
            {
                iCapturado = trajeto[k].getI();
                jCapturado = trajeto[k].getJ();

                matriz[iCapturado][jCapturado] = null;
            }
        }
    }

    // percorre o tabuleiro, imprimindo-o assim como sua legenda (coordenadas)
    public void imprimirTabuleiro()
    {
        for (int i = 0; i < 8; i++)
        {
            System.out.print((8 - i));

            for (int j = 0; j < 8; j++)
            {
                if (matriz[i][j] == null)
                {
                    System.out.print(" -");
                }
                else
                {
                    System.out.print(" " + matriz[i][j].getRepresentacao());
                }
            }

            System.out.println("");
        }

        System.out.println("  a b c d e f g h");
        System.out.println("");
    }

    // escreve no arquivo cujo caminho eh "saida" o estado do tabuleiro
    public void exportarArquivo(String saida)
    {
        CSVHandling csv = new CSVHandling();
        String estadoFinal[];

        csv.setDataExport(saida);

        estadoFinal = obterEstado();

        csv.exportState(estadoFinal);
    }

    // retorna um vetor de strings onde cada posicao eh uma casa do tabuleiro
    public String[] obterEstado()
    {
        String vetor[];
        String casa = "";
        char coluna, estado;
        int linha, k = 0;

        if (erro)
        // caso erro seja true, o estado deve simplesmente ser "erro"
        {
            vetor = new String[1];

            vetor[0] = "erro";
        }
        else
        {
            vetor = new String[64];

            for (int j = 0; j < 8; j++)
            {
                coluna = (char) (j + 97);
                // correcao dos numeros usados na matriz para as coordenadas do tabuleiro

                for (int i = 0; i < 8; i++)
                {
                    linha = i + 1;
                    // correcao dos numeros usados na matriz para as coordenadas do tabuleiro

                    if (matriz[7 - i][j] == null)
                    {
                        estado = '_';
                    }
                    else
                    {
                        estado = matriz[7 - i][j].getRepresentacao();
                    }

                    casa += coluna;
                    casa += linha;
                    casa += estado;

                    vetor[k] = casa;
                    k++;
                    casa = "";
                }
            }
        }

        return vetor;
    }
}
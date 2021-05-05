package mc322.lab05b;

public class Dama extends Peca
{
    public Dama(int i, int j, int cor, char representacao)
    {
        super(i, j, cor, representacao);
    }

    // retorna um vetor de boolean contendo a confirmacao de se a peca pode realizar o movimento
    // descrito pelo trajeto, assim como se houve captura nesse movimento
    public boolean[] verificarMovimento(Peca trajeto[], int iFinal, int jFinal)
    {
        int len = trajeto.length;
        int k = 1;
        int iCapturado = 0, jCapturado = 0;
        boolean verificacoes[] = new boolean[2];
        // verificacoes[0] guarda se o movimento eh valido, e verificacoes[1] guarda se
        // houve captura no movimento
        boolean capturaExcessiva = false;
        // guarda se a dama capturou duas pecas em um unico movimento, algo que desrespeita as regras

        if (trajeto[0] != null && trajeto[len - 1] == null)
        {
            if (trajeto[0].getI() == getI() && trajeto[0].getJ() == getJ())
            {
                for (k = 1; k < len - 1; k++)
                {
                    if (trajeto[k] != null && trajeto[k].getCor() != getCor())
                    {
                        if (!verificacoes[1])
                        {
                            iCapturado = trajeto[k].getI();
                            jCapturado = trajeto[k].getJ();
                            verificacoes[1] = true;
                        }
                        else
                        // caso alguma captura ja tenha ocorrido, o movimento eh invalido
                        {
                            System.out.println("Movimento invalido!");
                            verificacoes[1] = false;
                            capturaExcessiva = true;
                        }
                    }
                }

                if (verificacoes[1])
                {
                    if (iFinal == iCapturado + 1 || iFinal == iCapturado - 1)
                    {
                        if (jFinal == jCapturado + 1 || jFinal == jCapturado - 1)
                        // a dama deve parar no proximo espaco livre do trajeto apos a captura
                        {
                            setI(iFinal);
                            setJ(jFinal);

                            verificacoes[0] = true;
                        }
                    }
                }
                else if (!capturaExcessiva)
                {
                    setI(iFinal);
                    setJ(jFinal);

                    verificacoes[0] = true;
                }
            }
        }

        if (!verificacoes[0] && !capturaExcessiva)
        {
            System.out.println("Movimento invalido!");
        }

        return verificacoes;
    }
}
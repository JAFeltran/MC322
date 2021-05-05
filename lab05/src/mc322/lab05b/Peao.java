package mc322.lab05b;

public class Peao extends Peca
{
    public Peao(int i, int j, int cor, char representacao)
    {
        super(i, j, cor, representacao);
    }

    // retorna um vetor de boolean contendo a confirmacao de se a peca pode realizar o movimento
    // descrito pelo trajeto, assim como se houve captura nesse movimento
    public boolean[] verificarMovimento(Peca trajeto[], int iFinal, int jFinal)
    {
        int len = trajeto.length;
        boolean verificacoes[] = new boolean[2];
        // verificacoes[0] guarda se o movimento eh valido, e verificacoes[1] guarda se
        // houve captura no movimento

        if (trajeto[0] != null && trajeto[len - 1] == null)
        {
            if (trajeto[0].getI() == getI() && trajeto[0].getJ() == getJ())
            {
                if (len == 2)
                {
                    if ((getCor() == 0 && getI() > iFinal) || (getCor() == 1 && getI() < iFinal))
                    // peoes so podem andar para frente, mas "frente" eh relativo para cada cor
                    {
                        setI(iFinal);
                        setJ(jFinal);

                        verificacoes[0] = true;
                    }
                }
                else if (len == 3)
                {
                    if (trajeto[1] != null)
                    // para um peao ter um trajeto de tamanho 3, ele capturou alguma peca,
                    // que obrigatoriamente deve estar entre as posicoes inicial e final
                    {
                        if (trajeto[0].getCor() != trajeto[1].getCor())
                        {
                            setI(iFinal);
                            setJ(jFinal);

                            verificacoes[0] = true;
                            verificacoes[1] = true;
                        }
                    }
                }
            }
        }

        if (!verificacoes[0])
        {
            System.out.println("Movimento invalido!");
        }

        return verificacoes;
    }
}
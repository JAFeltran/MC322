package mc322.lab05b;

public class Peca
{
    private int i;
    private int j;
    // representam as coordenadas da peca na matriz que representa o tabuleiro
    private int cor;
    // representa a cor da peca, sendo preto 1 e branco 0
    private char representacao;
    // guarda como a peca eh representada na hora de ser impressa, de acordo com sua cor e tipo
    // sendo 'b' e 'B' para peoes e damas, respectivamente, brancas e o 'p' e 'P' para peoes
    // e damas pretas

    public Peca(int i, int j, int cor, char representacao)
    {
        this.i = i;
        this.j = j;
        this.cor = cor;
        this.representacao = representacao;
    }

    public int getI()
    {
        return i;
    }

    public int getJ()
    {
        return j;
    }

    public void setI(int i)
    {
        this.i = i;
    }

    public void setJ(int j)
    {
        this.j = j;
    }

    public int getCor()
    {
        return cor;
    }

    public char getRepresentacao()
    {
        return representacao;
    }

    //existe somente para polimorfismo
    public boolean[] verificarMovimento(Peca trajeto[], int iFinal, int jFinal)
    {
        boolean verificacoes[] = new boolean[2];

        return verificacoes;
    }
}
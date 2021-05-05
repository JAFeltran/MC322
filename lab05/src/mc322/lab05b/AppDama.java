package mc322.lab05b;

public class AppDama
{
    // executa a sequencia de comandos localizada no arquivo de caminho "entrada" e escreve no
    // arquivo "saida" o estado final do tabuleiro
    public static String[] executaJogo(String entrada, String saida)
    {
        CSVHandling csv = new CSVHandling();
        String[] comandos, historico;
        Tabuleiro tabuleiro = new Tabuleiro();
        int len;

        csv.setDataSource(entrada);

        comandos = csv.requestCommands();

        len = comandos.length;

        historico = new String[len + 1];

        historico[0] = tabuleiro.executarComando(null, false);
        // primeiro deve-se imprimir e guardar o estado inicial do tabuleiro

        for (int k = 1; k <= len; k++)
        {
            if (k == len)
            {
                historico[k] = tabuleiro.executarComando(comandos[k - 1], true);
            }
            else
            {
                historico[k] = tabuleiro.executarComando(comandos[k - 1], false);
            }
        }

        tabuleiro.exportarArquivo(saida);

        return historico;
    }

    public static void main(String[] args)
    {
        String[] historico;

        if (args.length == 2)
        {
            historico = executaJogo(args[0], args[1]);
        }
        else
        {
            System.out.println("Devem ser passados dois argumentos");
        }
    }
}
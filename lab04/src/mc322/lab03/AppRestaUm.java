package mc322.lab04;

public class AppRestaUm {
    public static String[] executaJogo(String caminho)
    {
        CSVReader csv = new CSVReader();
        String comandos[], saida[];
        TabuleiroRestaUm tabuleiro = new TabuleiroRestaUm();
        int len;

        csv.setDataSource(caminho);

        comandos = csv.requestCommands();

        len = comandos.length;
        
        saida = new String[len + 1];

        saida[0] = tabuleiro.obterSaida(0, 0, 0, 0, true);

        for (int k = 1; k <= len; k++) {
            saida[k] = tabuleiro.executarComando(comandos[k - 1]);
        }
        
        return saida;
    }

    public static void main(String parametros[])
    {
        String caminho = "mc322/lab04/teste.csv";
        String vetor[];

        vetor = executaJogo(caminho);
    }
}
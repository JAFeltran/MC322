package mc322.lab03;

public class Animacao {
    String sequencia;
    AquarioLombriga lombriga;
    int corrente;

    public Animacao(String sequencia) {
        int tam_lombriga, tam_aquario, fim, cabeca;

        this.sequencia = sequencia;

        tam_aquario = obterInteiro(0);
        tam_lombriga = obterInteiro(2);
        fim = obterInteiro(4);
        cabeca = fim + tam_lombriga - 1;

        this.corrente = 6;
        this.lombriga = new AquarioLombriga(tam_aquario, cabeca, fim);
    }

    public int obterInteiro(int i) {
        char num_char;
        int num_int, dezena;

        num_char = sequencia.charAt(i);

        dezena = 10 * (Character.getNumericValue(num_char));

        num_char = sequencia.charAt(i + 1);

        num_int = (Character.getNumericValue(num_char)) + dezena;

        return num_int;
    }

    public String apresenta() {
        String estado;

        estado = lombriga.apresenta();

        return estado;
    }

    public void passo() {
        char comando;

        comando = sequencia.charAt(corrente);

        if (comando == 'C') {
            lombriga.crescer();
        } else if (comando == 'M') {
            lombriga.mover();
        } else if (comando == 'V') {
            lombriga.virar();
        }

        corrente++;
    }
}
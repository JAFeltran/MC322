package mc322.lab03;

public class AppLab03 {
    public static void main(String parametros[]) {
        String sequencia = "080403MCMVM";
        Animacao anim = new Animacao(sequencia);

        System.out.println(anim.apresenta());

        for (int i = anim.corrente; i < sequencia.length(); i++) {
            anim.passo();
            System.out.println(anim.apresenta());
        }
    }
}
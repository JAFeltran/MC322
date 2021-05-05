# Arquivos Java do Jogo

Para acionar o jogo, na modalidade A, deve-se inserir na variável "caminho" (na função "main" na classe AppDamaA) o caminho para um arquivo do tipo csv que contenha todos os movimentos que serão executados no jogo. Esses movimentos devem ser da forma "ax:by", onde "ax" e "bx" são, respectivamente, as posições inicial e final da peça movida, usando as letras e números das laterais do tabuleiro. Infelizmente, essa versão não está finalizada.

Para acionar o jogo, na modalidade B, deve-se passar como argumentos os caminhos dos arquivos csv de entrada e saída, respectivamente. O primeiro arquivo deve conter movimentos a serem executados, na forma "ax:by", onde "ax" e "by" são, respectivamente, as posições inicial e final da peça. O segundo deve conter um arquivo vazio, onde será escrito o estado final do tabuleiro, ou "erro" caso o último movimento seja inválido.

[Arquivos Java A](src/mc322/lab05a)

[Arquivos Java B](src/mc322/lab05b)

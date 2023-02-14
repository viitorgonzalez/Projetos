import java.util.Scanner;

public class Main {
    
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Campo[][] velha = new Campo[3][3];
        //implementar random para X ou O
        char simboloAtual = 'X';
        Boolean game = true;
        char vitoria = ' ';

        iniciarJogo(velha);
    
        //enquanto game for true
        while(game) {

            desenhaJogo(velha);
            vitoria = verificaVitoria(velha);
            if(vitoria != ' ') {
                System.out.printf("Jogador %s venceu!\n", vitoria);
                break;
            }
            try {
                if(verificaJogada(velha, jogar(scanner, simboloAtual), simboloAtual)) {
                    if(simboloAtual == 'X') {
                        simboloAtual = 'O';
                    } else {
                        simboloAtual = 'X';
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro!");
            }
        }
        System.out.println("Fim do jogo!");
    }

    public static void desenhaJogo(Campo[][] velha) {

        limparTela();
        System.out.println("   0    1    2");
        System.out.printf("0   %c | %c | %c \n", velha[0][0].getSimbolo(), velha[0][1].getSimbolo(), velha[0][2].getSimbolo());
        System.out.println("----------------");
        System.out.printf("1   %c | %c | %c \n", velha[1][0].getSimbolo(), velha[1][1].getSimbolo(), velha[1][2].getSimbolo());
        System.out.println("----------------");
        System.out.printf("2   %c | %c | %c \n", velha[2][0].getSimbolo(), velha[2][1].getSimbolo(), velha[2][2].getSimbolo());
    }

    public static void limparTela() {

        for(int i = 0; i < 200; i++) {
            System.out.println("");
        }
    }

    public static char verificaVitoria(Campo[][] velha) {

        for(int i=0;i<3;i++){
			if((velha[i][0].getSimbolo()=='X' && velha[i][1].getSimbolo()=='X' && velha[i][2].getSimbolo()=='X')||(velha[i][0].getSimbolo()=='O' && velha[i][1].getSimbolo()=='O' && velha[i][2].getSimbolo()=='O')){
				return velha[i][0].getSimbolo();
			}
		}
		for(int i=0;i<3;i++){
			if((velha[0][i].getSimbolo()=='X' && velha[1][i].getSimbolo()=='X' && velha[2][i].getSimbolo()=='X')||(velha[0][i].getSimbolo()=='O' && velha[1][i].getSimbolo()=='O' && velha[2][i].getSimbolo()=='O')){
				return velha[0][i].getSimbolo();
			}
		}
		if((velha[0][0].getSimbolo()=='X' && velha[1][1].getSimbolo()=='X' && velha[2][2].getSimbolo()=='X')||(velha[0][2].getSimbolo()=='O' && velha[1][1].getSimbolo()=='O' && velha[2][0].getSimbolo()=='O')){
			return velha[0][0].getSimbolo();
		}
        return ' ';
    }

    public static Boolean verificaJogada(Campo[][] velha, int p[], char simboloAtual) {
        
        if(velha[p[0]][p[1]].getSimbolo() == ' ') {
            velha[p[0]][p[1]].setSimbolo(simboloAtual);
            return true;
        } else {
            return false;
        }
    }

    public static int[] jogar(Scanner scanner, char simboloAtual) {

        int p[] = new int[2];
        System.out.printf("%s %c \n", "Quem joga:", simboloAtual);
        System.out.print("Informa a linha: ");
        p[0] = scanner.nextInt();
        System.out.print("Informa a coluna: ");
        p[1] = scanner.nextInt();
        return p;
    }

    public static void iniciarJogo(Campo[][] velha) {

        for(int linha = 0; linha < 3; linha++) {
            for(int coluna = 0; coluna < 3; coluna ++) {
                velha[linha][coluna] = new Campo();
            }
        }
    }
}
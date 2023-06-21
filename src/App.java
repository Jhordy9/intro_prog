import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    static char[][] board = new char[6][7];
    static char playerColor;
    static char computerColor;

    public static void main(String[] args) throws Exception {

        init();

        System.out.println("Escolha uma cor: Vermelho (V) ou Azul (A): ");
        playerColor = sc.next().charAt(0);

        if (playerColor == 'V') {
            computerColor = 'A';
        }
        computerColor = 'V';

        sc.close();
    }

    static void init() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = 'B';
            }
        }
    }

    static void showBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    static void playerMove() {
        System.out.println("Escolha uma coluna entre 1 e 6: ");
        int column = sc.nextInt() - 1;

        for (int i = 5; i >= 0; i--) {
            if (board[i][column] == 'B') {
                board[i][column] = playerColor;
                break;
            }
        }
    }

    static void randomComputerMove() {
        int column = (int) (Math.random() * 7);

        for (int i = 5; i >= 0; i--) {
            if (board[i][column] == 'B') {
                board[i][column] = computerColor;
                break;
            }
        }
    }
}

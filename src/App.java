import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static final int LINE = 6;
    private static final int COLUMN = 7;

    private static char[][] board = new char[LINE][COLUMN];
    private static char playerColor;
    private static char computerColor;

    public static void main(String[] args) throws Exception {
        init();

        System.out.println("Escolha uma cor: Vermelho (V) ou Azul (A): ");
        playerColor = Character.toUpperCase(sc.next().charAt(0));

        if (playerColor == 'V') {
            computerColor = 'A';
        } else {
            computerColor = 'V';
        }

        run();

        sc.close();
    }

    static void init() {
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < COLUMN; j++) {
                board[i][j] = 'B';
            }
        }
    }

    static void showBoard() {
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < COLUMN; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    static void playerMove() {
        boolean moveMade = false;

        System.out.println("Escolha uma coluna entre 1 e 7: ");
        int column = sc.nextInt() - 1;

        if (column < 0 || column > 6) {
            System.out.println("Coluna inválida!");
            playerMove();
        } else {
            for (int i = 5; i >= 0; i--) {
                if (board[i][column] == 'B') {
                    board[i][column] = playerColor;
                    moveMade = true;
                    break;
                }
            }
            if (!moveMade) {
                System.out.println("Coluna cheia! Escolha outra.");
                playerMove();
            }
        }

        if (isWin(playerColor)) {
            System.out.println("Você ganhou!");
            System.exit(0);
        }
    }

    static void randomComputerMove() {
        int column = (int) (Math.random() * COLUMN);
        boolean moveMade = false;

        for (int i = 5; i >= 0; i--) {
            if (board[i][column] == 'B') {
                board[i][column] = computerColor;
                moveMade = true;
                break;
            }
        }

        if (!moveMade) {
            randomComputerMove();
        }

        if (isWin(computerColor)) {
            System.out.println("O computador ganhou!");
            System.exit(0);
        }
    }

    public static boolean isWin(char color) {
        // Horizontal
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < COLUMN - 3; j++) {
                if (board[i][j] == color && board[i][j + 1] == color && board[i][j + 2] == color
                        && board[i][j + 3] == color)
                    return true;
            }
        }

        // Vertical
        for (int i = 0; i < LINE - 3; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (board[i][j] == color && board[i + 1][j] == color && board[i + 2][j] == color
                        && board[i + 3][j] == color)
                    return true;
            }
        }

        // Diagonal up
        for (int i = 3; i < LINE; i++) {
            for (int j = 0; j < COLUMN - 3; j++) {
                if (board[i][j] == color && board[i - 1][j + 1] == color && board[i - 2][j + 2] == color
                        && board[i - 3][j + 3] == color)
                    return true;
            }

        }

        // Diagonal down
        for (int i = 3; i < LINE; i++) {
            for (int j = 3; j < COLUMN; j++) {
                if (board[i][j] == color && board[i - 1][j - 1] == color && board[i - 2][j - 2] == color
                        && board[i - 3][j - 3] == color)
                    return true;
            }
        }

        return false;
    }

    static void reset() {
        init();
        run();
    }

    static void runOptions() {
        // Still continue if the option number is different from 1
        System.out.println("Digite 1 para continuar, 2 para ver o tabuleiro ou 0 para resetar o jogo: ");
        int option = sc.nextInt();

        if (option == 2) {
            showBoard();
            runOptions();
        }

        if (option == 0) {
            reset();
        }
    }

    static void run() {
        while (true) {
            playerMove();
            randomComputerMove();

            runOptions();
        }
    }
}

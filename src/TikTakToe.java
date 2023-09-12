import java.util.Scanner;

public class TikTakToe {
    public static void main(String[] args) {
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        int currentPlayer = 1; // Spieler 1 beginnt
        boolean gameFinished = false;

        Scanner scanner = new Scanner(System.in);

        printBoard(board);

        while (!gameFinished) {
            System.out.println("Spieler " + currentPlayer + ", geben Sie Ihre Koordinaten ein (z.B. 1,3): ");
            String input = scanner.nextLine();

            if (isValidInput(input) && isValidMove(board, input)) {
                String[] coordinates = input.split(",");
                int row = Integer.parseInt(coordinates[0]) - 1;
                int col = Integer.parseInt(coordinates[1]) - 1;

                if (currentPlayer == 1) {
                    board[row][col] = 'x';
                    currentPlayer = 2;
                } else {
                    board[row][col] = 'o';
                    currentPlayer = 1;
                }

                printBoard(board);

                if (checkWin(board, 'x')) {
                    System.out.println("Spieler 1 (x) gewinnt!");
                    gameFinished = true;
                } else if (checkWin(board, 'o')) {
                    System.out.println("Spieler 2 (o) gewinnt!");
                    gameFinished = true;
                } else if (isBoardFull(board)) {
                    System.out.println("Unentschieden! Das Spielfeld ist voll.");
                    gameFinished = true;
                }
            } else {
                System.out.println("Ungültige Eingabe. Bitte geben Sie Ihre Koordinaten erneut ein.");
            }
        }

        scanner.close();
    }

    // Überprüfen, ob die Eingabe gültig ist (z.B. "1,3")
    private static boolean isValidInput(String input) {
        return input.matches("[1-3],[1-3]");
    }

    // Überprüfen, ob der Zug gültig ist (Zelle ist leer)
    private static boolean isValidMove(char[][] board, String input) {
        String[] coordinates = input.split(",");
        int row = Integer.parseInt(coordinates[0]) - 1;
        int col = Integer.parseInt(coordinates[1]) - 1;

        return board[row][col] == ' ';
    }

    // Überprüfen, ob ein Spieler gewonnen hat
    private static boolean checkWin(char[][] board, char player) {
        // Überprüfen der Reihen
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // Überprüfen der Spalten
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }

        // Überprüfen der Diagonalen
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        return false;
    }

    // Überprüfen, ob das Spielfeld voll ist (Unentschieden)
    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Spielfeld ausgeben
    private static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }
}

package portfolio.games;

import java.util.Scanner;

/** Console two-player Tic Tac Toe. */
public class TicTacToeGame {
    private final char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public static void main(String[] args) {
        new TicTacToeGame().play();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = 'X';

        for (int turns = 0; turns < 9; turns++) {
            printBoard();
            makeMove(scanner, currentPlayer);
            if (hasWinner(currentPlayer)) {
                printBoard();
                System.out.println(currentPlayer + " wins.");
                return;
            }
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
        }

        printBoard();
        System.out.println("Tie game.");
    }

    private void makeMove(Scanner scanner, char player) {
        while (true) {
            System.out.print("Player " + player + ", enter row and column (1-3 1-3): ");
            int row = scanner.nextInt() - 1;
            int column = scanner.nextInt() - 1;
            if (row >= 0 && row < 3 && column >= 0 && column < 3 && board[row][column] == ' ') {
                board[row][column] = player;
                return;
            }
            System.out.println("Invalid move. Try again.");
        }
    }

    private boolean hasWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        return board[0][0] == player && board[1][1] == player && board[2][2] == player
                || board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    private void printBoard() {
        System.out.println();
        for (int row = 0; row < 3; row++) {
            System.out.println(" " + board[row][0] + " | " + board[row][1] + " | " + board[row][2]);
            if (row < 2) {
                System.out.println("---+---+---");
            }
        }
    }
}

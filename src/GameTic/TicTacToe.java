package GameTic;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private final char[][] gameBoard = new char[3][3];
    private final Player[] players = new Player[2];
    private final Move moves = new Move();
    private int playerTurn = 0;

    private void createGameBoard() {
        for (char[] chars : gameBoard) {
            Arrays.fill(chars, ' ');
        }
    }

    public void startPlaying() {
        createGameBoard();
        createPlayer();
        printGameBoard();

        while (!gameBoardFull() && !verifyWinner()) {
            registerPlay(players[playerTurn], moves);

            if (makePlays(players[playerTurn], moves)) {
                printGameBoard();
                playerTurn = (playerTurn + 1) % 2;
            } else {
                System.out.println("Invalid move. try again");
            }
        }

        if (gameBoardFull()) {
            Player winner;
            winner = players[(playerTurn + 1) % 2];
            System.out.println("The player " + winner.getName() + " won, Congratulations.");

        } else {
            System.out.println("No one won !");
        }

    }

    private void createPlayer() {
        players[0] = new Player();
        players[0].setName("A");
        players[0].setSymbol('X');

        players[1] = new Player();
        players[1].setName("B");
        players[1].setSymbol('O');
    }


    private void registerPlay(Player x, Move move) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Player " + x.getName() + " => Line");
        move.setLine(sc.nextInt());
        System.out.println("Player " + x.getName() + " => Column");
        move.setColumn(sc.nextInt());

    }


    private boolean makePlays(Player player, Move move) {
        if (gameBoard[move.getLine()][move.getColumn()] != ' ') {
            return false;
        }

        gameBoard[move.getLine()][move.getColumn()] = player.getSymbol();
        return true;
    }

    private boolean verifyWinner() {
        //Diagonal 1
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] != ' ') {
            return true;
        }
        //Diagonal 2
        else if (gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] && gameBoard[0][2] != ' ') {
            return true;
        }
        //Column 1
        else if (gameBoard[0][0] == gameBoard[1][0] && gameBoard[0][0] == gameBoard[2][0] && gameBoard[2][0] != ' ') {
            return true;
        }
        //Column 2
        else if (gameBoard[0][1] == gameBoard[1][1] && gameBoard[0][1] == gameBoard[2][1] && gameBoard[0][1] != ' ') {
            return true;
        }
        //Column 3
        else if (gameBoard[0][2] == gameBoard[1][2] && gameBoard[0][2] == gameBoard[2][2] && gameBoard[0][2] != ' ') {
            return true;
        }
        //Line 1
        else if (gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][0] == gameBoard[0][2] && gameBoard[0][0] != ' ') {
            return true;
        }
        //Line 2
        else if (gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][0] == gameBoard[1][2] && gameBoard[1][0] != ' ') {
            return true;
        }
        //Line 3
        else if (gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][0] == gameBoard[2][2] && gameBoard[2][0] != ' ') {
            return true;
        }

        return false;
    }

    private boolean gameBoardFull() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }


    public void printGameBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (j <= 1) {
                    System.out.print(gameBoard[i][j] + " | ");
                } else {
                    System.out.print(gameBoard[i][j]);
                }
            }
            System.out.println();
        }
    }
}

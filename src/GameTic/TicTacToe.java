package GameTic;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private final char[][] gameBoard = new char[3][3];
    private final Player[] players = new Player[2];
    private final Move moves = new Move();
    protected int playerTurn = 0;
    int p1 = 0,p2 = 0, tied = 0;


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

        if (verifyWinner()) {                   // gameBoardFull()   <--- * --->     verifyWinner()
            Player winner;
            winner = players[(playerTurn + 1) % 2];
            System.out.println("The player " + winner.getName() + " won, Congratulations."); // Winner
            if(winner.getName() == "A [X]"){
                p1++;
            }else if (winner.getName() == "B [O]"){
                p2++;
            }
        }else{
            tied++;
            System.out.println("No one won !");
        }

    }

    protected void contVictories(){

    }


    private void createPlayer() {
        players[0] = new Player();
        players[0].setName("A [X]");
        players[0].setSymbol('X');

        players[1] = new Player();
        players[1].setName("B [O]");
        players[1].setSymbol('O');
    }


    private void registerPlay(Player x, Move move) {
        Scanner sc = new Scanner(System.in);
        int line = 0, column = 0;
        System.out.print("Player " + x.getName() + " => Line : ");
        line = sc.nextInt();
        if(line >= 1 && line <= 3){
            line--;
            move.setLine(line);
        }

        System.out.print("Player " + x.getName() + " => Column : ");
        column = sc.nextInt();
        if(column >= 1 && column <=3){
            column--;
            move.setColumn(column);
        }


    }


    private boolean makePlays(Player player, Move move) {
        if (gameBoard[move.getLine()][move.getColumn()] != ' ') {
            return false;
        }

        gameBoard[move.getLine()][move.getColumn()] = player.getSymbol();
        return true;
    }

    protected boolean verifyWinner() {
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

    private boolean gameBoardFull() {                       //  !gameBoardFull()  == false
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
                if (j <= 1) {                           //      Column in i = 0, i = 1
                    System.out.print(gameBoard[i][j] + " | ");
                } else {
                    System.out.print(gameBoard[i][j]);
                }
            }
            System.out.println();
        }
    }


    public void cleanMove(){
        for (int i = 0; i <= gameBoard.length; i++) {
            for (int j = 0; j <= gameBoard[i].length; j++) {
                gameBoard[i][j] = '\u0000';
                playerTurn = 0;
            }
        }
    }

}
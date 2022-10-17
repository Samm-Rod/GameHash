package GameTic;

import java.util.Objects;
import java.util.Scanner;

public class GameHash {
    public static void main(String[] args) {
        TicTacToe gamePlay = new TicTacToe();


        Scanner sc = new Scanner(System.in);
        boolean win;
        win = !gamePlay.verifyWinner();
        char repeat;

        do{
            gamePlay.startPlaying(); //         Start Game !

            System.out.print("Do you wanna play again [Y/N]: ");
            repeat = sc.next().charAt(0);

            try{
                if(Objects.equals(repeat,'y')){
                    gamePlay.cleanMove();
                    win = false;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println();
            }

        }while(Objects.equals(repeat,'y'));
        System.out.println(" --- SEE YOU LATER ! --- ");
        System.out.println("Player A[X] Victories : "+gamePlay.p1++);
        System.out.println("Player B[O] Victories : "+gamePlay.p2++);
        System.out.println("Tied : "+gamePlay.tied++);
    }

}

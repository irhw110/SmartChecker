import java.util.Scanner;

public class Gameplay {
    public static void main(String args[]) {
        Board b = new Board();
        RandomBot rb = new RandomBot();
        b.showBoard();
        while (b.isEndGame() == 0) {

            // White turn, player
            System.out.println("Giliran sekarang: "+State.WTURN);
            Scanner inp = new Scanner(System.in);
            int currentX = inp.nextInt();
            int currentY = inp.nextInt();
            int moveX = inp.nextInt();
            int moveY = inp.nextInt();
            System.out.println(currentX+' '+currentY+' '+moveX+' '+moveY);
            b.movePawn(currentX, currentY, moveX, moveY);
            b.showBoard();
            

            //Black turn, random
            rb.moveRandomly(b);
            System.out.println("Giliran sekarang: "+State.WTURN);
            b.showBoard();
        }
        if (b.isEndGame() == 1) {
            System.out.println("Putih menang gan");
        } else {
            System.out.println("Hitam menang gan");
        }
    }
}
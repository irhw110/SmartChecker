import java.util.Scanner;

public class Gameplay {
    public static void main(String args[]) {
        Board b = new Board();
        RandomBot rb = new RandomBot();
        b.showBoard();
        UI ui = new UI();
        while (b.isEndGame() == 0) {

            // White turn, player
            System.out.println("Giliran sekarang: "+State.WTURN);
            Scanner inp = new Scanner(System.in);
            int currentX, currentY, moveX, moveY;
            do {
                currentX = inp.nextInt();
                currentY = inp.nextInt();
                moveX = inp.nextInt();
                moveY = inp.nextInt();
            } while (!b.isLegalMove(currentX, currentY, moveX, moveY));
            b.movePawn(currentX, currentY, moveX, moveY);
            b.showBoard();
            ui.updateUI(b);
            

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
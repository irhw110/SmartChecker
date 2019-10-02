import java.util.Scanner;

// Class yang menghandle gameplay utama
public class Gameplay {
    public static void main(String args[]) throws InterruptedException {
        Scanner inp = new Scanner(System.in);
        int player1;
        int player2;

        // Player 1 configuration
        do {
            System.out.println("Player 1 as:\n1. Human\n2. Random");
            player1 = inp.nextInt();
        } while (player1<1 || player1>2);

        // Player 2 configuration
        do {
            System.out.println("Player 2 as:\n1. AI\n2. Random");
            player2 = inp.nextInt();
        } while (player2<1 || player2>2);

        Board b = new Board();
        b.showBoard();
        
        UI ui = new UI();
        ui.updateUI(b);
        Thread.sleep(500);
        while (b.isEndGame() == 0) {

            // Player 1 turn
            switch (player1) {
                case 1:
                    int currentX, currentY;
                    int moveX, moveY;
                    do {
                        currentX = inp.nextInt();
                        currentY = inp.nextInt();
                        moveX = inp.nextInt();
                        moveY = inp.nextInt();
                    } while (!b.isLegalMove(currentX, currentY, moveX, moveY));
                    b.movePawn(currentX, currentY, moveX, moveY);
                    break;
                case 2:
                    RandomBot rb = new RandomBot();
                    rb.moveRandomly(b);
                    break;
            }
            b.showBoard();
            ui.updateUI(b);
            Thread.sleep(500);
            if (b.isEndGame() != 0) break;  

            // Player 2 turn
            switch (player2) {
                case 1:
                    AlphaBeta ab = new AlphaBeta();
                    ChosenMove ctemp= new ChosenMove(0,0,0);
                    ctemp.CopyChosenMove(ab.alphabeta(b));
                
                    int[] move = RandomBot.moveTo(b.getBlackPawns().getXFrom(ctemp.idpawn),b.getBlackPawns().getYFrom(ctemp.idpawn),ctemp.move);
                    b.movePawn(b.getBlackPawns().getXFrom(ctemp.idpawn),b.getBlackPawns().getYFrom(ctemp.idpawn), move[0], move[1]);
        
                    break;
                case 2:
                    RandomBot rb = new RandomBot();
                    rb.moveRandomly(b);
                    break;
            }
            ui.updateUI(b);
            Thread.sleep(500);

        }
        if (b.isEndGame() == 1) {
            System.out.println("Putih menang gan");
        } else {
            System.out.println("Hitam menang gan");
        }
        inp.close();
    }
}
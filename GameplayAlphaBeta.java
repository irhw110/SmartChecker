import java.util.Scanner;

public class GameplayAlphaBeta {
    public static void main(String args[]) throws InterruptedException {
        Board b = new Board();
        AlphaBeta ab = new AlphaBeta();
        ChosenMove ctemp= new ChosenMove(0,0,0);
        b.showBoard();

        
        UI ui = new UI();
        ui.updateUI(b);
        while (b.isEndGame() == 0) {

            // White turn, player
            State.WTURN = true;
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
            ui.updateUI(b);
            Thread.sleep(500);
            if (b.isEndGame() != 0) break;
                
                   

            //Black turn, alphabeta
            System.out.println(" wturn 2 " +State.WTURN);
            ctemp.CopyChosenMove(ab.alphabeta(b));
            System.out.println(" wturn 3 " +State.WTURN);

            System.out.println("Move pilihan "+b.getBlackPawns().getXFrom(ctemp.idpawn) +" "+b.getBlackPawns().getYFrom(ctemp.idpawn)+" "+ctemp.move);
            int[] move = RandomBot.moveTo(b.getBlackPawns().getXFrom(ctemp.idpawn),b.getBlackPawns().getYFrom(ctemp.idpawn),ctemp.move);
            b.movePawn(b.getBlackPawns().getXFrom(ctemp.idpawn),b.getBlackPawns().getYFrom(ctemp.idpawn), move[0], move[1]);

            System.out.println("Giliran sekarang: "+State.WTURN);
            b.showBoard();
            ui.updateUI(b);
        }
        if (b.isEndGame() == 1) {
            System.out.println("Putih menang gan");
        } else {
            System.out.println("Hitam menang gan");
        }
    }
}
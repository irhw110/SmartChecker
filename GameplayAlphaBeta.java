import java.util.Scanner;

public class GameplayAlphaBeta {
    public static void main(String args[]) throws InterruptedException {
        Board b = new Board();
        AlphaBeta ab = new AlphaBeta();
        ChosenMove ctemp= new ChosenMove(0,0,0);
        b.showBoard();
        
        UI ui = new UI();
        ui.updateUI(b);
        System.out.println("Giliran sekarang: "+b.getWTURN());
        while (b.isEndGame() == 0) {

            // White turn, player
            
            Scanner inp = new Scanner(System.in);
            int currentX, currentY, moveX, moveY;
            do {
                currentX = inp.nextInt();
                currentY = inp.nextInt();
                moveX = inp.nextInt();
                moveY = inp.nextInt();
            } while (!b.isLegalMove(currentX, currentY, moveX, moveY));
            b.movePawn(currentX, currentY, moveX, moveY);
            System.out.println("Giliran sekarang: "+b.getWTURN());
            ui.updateUI(b);
            Thread.sleep(500);
            if (b.isEndGame() != 0) break;    
                   

            //Black turn, alphabeta
            ctemp.CopyChosenMove(ab.alphabeta(b));
            
            int[] move = RandomBot.moveTo(b.getBlackPawns().getXFrom(ctemp.idpawn),b.getBlackPawns().getYFrom(ctemp.idpawn),ctemp.move);
            System.out.println("move pilihan "+b.getBlackPawns().getXFrom(ctemp.idpawn) +" "+b.getBlackPawns().getYFrom(ctemp.idpawn)+" "+move[0]+" "+move[1]);
            b.movePawn(b.getBlackPawns().getXFrom(ctemp.idpawn),b.getBlackPawns().getYFrom(ctemp.idpawn), move[0], move[1]);

            System.out.println("Giliran sekarang: "+b.getWTURN());
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
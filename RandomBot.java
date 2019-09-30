import java.util.Random;

public class RandomBot {
    private static int moveX[]= new int[] {1, -1,  1, -1, 2, -2,  2, -2};
    private static int moveY[]= new int[] {1, -1,  1, -1, 2, -2,  2, -2};

    public static int[] moveTo(int curX, int curY, int idxMove) {
        return new int[] {curX-moveX[idxMove], curY-moveY[idxMove]};
    }

    public void moveRandomly(Board b) {
        Random randIdx = new Random(); 
        Random randMove = new Random(); 
        int[] moves = new int[2];
        int x, y;

        do {
            int idx = randIdx.nextInt(12);
            int move = randMove.nextInt(8);
            if (State.WTURN) {
                ArrayOfPawn whitePawns = b.getWhitePawns();
                x = whitePawns.getXFrom(idx);
                y = whitePawns.getYFrom(idx);
                moves = moveTo(x, y, move);
            } else {
                ArrayOfPawn blackPawns = b.getBlackPawns();
                x = blackPawns.getXFrom(idx);
                y = blackPawns.getYFrom(idx);
                moves = moveTo(x, y, move);
            }

        } while (!b.isLegalMove(x, y, moves[0], moves[1]));

        b.movePawn(x, y, moves[0], moves[1]);
    }
}
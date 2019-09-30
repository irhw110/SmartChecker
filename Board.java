import java.lang.Math;
import java.util.Scanner;

public class Board {
    final int width;
    final int height;
    private int board[][];

    // Constructor for 8x8 board
    public Board() {
        width = 8;
        height = 8;
        board = new int[height][width];

        // Initialize all pawns on the board
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // White Pawns
                if ((i == 0 || i == 2) && (j % 2 == 0) ||
                    (i == 1 && j % 2 == 1)) {
                    board[i][j] = State.WHITE;
                // Black Pawns
                } else
                if ((i == 5 || i == 7) && (j % 2 == 1) ||
                    (i == 6 && j % 2 == 0)) {
                    board[i][j] = State.BLACK;
                } else {
                    board[i][j] = State.NOPAWN;
                }
            }
        }

        // Initialize White is going to move first
        State.WTURN = true;
    }

    
    // Check if a move is legal or not
    public boolean IsLegalMove(int current, int move){
        // First, we're going to get 
        int x = current/10;
        int y = current%10;
        int x1 = move/10;
        int y1 = move%10;
        // Check if the x and y index is not out of bound        
        if ((x>=0 && x<=7)&&(y>=0 && y<=7) &&
            (x1>=0 && x1<=7)&&(y1>=0 && y1<=7)) {
            // Check if the player is choosing the right pawn
            if (((board[x][y] == State.WHITE || board[x][y] == State.WKING) && State.WTURN) ||
                ((board[x][y] == State.BLACK || board[x][y] == State.BKING) && !State.WTURN)) {
                // Check whether the move state is empty or not
                if (board[x1][y1] == State.NOPAWN) {
                    if (Math.abs(x1-x) == 1 && Math.abs(y1-y) == 1) {
                        return true;
                    }
                }
            }
        }
        // if ()
        return false;
    }

    boolean IsCanEat() {

    }

    // Show the current checkers board
    public void ShowBoard(){
        for (int i=height-1; i>=0; i--) {
            for (int j=0; j<width; j++) {
                System.out.print(" "+board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public int GetHeight() {
      return this.height;
    }

    public int GetWidth() {
      return this.width;
    }

    public int GetPawn(int x, int y) {
        return this.board[x][y];
    }

    // Driver
    public static void main(String args[]) {
        Board b = new Board();
        b.ShowBoard();
        int current;
        int move;
        Scanner inp = new Scanner(System.in);
        current = inp.nextInt();
        move = inp.nextInt();
        System.out.println(b.IsLegalMove(current, move));
    }
}

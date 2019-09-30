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
        int x2 = 0;
        int y2 = 0;

        /* pengisian x2 dengan diantara x1 dan x untuk kasus selisih abs x dan x1 adalah 2 */
        if (x1<x) {
          x2 = x-1;
        } else if (x1>x) {
          x2 = x+1;
        }

        /* pengisian y2 dengan diantara y1 dan y untuk kasus selisih abs y dan y1 adalah 2 */
        if (y1<y) {
          y2 = y-1;
        } else if (y1>y) {
          y2 = y+1;
        }

        // Check if the x and y index is not out of bound
        if ((x>=0 && x<=7)&&(y>=0 && y<=7) &&
            (x1>=0 && x1<=7)&&(y1>=0 && y1<=7)) {
            // Check if the player is choosing the right pawn
            if (((board[x][y] == State.WHITE || board[x][y] == State.WKING) && State.WTURN) ||
                ((board[x][y] == State.BLACK || board[x][y] == State.BKING) && !State.WTURN)) {
                // Check whether the move state is empty or not
                if (board[x1][y1] == State.NOPAWN) {
                    /* kasus gerak satu langkah */
                    if (Math.abs(x1-x) == 1 && Math.abs(y1-y) == 1) {
                        if (board[x][y] == State.WHITE && y1>y) {
                            return true;
                        } else if (board[x][y] == State.BLACK && y1<y) {
                            return true;
                        } else if (board[x][y] == State.BKING || board[x][y] == State.WKING) {
                            return true;
                        }
                    /* kasus makan */
                    } else if (Math.abs(x1-x) == 2 && Math.abs(y1-y) == 2) {
                        if (board[x][y] == State.WHITE && y1>y && (board[x2][y2] == State.BLACK || board[x2][y2] == State.BKING)) {
                            return true;
                        } else if (board[x][y] == State.BLACK && y1<y && (board[x2][y2] == State.WHITE || board[x2][y2] == State.WKING)) {
                            return true;
                        } else if (board[x][y] == State.WKING && (board[x2][y2] == State.BLACK || board[x2][y2] == State.BKING)) {
                            return true;
                        } else if (board[x][y] == State.BKING && (board[x2][y2] == State.WHITE || board[x2][y2] == State.WKING)) {
                            return true;
                        }
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

import java.lang.Math;

public class Board {
    private int width;
    private int height;
    private int board[][];
    private ArrayOfPawn whitePawns;
    private ArrayOfPawn blackPawns;

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
        
        whitePawns = new ArrayOfPawn(State.COlORWHITE, 8);
        blackPawns = new ArrayOfPawn(State.COLORBLACK, 8);
    }

    public void copy(Board b) {
        // System.out.println("Copy constructor called");
        height = b.height;
        width = b.width;
        for (int i=height-1; i>=0; i--) {
            for (int j=0; j<width; j++) {
                setBoard(i,j,b.getPawn(i,j));
            }
        }
        for (int k=0; k<b.whitePawns.getLength(); k++) {
            whitePawns.setPawn(k,b.whitePawns.getXFrom(k),b.whitePawns.getYFrom(k));
        }
        for (int l=0; l<b.whitePawns.getLength(); l++) {
            blackPawns.setPawn(l,b.blackPawns.getXFrom(l),b.blackPawns.getYFrom(l));
        }
    }

    public int[] SplitMove(int move){
        int x1 = move/10;
        int y1 = move%10;
        return new int[] {x1, y1};
    }


    // Check if a move is legal or not
    public boolean isLegalMove(int x, int y, int x1, int y1){
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
                        if (board[x][y] == State.WHITE && x1>x) {
                            return true;
                        } else if (board[x][y] == State.BLACK && x1<x) {
                            return true;
                        } else if (board[x][y] == State.BKING || board[x][y] == State.WKING) {
                            return true;
                        }
                    /* kasus makan */
                    } else if (Math.abs(x1-x) == 2 && Math.abs(y1-y) == 2) {
                        if (board[x][y] == State.WHITE && x1>x && (board[x2][y2] == State.BLACK || board[x2][y2] == State.BKING)) {
                            return true;
                        } else if (board[x][y] == State.BLACK && x1<x && (board[x2][y2] == State.WHITE || board[x2][y2] == State.WKING)) {
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

    public void movePawn(int x, int y, int x1, int y1) {
        if (isLegalMove(x, y, x1, y1)){
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

            // Kasus 1 update nilai
            if (State.WTURN && (Math.abs(x1-x) == 1 && Math.abs(y1-y) == 1)) {
                int i = whitePawns.findPawn(x, y);
                whitePawns.setPawn(i,x1,y1);
            } else if (!State.WTURN && (Math.abs(x1-x) == 1 && Math.abs(y1-y) == 1)) {
                int i = blackPawns.findPawn(x, y);
                blackPawns.setPawn(i,x1,y1);
            // Kasus 2 hapus nilai
            } else if (State.WTURN) {
                int i = whitePawns.findPawn(x, y);
                whitePawns.setPawn(i,x1,y1);
                blackPawns.deletePawn(x2,y2);
                board[x2][y2] = State.NOPAWN;
            } else {
                int i = blackPawns.findPawn(x, y);
                blackPawns.setPawn(i,x1,y1);
                whitePawns.deletePawn(x2,y2);
                board[x2][y2] = State.NOPAWN;
            }
            // Check if a pawn has reached the edge of the board
            if (State.WTURN && x1==7) {
                board[x1][y1] = State.WKING;
                int i = whitePawns.findPawn(x1, y1);
                whitePawns.setPawnState(i, State.WKING);
            } else if (!State.WTURN && x1==0) {
                board[x1][y1] = State.BKING;
                int i = whitePawns.findPawn(x1, y1);
                whitePawns.setPawnState(i, State.BKING);
            } else {
                board[x1][y1] = board[x][y];
            }

            board[x][y] = State.NOPAWN;
            State.WTURN = !State.WTURN;
        }
    }

    // Show the current checkers board
    public void showBoard(){
        for (int i=height-1; i>=0; i--) {
            for (int j=0; j<width; j++) {
                System.out.print(" "+board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public ArrayOfPawn getWhitePawns() {
        return whitePawns;
    }

    public ArrayOfPawn getBlackPawns() {
        return blackPawns;
    }

    public int getHeight() {
      return this.height;
    }

    public int getWidth() {
      return this.width;
    }

    public int getPawn(int x, int y) {
        return this.board[x][y];
    }

    public void setBoard(int x, int y, int val) {
        this.board[x][y] = val;
    }

    // return 0 if The game hasn't ended yet,
    // return 1 if White wins the game
    // return -1 if Black wins the game
    public int isEndGame() {
        if (whitePawns.getLength() == 0)
            return 1;
        else if (blackPawns.getLength() == 0)
            return -1;
        return 0;
    }
}

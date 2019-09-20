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
    }

    // Show the current checkers board
    public void ShowBoard(){
        for (int i=0; i<height; i++) {
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
    }
}

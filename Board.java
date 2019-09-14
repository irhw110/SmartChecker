public class Board{
    private int width;
    private int height;
    private int board[][];

    public Board(int width, int height){
        this.width = width;
        this.height = height;

        board = new int[height][width];
        for (int i=0; i<height; i++){
            for (int j=0; j< width; j++) {
                board[i][j] = State.NOPAWN;
            }
        }
    }
    
    
}
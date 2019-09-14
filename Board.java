import Pawn.java;

public class Board{
    private int width;
    private int height;
    private Pawn whitePawn[];
    private Pawn blackPawn[];

    public Board(int width, int height, int countWhitePawn, int countBlackPawn){
        this.width = width;
        this.height = height;
        this.whitePawn = new Pawn[countWhitePawn];
        for (i=0;i<countWhitePawn;i++){
            whitePawn[i] = new Pawn();
        }

        this.blackPawn = new Pawn[countBlackPawn];
        for (i=0;i<countWhitePawn;i++){
            blackPawn[i] = new Pawn();
        }
    }
    
    
}
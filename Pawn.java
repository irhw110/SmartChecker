import Point.java;

public class Pawn{
    private Point posisi;
    private boolean isKing;

    public Pawn(Point posisi, boolean isKing){
        this.posisi = posisi;
        this.isKing = false;
    }

    public setKing(){
        this.isKing = true;
    }

    public setPosisi(int x, int y){
        this.posisi.setX(x);
        this.posisi.setY(y);
    }
}
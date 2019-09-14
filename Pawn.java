public class Pawn{
    private Point posisi;
    private boolean isKing;

    public Pawn(Point posisi, boolean isKing){
        this.posisi = posisi;
        this.isKing = isKing;
    }

    public void setKing(){
        this.isKing = true;
    }

    public void setPosisi(int x, int y){
        this.posisi.setX(x);
        this.posisi.setY(y);
    }

    public Point getPosisi(){
        return posisi;
    }

}
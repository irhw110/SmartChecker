public class Pawn{
    private int x;
    private int y;
    private int state;

    public Pawn(int x, int y, int state){
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setState(int state){
        this.state = state;
    }
    
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getState(){
        return this.state;
    }

}
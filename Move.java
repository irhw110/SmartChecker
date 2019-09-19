public class Move {

    public Board[] NextMove;

    public boolean CheckPosition(int x,int y) {
        if((x>=0 && x<=7)&&(y>=0 && y<=7))  {
            return true;
        }
        return false;
    }

    public Board[] GenerateAllMove(Board B,int side)    {
        
    }
    
    public void CheckMovePawnLeft(Board B, int x,int y,int side) {
        //player top
        if(side =0 )    {
            if(CheckPosition(x+1,y-1))  {

            }
        }
        //Player bottom
        else{
            if(CheckPosition(x-1,y+1))  {

            }
        }
    }
}
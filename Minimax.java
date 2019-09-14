

public class Minimax {

    final int MAXDEPTH;

    private int depth;
    private int chosenmove;
    private Board current_checked_board;


    public void minimax(Board B)   {
        depth=0;
        int temp_max=Integer.MIN_VALUE;

        //generate all possible Board
            //for each board assign current_checked_board with generated board
            //find board with highest value and save the Board in current_checked_Board
        //kayaknya harus generate all possible move di file ini
        return current_checked_board;
    }

    public int find_max_recursive()   {
        int temp_max=Integer.MIN_VALUE;
        if(depth<MAXDEPTH)  {
            depth++;
            //generate all possible Board
                //for each board assign current_checked_board with generated board
                //find board with highest value
            //eval
            //cari max
        }
        depth--;
        
        return temp_max;
    }

    public int find_min_recursive()   {
        int temp_min=Integer.MAX_VALUE;
        if(depth<MAXDEPTH)  {
            depth++;
            //for all possible move
            //eval find_max_recursive
            //cari min
        }
        depth--;
        return temp_min;
    }

    public int max(int a,int b)    {
        if(a>b) {
            return a;
        }
        else return b;
    }

    public int min(int a,int b)    {
        if(a<b) {
            return a;
        }
        else return b;
    }

    public static void main(String[] args)  {

    }
}
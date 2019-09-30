public class Minimax {
    
    final int MAXDEPTH = 3;

    private int depth;
    private ChosenMove chosenmove;
    // private Board current_checked_board;
    private int current_checked_board;


    public ChosenMove minimax()   {
        depth=1;
        ChosenMove temp_max= new ChosenMove(0,0,Integer.MIN_VALUE); //initialize value with MIN_INTEGER
        ChosenMove[] arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);
        arrayset=generateAllMove();

        for(int i=0; i < arrayset.length; i++) {
            temp.CopyChosenMove(find_min_recursive(arrayset[i]));
            if(temp.value > temp_max.value) {
                temp_max.CopyChosenMove(temp);
            }
        }
        //generate all possible Board
            //for each board assign current_checked_board with generated board
            //find board with highest value and save the Board in current_checked_Board
        //kayaknya harus generate all possible move di file ini
        return temp_max;
    }

    public ChosenMove find_max_recursive(ChosenMove C)   {
        
        ChosenMove temp_max= new ChosenMove(0,0,Integer.MIN_VALUE); //initialize value with MIN_INTEGER
        ChosenMove[] arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);
        arrayset=generateAllMove();

        depth++;
        
        if(depth<MAXDEPTH)  {
            
            for(int i=0;i<arrayset.length;i++) {
                temp.CopyChosenMove(find_min_recursive(arrayset[i]));
                if(temp.value > temp_max.value) {
                    temp_max.CopyChosenMove(temp);
                }
            }
            //generate all possible Board
                //for each board assign current_checked_board with generated board
                //find board with highest value
            //eval
            //find max
        }
        else {
            for(int i=0;i<arrayset.length;i++) {
                if(arrayset[i].value > temp_max.value) {
                    temp_max.CopyChosenMove(arrayset[i]);
                }
            }
            // Iterate generateAllMoves
            // find max
        }
        depth--;
        
        return temp_max;
    }

    public ChosenMove find_min_recursive(ChosenMove C)   {
        ChosenMove temp_min= new ChosenMove(0,0,Integer.MAX_VALUE); //initialize value with MAX_INTEGER
        ChosenMove[] arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);
        arrayset=generateAllMove();
        
        depth++;

        if(depth<MAXDEPTH)  {
            
            //for all possible move
            //eval find_max_recursive
            //cari min
            for(int i=0; i<arrayset.length; i++) {
                temp.CopyChosenMove(find_max_recursive(arrayset[i]));
                if(temp.value < temp_min.value) {
                    temp_min.CopyChosenMove(temp);
                }
            }
        }
        else {
            // Iterate generateAllMoves
            // find min
            for(int i=0; i<arrayset.length; i++) {
                if(arrayset[i].value < temp_min.value) {
                    temp_min.CopyChosenMove(arrayset[i]); 
                }
            }
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

    public ChosenMove[] generateAllMove() {
        return null; 
    }
    

    public static void main(String[] args)  {
        Minimax M = new Minimax();
        System.out.println("Hasil = " + M.minimax());
    }
}
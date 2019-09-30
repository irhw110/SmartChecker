public class AlphaBeta {
    
    final int MAXDEPTH = 3;

    private int depth;
    private ChosenMove chosenmove;
    // private Board current_checked_board;
    private int current_checked_board;


    public ChosenMove alphabeta()   {
        depth=1;
        ChosenMove temp_max= new ChosenMove(); //initialize value with MIN_INTEGER
        ChosenMove[] arrayset;
        ChosenMove temp;
        arrayset=generateAllMove();
        int alpha= Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;


        for(int i=0; i < arrayset.length; i++) {
            temp = find_min_recursive(arrayset[i],alpha,beta);
            if(temp.value > temp_max.value) {
                temp_max = temp;
            }
            alpha = max( alpha, temp_max.value);
            if (beta <= alpha)
                break;
        }
        
        //generate all possible Board
            //for each board assign current_checked_board with generated board
            //find board with highest value and save the Board in current_checked_Board
        //kayaknya harus generate all possible move di file ini
        return temp_max;
    }

    public ChosenMove find_max_recursive(ChosenMove C,int alpha,int beta)   {
        
        ChosenMove temp_max= new ChosenMove(); //initialize value with MIN_INTEGER
        ChosenMove[] arrayset;
        ChosenMove temp;
        arrayset=generateAllMove();

        depth++;
        
        if(depth<MAXDEPTH)  {
            
            for(int i=0;i<arrayset.length;i++) {
                temp = find_min_recursive(arrayset[i],alpha,beta);
                if(temp.value > temp_max.value) {
                    temp_max = temp;
                }
                alpha = max( alpha, temp_max.value);
                if (beta <= alpha)
                    break;
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
                    temp_max = arrayset[i];
                }
            }
            // Iterate generateAllMoves
            // find max
        }
        depth--;
        
        return temp_max;
    }

    public ChosenMove find_min_recursive(ChosenMove C,int alpha, int beta)   {
        ChosenMove temp_min= new ChosenMove(); //initialize value with MAX_INTEGER
        ChosenMove[] arrayset;
        ChosenMove temp;
        arrayset=generateAllMove();
        
        depth++;

        if(depth<MAXDEPTH)  {
            
            //for all possible move
            //eval find_max_recursive
            //cari min
            for(int i=0; i < arrayset.length; i++) {
                temp = find_max_recursive(arrayset[i],alpha,beta);
                if(temp.value < temp_min.value) {
                    temp_min = temp;
                }
                beta = min( beta, temp_min.value);
                if (beta <= alpha)
                    break;
            }
        }
        else {
            // Iterate generateAllMoves
            // find min
            for(int i=0; i < arrayset.length; i++) {
                if(arrayset[i].value < temp_min.value) {
                    temp_min = arrayset[i]; 
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
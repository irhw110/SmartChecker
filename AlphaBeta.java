import java.util.*;

public class AlphaBeta {

    final int MAXDEPTH = 3;

    private int depth;
    private ChosenMove chosenmove;
    // private Board current_checked_board;
    private int current_checked_board;
    private int beta;
    private int alpha;


    public ChosenMove alphabeta()   {
        depth=1;
        ChosenMove temp_max= new ChosenMove(0,0,Integer.MIN_VALUE); //initialize value with MIN_INTEGER
        ChosenMove[] arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);
        // arrayset=generateAllMove();
        int alpha= Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;


        // for(int i=0; i < arrayset.length; i++) {
        //     temp.CopyChosenMove(find_min_recursive(arrayset[i]));
        //     if(temp.value > temp_max.value) {
        //         temp_max.CopyChosenMove(temp);
        //     }
        //     alpha = max( alpha, temp_max.value);
        //     if (beta <= alpha)
        //         break;
        // }

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
        // arrayset=generateAllMove();

        depth++;

        if(depth<MAXDEPTH)  {

            // for(int i=0;i<arrayset.length;i++) {
            //     temp.CopyChosenMove(find_min_recursive(arrayset[i]));
            //     if(temp.value > temp_max.value) {
            //         temp_max.CopyChosenMove(temp);
            //     }
            //     alpha = max( alpha, temp_max.value);
            //     if (beta <= alpha)
            //         break;
            // }
            //generate all possible Board
                //for each board assign current_checked_board with generated board
                //find board with highest value
            //eval
            //find max
        }
        else {
            // for(int i=0;i<arrayset.length;i++) {
            //     if(arrayset[i].value > temp_max.value) {
            //         temp_max.CopyChosenMove(arrayset[i]);
            //     }
            // }
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
        // arrayset = generateAllMove();

        depth++;

        if(depth<MAXDEPTH)  {

            //for all possible move
            //eval find_max_recursive
            //cari min
            // for(int i=0; i < arrayset.length; i++) {
            //     temp.CopyChosenMove(find_max_recursive(arrayset[i]));
            //     if(temp.value < temp_min.value) {
            //         temp_min.CopyChosenMove(temp);
            //     }
            //     beta = min( beta, temp_min.value);
            //     if (beta <= alpha)
            //         break;
            // }
        }
        else {
            // Iterate generateAllMoves
            // find min
            // for(int i=0; i < arrayset.length; i++) {
            //     if(arrayset[i].value < temp_min.value) {
            //         temp_min.CopyChosenMove(arrayset[i]);
            //     }
            // }
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

    public static ArrayList<ChosenMove> generateAllMove(Board b) {
        ArrayList<ChosenMove> arrli = new ArrayList<ChosenMove>();
        if (State.WTURN) {
            for (int i=0; i<b.getWhitePawns().getLength(); i++) {
                Board btemp = new Board();
                for (int j=0; j<8; j++) {
                    int[] move = RandomBot.moveTo(b.getWhitePawns().getXFrom(i),b.getWhitePawns().getYFrom(i),j);
                    btemp.copy(b);
                    if (b.isLegalMove(b.getWhitePawns().getXFrom(i),b.getWhitePawns().getYFrom(i), move[0], move[1])) {
                        btemp.movePawn(b.getWhitePawns().getXFrom(i),b.getWhitePawns().getYFrom(i), move[0], move[1]);
                        int eval = Eval.EvalBoard(btemp);
                        ChosenMove c1 = new ChosenMove(i,j,eval);
                        arrli.add(c1);
                    }
                }
            }
        } else {
            for (int i=0; i<b.getBlackPawns().getLength(); i++) {
                for (int j=0; j<8; j++) {
                    int[] move = RandomBot.moveTo(b.getBlackPawns().getXFrom(i),b.getBlackPawns().getYFrom(i),j);
                    Board btemp = new Board();
                    if (b.isLegalMove(b.getBlackPawns().getXFrom(i),b.getBlackPawns().getYFrom(i), move[0], move[1])) {
                        btemp.copy(b);
                        btemp.movePawn(b.getBlackPawns().getXFrom(i),b.getBlackPawns().getYFrom(i), move[0], move[1]);
                        int eval = Eval.EvalBoard(btemp);
                        ChosenMove c1 = new ChosenMove(i,j,eval);
                        arrli.add(c1);
                    }
                }
            }
        }
        return (arrli);
    }

    public static void main(String[] args)  {
        // Minimax M = new Minimax();
        Board b = new Board();
        b.showBoard();
        // ArrayList<ChosenMove> arrli = AlphaBeta.generateAllMove(b);
        // ChosenMove c1 = new ChosenMove(arrli.get(0));
        // System.out.println(arrli.size());
        // c1.printIdPawn();
    }
}

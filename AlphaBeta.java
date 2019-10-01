import java.util.*;

public class AlphaBeta {

    final int MAXDEPTH = 3;

    private int depth;
    private ChosenMove chosenmove;
    private int beta;
    private int alpha;
    private Board[] btemp;
    private int neff;




    public ChosenMove alphabeta(Board b)   {
        depth=1;
        btemp = new Board[20];
        for(int i=0; i<20 ;i++)   {
            btemp[i]= new Board();
        }
        neff=0;
        btemp[neff].copy(b);
        ChosenMove temp_max= new ChosenMove(0,0,Integer.MIN_VALUE); //initialize value with MIN_INTEGER
        ArrayList<ChosenMove> arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);
        // arrayset=generateAllMove();
        int alpha= Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        arrayset = generateAllMove(btemp[neff]);

        for(int i=0; i < arrayset.size(); i++) {
            System.out.println(i);
            temp.CopyChosenMove(find_min_recursive(arrayset.get(i)));
            // System.out.println("move "+btemp[neff].getBlackPawns().getXFrom(temp.idpawn) +" "+btemp[neff].getBlackPawns().getYFrom(temp.idpawn)+" "+temp.move);
            int x1 = btemp[neff].getBlackPawns().getXFrom(temp.idpawn);
            int x2 = btemp[neff].getBlackPawns().getYFrom(temp.idpawn);
            System.out.println("move "+btemp[neff].getBlackPawns().getXFrom(temp.idpawn) +" "+btemp[neff].getBlackPawns().getYFrom(temp.idpawn)+" "+RandomBot.moveTo(x1,x2,temp.move)[0]+" "+RandomBot.moveTo(x1,x2,temp.move)[1]);
            System.out.println("nilai "+temp.value);
            if(temp.value > temp_max.value) {
                temp_max.CopyChosenMove(temp);
            }
            alpha = max( alpha, temp_max.value);
            if (beta <= alpha) {
                System.out.println("break");
                break;
            }
        }

        //generate all possible Board
            //for each board assign current_checked_board with generated board
            //find board with highest value and save the Board in current_checked_Board
        //kayaknya harus generate all possible move di file ini
        
        return temp_max;
    }

    public ChosenMove find_max_recursive(ChosenMove C)   {
        btemp[neff+1].copy(BoardAfterMove(btemp[neff],C));
        neff++;
        ChosenMove temp_max= new ChosenMove(0,0,Integer.MIN_VALUE); //initialize value with MIN_INTEGER
        ArrayList<ChosenMove> arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);
        arrayset = generateAllMove(btemp[neff]);

        depth++;

        System.out.println(depth + " -max");
        if(depth<MAXDEPTH)  {

            for(int i=0;i<arrayset.size();i++) {
                temp.CopyChosenMove(find_min_recursive(arrayset.get(i)));
                System.out.println("move "+btemp[neff].getBlackPawns().getXFrom(temp.idpawn) +" "+btemp[neff].getBlackPawns().getYFrom(temp.idpawn)+" "+temp.move);
                System.out.println(temp.value);
                if(temp.value > temp_max.value) {
                    temp_max.CopyChosenMove(temp);
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
            for(int i=0;i<arrayset.size();i++) {
                if(arrayset.get(i).value > temp_max.value) {
                    temp_max.CopyChosenMove(arrayset.get(i));
                }
            }
            // Iterate generateAllMoves
            // find max
        }
        depth--;
        neff--;

        return temp_max;
    }

    public ChosenMove find_min_recursive(ChosenMove C)   {
        btemp[neff+1].copy(BoardAfterMove(btemp[neff],C));
        neff++;
        ChosenMove temp_min= new ChosenMove(0,0,Integer.MAX_VALUE); //initialize value with MAX_INTEGER
        ArrayList<ChosenMove> arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);
        arrayset = generateAllMove(btemp[neff]);

        depth++;
        System.out.println(depth + " -min");
        if(depth<MAXDEPTH)  {
            for(int i=0; i < arrayset.size(); i++) {
                temp.CopyChosenMove(find_max_recursive(arrayset.get(i)));
                System.out.println("move "+btemp[neff].getBlackPawns().getXFrom(temp.idpawn) +" "+btemp[neff].getBlackPawns().getYFrom(temp.idpawn)+" "+temp.move);
                System.out.println(temp.value);
                if(temp.value < temp_min.value) {
                    temp_min.CopyChosenMove(temp);
                }
                beta = min( beta, temp_min.value);
                if (beta <= alpha)
                    break;
            }
        }
        else {
            for(int i=0; i < arrayset.size(); i++) {
                if(arrayset.get(i).value < temp_min.value) {
                    temp_min.CopyChosenMove(arrayset.get(i));
                }
            }
        }
        depth--;
        neff--;

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

    public Board BoardAfterMove(Board b,ChosenMove c){
        int[] move = RandomBot.moveTo(b.getWhitePawns().getXFrom(c.idpawn),b.getWhitePawns().getYFrom(c.idpawn),c.move);
        Board bb = new Board();
        bb.movePawn(b.getWhitePawns().getXFrom(c.idpawn),b.getWhitePawns().getYFrom(c.idpawn), move[0], move[1]);
        b.setWTURN(); 
        return bb; 
    }

    public static ArrayList<ChosenMove> generateAllMove(Board b) {
        ArrayList<ChosenMove> arrli = new ArrayList<ChosenMove>();
        if (b.getWTURN()) {
            for (int i=0; i<b.getWhitePawns().getLength(); i++) {
                for (int j=0; j<8; j++) {
                    int[] move = RandomBot.moveTo(b.getWhitePawns().getXFrom(i),b.getWhitePawns().getYFrom(i),j);
                    Board btemps=new Board();
                    btemps.copy(b);
                    if (b.isLegalMove(b.getWhitePawns().getXFrom(i),b.getWhitePawns().getYFrom(i), move[0], move[1])) {
                        btemps.movePawn(b.getWhitePawns().getXFrom(i),b.getWhitePawns().getYFrom(i), move[0], move[1]);
                        int eval = Eval.EvalBoard(btemps);
                        ChosenMove c1 = new ChosenMove(i,j,eval);
                        arrli.add(c1);
                    }
                }
            }
        } else {
            for (int i=0; i<b.getBlackPawns().getLength(); i++) {
                for (int j=0; j<8; j++) {
                    int[] move = RandomBot.moveTo(b.getBlackPawns().getXFrom(i),b.getBlackPawns().getYFrom(i),j);
                    Board btemps=new Board();
                    btemps.copy(b);
                    if (b.isLegalMove(b.getBlackPawns().getXFrom(i),b.getBlackPawns().getYFrom(i), move[0], move[1])) {
                        btemps.movePawn(b.getBlackPawns().getXFrom(i),b.getBlackPawns().getYFrom(i), move[0], move[1]);
                        int eval = Eval.EvalBoard(btemps);
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
        ArrayList<ChosenMove> c1= AlphaBeta.generateAllMove(b);
        // System.out.println(c1.size());
        System.out.println(b.getBlackPawns().getXFrom(c1.get(6).idpawn));
        System.out.println(b.getBlackPawns().getYFrom(c1.get(6).idpawn));
        int[] move = RandomBot.moveTo(b.getBlackPawns().getXFrom(c1.get(6).idpawn),b.getBlackPawns().getYFrom(c1.get(6).idpawn),c1.get(6).move);
        System.out.println(move[0]+" y"+ move[1]);
        // c1.get(0).printMove();
        // System.out.println(b.isLegalMove(b.getBlackPawns().getXFrom(c1.get(0).idpawn),b.getBlackPawns().getYFrom(c1.get(0).idpawn), move[0], move[1]));
    }
}

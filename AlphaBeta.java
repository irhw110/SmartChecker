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
        for(int i=0; i<MAXDEPTH+1 ;i++)   {
            btemp[i]= new Board();
        }
        neff=0;
        btemp[neff].copy(b);

        int index=0;

        ChosenMove temp_max= new ChosenMove(0,0,Integer.MIN_VALUE); //initialize value with MIN_INTEGER
        ArrayList<ChosenMove> arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);
        // arrayset=generateAllMove();
        int alpha= Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        arrayset = generateAllMove(btemp[neff]);
        // System.out.println("Kondisi Awal");
        // btemp[neff].showBoard();

        for(int i=0; i < arrayset.size(); i++) {
            // System.out.println("Iterasi ke: "+i);
            temp.CopyChosenMove(find_min_recursive(arrayset.get(i)));
            // System.out.println("move "+btemp[neff].getBlackPawns().getXFrom(temp.idpawn) +" "+btemp[neff].getBlackPawns().getYFrom(temp.idpawn)+" "+temp.move);
           
            int[] move = RandomBot.moveTo(btemp[neff].getBlackPawns().getXFrom(arrayset.get(i).idpawn),btemp[neff].getBlackPawns().getYFrom(arrayset.get(i).idpawn),arrayset.get(i).move);
            System.out.println("move "+ i+ " : " +btemp[neff].getBlackPawns().getXFrom(arrayset.get(i).idpawn) +" "+btemp[neff].getBlackPawns().getYFrom(arrayset.get(i).idpawn)+" "+move[0]+" "+move[1] + " : "+arrayset.get(i).value);
            btemp[neff].movePawn(btemp[neff].getBlackPawns().getXFrom(arrayset.get(i).idpawn),btemp[neff].getBlackPawns().getYFrom(arrayset.get(i).idpawn), move[0], move[1]);

            // int x1 = btemp[neff].getBlackPawns().getXFrom(temp.idpawn);
            // int x2 = btemp[neff].getBlackPawns().getYFrom(temp.idpawn);
            // System.out.println("Ini move depth 1 "+btemp[neff].getBlackPawns().getXFrom(temp.idpawn) +" "+btemp[neff].getBlackPawns().getYFrom(temp.idpawn)+" "+RandomBot.moveTo(x1,x2,temp.move)[0]+" "+RandomBot.moveTo(x1,x2,temp.move)[1]);
            // System.out.println("nilai "+temp.value);
            if(temp.value > temp_max.value) {
                index=i;
            }
            alpha = max( alpha, temp_max.value);
            if (beta <= alpha) {
                // System.out.println("break");
                break;
            }
        }

        //generate all possible Board
            //for each board assign current_checked_board with generated board
            //find board with highest value and save the Board in current_checked_Board
        //kayaknya harus generate all possible move di file ini
        
        return arrayset.get(index);
    }

    public ChosenMove find_max_recursive(ChosenMove C)   {
        btemp[neff+1].copy(BoardAfterMove(btemp[neff],C));
        // System.out.println("Generate all move dalam max yg mau dicopy: "+btemp[neff].getWTURN());
        // System.out.println("Generate all move dalam max result1: "+btemp[neff+1].getWTURN());
        neff++;

        int index=0;
        
        ChosenMove temp_max= new ChosenMove(0,0,Integer.MIN_VALUE); //initialize value with MIN_INTEGER
        ArrayList<ChosenMove> arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);

        depth++;
        // System.out.println(depth + " -max " + btemp[neff].getWTURN());
        // btemp[neff].showBoard();
        // System.out.println(btemp[neff].getWTURN());
        
        arrayset = generateAllMove(btemp[neff]);
        
        if(depth<MAXDEPTH)  {

            for(int i=0;i<arrayset.size();i++) {
                temp.CopyChosenMove(find_min_recursive(arrayset.get(i)));
                
                
                if(temp.value > temp_max.value) {
                    index = i;
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
                    index=i;
                }
            }
            // Iterate generateAllMoves
            // find max
        }
        depth--;
        

//         int x1 = btemp[neff].getBlackPawns().getXFrom(temp_max.idpawn);
//                 int x2 = btemp[neff].getBlackPawns().getYFrom(temp_max.idpawn);

// System.out.println("max "+index);
//                 System.out.println("value " + arrayset.get(index).value);
                neff--;
        return arrayset.get(index);
    }

    public ChosenMove find_min_recursive(ChosenMove C)   {
        btemp[neff+1].copy(BoardAfterMove(btemp[neff],C));
        neff++;
        
        ChosenMove temp_min= new ChosenMove(0,0,Integer.MAX_VALUE); //initialize value with MAX_INTEGER
        ArrayList<ChosenMove> arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);

        depth++;
        // System.out.println(depth + " -min " + btemp[neff].getWTURN());
        // btemp[neff].showBoard();

        int index=0;
        
        arrayset = generateAllMove(btemp[neff]);

        
        if(depth<MAXDEPTH)  {
            for(int i=0; i < arrayset.size(); i++) {
                temp.CopyChosenMove(find_max_recursive(arrayset.get(i)));
                
                
                if(temp.value < temp_min.value) {
                    index=i;
                }
                beta = min( beta, temp_min.value);
                if (beta <= alpha)
                    break;
            }
        }
        else {
            for(int i=0; i < arrayset.size(); i++) {
                if(arrayset.get(i).value < temp_min.value) {
                    index=i;
                }
            }
        }
        depth--;
        

        // int x1 = btemp[neff].getWhitePawns().getXFrom(temp_min.idpawn);
        // int x2 = btemp[neff].getWhitePawns().getYFrom(temp_min.idpawn);

        //         System.out.println("min "+index);
        //         System.out.println("value " + arrayset.get(index).value);
neff--;
        return arrayset.get(index);
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
        // int[] move = RandomBot.moveTo(b.getWhitePawns().getXFrom(c.idpawn),b.getWhitePawns().getYFrom(c.idpawn),c.move);
        // Board bb = new Board();
        // bb.movePawn(b.getWhitePawns().getXFrom(c.idpawn),b.getWhitePawns().getYFrom(c.idpawn), move[0], move[1]);
        // b.setWTURN(); 
        Board bb = new Board();
        bb.copy(b);
        if (bb.getWTURN()) {
            int[] move = RandomBot.moveTo(bb.getWhitePawns().getXFrom(c.idpawn),bb.getWhitePawns().getYFrom(c.idpawn),c.move);
            bb.movePawn(bb.getWhitePawns().getXFrom(c.idpawn),bb.getWhitePawns().getYFrom(c.idpawn), move[0], move[1]);
        } else {
            int[] move = RandomBot.moveTo(bb.getBlackPawns().getXFrom(c.idpawn),bb.getBlackPawns().getYFrom(c.idpawn),c.move);
            bb.movePawn(bb.getBlackPawns().getXFrom(c.idpawn),bb.getBlackPawns().getYFrom(c.idpawn), move[0], move[1]);
        }
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
        for (int i=0; i<c1.size(); i++) {
            System.out.println("posisi x: "+b.getBlackPawns().getXFrom(c1.get(i).idpawn));
            System.out.println("posisi y: "+b.getBlackPawns().getYFrom(c1.get(i).idpawn));
            int[] move = RandomBot.moveTo(b.getBlackPawns().getXFrom(c1.get(i).idpawn),b.getBlackPawns().getYFrom(c1.get(i).idpawn),c1.get(i).move);
            System.out.println("berpindah ke x: "+move[0]+" y: "+ move[1]);
            // c1.get(0).printMove();
            // System.out.println(b.isLegalMove(b.getBlackPawns().getXFrom(c1.get(0).idpawn),b.getBlackPawns().getYFrom(c1.get(0).idpawn), move[0], move[1]))    
        }
    }
}

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
        
        ChosenMove temp_max= new ChosenMove(0,0,Integer.MIN_VALUE); //initialize value with MIN_INTEGER
        ArrayList<ChosenMove> arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);
        // arrayset=generateAllMove();
        int alpha= Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        btemp[neff].copy(b);
        
        arrayset = CopyListofChosenMove(generateAllMove(b));
        System.out.println("kondisi btemp[0] saat expand pertama kali");
        btemp[neff].showBoard();
        //  System.out.println(depth + " -max");
        for(int i=0;i<arrayset.size();i++) {
            System.out.println("move "+btemp[neff].getBlackPawns().getXFrom(temp.idpawn) +" "+btemp[neff].getBlackPawns().getYFrom(temp.idpawn)+" "+temp.move);
            System.out.println("nilai "+temp.value);
        }
       
        for(int i=0; i < arrayset.size(); i++) {
            // System.out.println("move "+btemp[neff].getBlackPawns().getXFrom(temp.idpawn) +" "+btemp[neff].getBlackPawns().getYFrom(temp.idpawn)+" "+temp.move);
            // System.out.println("nilai "+temp.value);
            temp.CopyChosenMove(find_min_recursive(arrayset.get(i)));
            
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
        depth++;
        // System.out.println("max ");
        // btemp[neff].showBoard();
        ChosenMove temp_max= new ChosenMove(0,0,Integer.MIN_VALUE); //initialize value with MIN_INTEGER
        ArrayList<ChosenMove> arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);
        System.out.println(depth + " -max");
        arrayset = new ArrayList<ChosenMove> (generateAllMove(btemp[neff]));

        

        
        if(depth<MAXDEPTH)  {

            for(int i=0;i<arrayset.size();i++) {
                temp.CopyChosenMove(find_min_recursive(arrayset.get(i)));
                // System.out.println("move "+btemp[neff].getBlackPawns().getXFrom(temp.idpawn) +" "+btemp[neff].getBlackPawns().getYFrom(temp.idpawn)+" "+temp.move);
                // System.out.println(temp.value);
                if(temp.value > temp_max.value) {
                    temp_max.CopyChosenMove(temp);
                }
                alpha = max( alpha, temp_max.value);
                if (beta <= alpha)
                    break;
            }
        }
        else {
            for(int i=0;i<arrayset.size();i++) {
                if(arrayset.get(i).value > temp_max.value) {
                    temp_max.CopyChosenMove(arrayset.get(i));
                }
            }
        }
        depth--;
        neff--;

        return temp_max;
    }

    public ChosenMove find_min_recursive(ChosenMove C)   {
        System.out.println("test");
        btemp[neff].showBoard();
        btemp[neff+1].copy(BoardAfterMove(btemp[neff],C));
        neff++;
        depth++;
        // System.out.println("min ");
        // btemp[neff].showBoard();
        ChosenMove temp_min= new ChosenMove(0,0,Integer.MAX_VALUE); //initialize value with MAX_INTEGER
        ArrayList<ChosenMove> arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);
        System.out.println(depth + " -min");
        arrayset = new ArrayList<ChosenMove> (generateAllMove(btemp[neff]));
        
        if(depth<MAXDEPTH)  {
            for(int i=0; i < arrayset.size(); i++) {
                temp.CopyChosenMove(find_max_recursive(arrayset.get(i)));
                // System.out.println("move "+btemp[neff].getBlackPawns().getXFrom(temp.idpawn) +" "+btemp[neff].getBlackPawns().getYFrom(temp.idpawn)+" "+temp.move);
                // System.out.println(temp.value);
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
        State.WTURN=!State.WTURN;
        return bb; 
    }

    public static ArrayList<ChosenMove> generateAllMove(Board b) {
        ArrayList<ChosenMove> arrli = new ArrayList<ChosenMove>();
        
        if (State.WTURN == true) {
            System.out.println("white");
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
            System.out.println("black");
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
        System.out.println("start1");
        System.out.println(arrli.size());

        for(int i=0; i<arrli.size() ; i++ ) {
            System.out.println("move "+ b.getBlackPawns().getXFrom(arrli.get(i).idpawn) +" "+ b.getBlackPawns().getYFrom(arrli.get(i).idpawn)+" "+ arrli.get(i).move);
            System.out.println("nilai "+arrli.get(i).value);
        }
        System.out.println("end");

        return (arrli);
    }


    public ArrayList<ChosenMove> CopyListofChosenMove(ArrayList<ChosenMove> a) {
        ArrayList<ChosenMove> temp = new ArrayList<ChosenMove>(); 

        for(int i=0;i < a.size() ; i++) {
            temp.add(a.get(i));
        }
        return temp;
    }

    public static void main(String[] args)  {
        // Minimax M = new Minimax();
        Board b = new Board();
        b.showBoard();
        ArrayList<ChosenMove> c1= AlphaBeta.generateAllMove(b);
        // System.out.println(c1.size());
        for (int l=0; l<c1.size(); l++) {
            System.out.println(b.getBlackPawns().getXFrom(c1.get(l).idpawn));
            System.out.println(b.getBlackPawns().getYFrom(c1.get(l).idpawn));
            int[] move = RandomBot.moveTo(b.getBlackPawns().getXFrom(c1.get(l).idpawn),b.getBlackPawns().getYFrom(c1.get(l).idpawn),c1.get(l).move);
            System.out.println(move[0]+" y"+ move[1]);
        }
        // c1.get(0).printMove();
        // System.out.println(b.isLegalMove(b.getBlackPawns().getXFrom(c1.get(0).idpawn),b.getBlackPawns().getYFrom(c1.get(0).idpawn), move[0], move[1]));
    }
}

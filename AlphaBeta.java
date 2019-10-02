import java.util.*;

public class AlphaBeta {

    final int MAXDEPTH = 4; //Mencatat kedalaman maksimum yang akan digunakan
    private int depth;      //Mencatat kedalaman saat ini
    private int beta;       //Mencatat beta untuk membantu pemotongan
    private int alpha;      //Mencatat alpha untuk membantu pemotongan
    private Board[] btemp;  //Pencatatan kondisi state saat ini yang akan digunakan untuk expand node anaknya, menggunakan prinsip seperti stack
    private int neff;       // Mencatat banyak elemen pada btemp




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
        int alpha= Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        arrayset = generateAllMove(btemp[neff]);

        for(int i=0; i < arrayset.size(); i++) {
            temp.CopyChosenMove(find_min_recursive(arrayset.get(i)));
           
            if(temp.value > temp_max.value) {
                index=i;
                
                temp_max.CopyChosenMove(temp);
            }
            alpha = max( alpha, temp_max.value);
            if (beta <= alpha) {
                break;
            }
        }
        System.out.println(index);
        return arrayset.get(index);
    }

    public ChosenMove find_max_recursive(ChosenMove C)   {
        btemp[neff+1].copy(BoardAfterMove(btemp[neff],C));
        neff++;
        
        ChosenMove temp_max= new ChosenMove(0,0,Integer.MIN_VALUE); //initialize value with MIN_INTEGER
        ArrayList<ChosenMove> arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);

        depth++;
        
        arrayset = generateAllMove(btemp[neff]);
        
        if(depth<MAXDEPTH-1)  {

            for(int i=0;i<arrayset.size();i++) {
                temp.CopyChosenMove(find_min_recursive(arrayset.get(i)));
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
        btemp[neff+1].copy(BoardAfterMove(btemp[neff],C));
        neff++;
        
        ChosenMove temp_min= new ChosenMove(0,0,Integer.MAX_VALUE); //initialize value with MAX_INTEGER
        ArrayList<ChosenMove> arrayset;
        ChosenMove temp = new ChosenMove(0,0,0);

        depth++;
        
        arrayset = generateAllMove(btemp[neff]);
        
        if(depth<MAXDEPTH-1)  {
            for(int i=0; i < arrayset.size(); i++) {
                temp.CopyChosenMove(find_max_recursive(arrayset.get(i)));
            
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
                System.out.println("Nilai val: "+arrayset.get(i).value);
            }
        }
        depth--;
        neff--;
        return temp_min;
    }

    /* Mencari nilai maksimum dari 2 bilangan */
    public int max(int a,int b)    {
        if(a>b) {
            return a;
        }
        else return b;
    }

    /* Mencari nilai minimum dari 2 bilangan */
    public int min(int a,int b)    {
        if(a<b) {
            return a;
        }
        else return b;
    }

    /* Mengeluarkan output kondisi Board setelah melakukan langkah ChosenMove */ 
    public Board BoardAfterMove(Board b,ChosenMove c){
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


    /* Fungsi yang menghasilkan output arraylist dari semua langkah yang mungkin selanjutnya dalam bentuk objek ChosenMove*/
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

}

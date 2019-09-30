

public class ArrayOfPawn{
    private Pawn array[];
    private int size;
    private int length;

    public ArrayOfPawn(int color, int size){
        this.size = size;
        this.length = size * 3 / 2;
        this.array = new Pawn[this.length];

        int j=0;
        if(color == State.COlORWHITE){
            //row 0 & 2
            for(int i=0; i<size; i++){
                if(i % 2 == 0){
                    this.array[j] = new Pawn(0, i, State.WHITE);
                    j++;
                    this.array[j] = new Pawn(2, i, State.WHITE);
                    j++;
                }
            }
            
            //row 1
            for(int i=0; i<size; i++){
                if(i % 2 == 1){
                    this.array[j] = new Pawn(1, i, State.WHITE);
                    j++;
                }
            }
        } else if(color == State.COLORBLACK){
            //row 6
            for(int i=0; i<size; i++){
                if(i % 2 == 0){
                    this.array[j] = new Pawn(6, i, State.BLACK);
                    j++;
                }
            }
            
            //row 7
            for(int i=0; i<size; i++){
                if(i % 2 == 1){
                    this.array[j] = new Pawn(7, i, State.BLACK);
                    j++;
                    this.array[j] = new Pawn(5, i, State.BLACK);
                    j++;
                }
            }   
        }     
    }

    //return index of pawn at position (x,y), if found return index, not found return -1
    public int findPawn(int x, int y){
        int result = -1; 
        for(int i=0; i<length; i++){
            if((this.array[i].getX()==x) && (this.array[i].getY() == y)){
                result = i;
                break;
            }
        }
        return result;
    }

    public void deletePawn(int x, int y){
        int idxPawnWillDeleted = findPawn(x, y);
        if(idxPawnWillDeleted == this.length){
            this.length = this.length - 1;
        } else{
            this.array[idxPawnWillDeleted].setX(this.array[length-1].getX());
            this.array[idxPawnWillDeleted].setY(this.array[length-1].getY());
            this.array[idxPawnWillDeleted].setState(this.array[length-1].getState());
            
            this.length = this.length -1;
        }
        
    }

    public void setPawn(int i, int x, int y) {
        this.array[i].setX(x);
        this.array[i].setY(y);
    }

    public int getLength() {
        return length;
    }
}
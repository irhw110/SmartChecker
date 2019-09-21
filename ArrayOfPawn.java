

public class ArrayOfPawn{
    private Pawn array[];
    private int size;
    private int length;

    public ArrayOfPawn(int color, int size){
        this.size = size;
        this.length = size / 2;
        this.array = new Pawn[this.size];
        
        if(color == State.COlORWHITE){
            //row 0
            for(int i=0; i<=size; i++){
                if(i % 2 == 0){
                    this.array[i] = new Pawn(0, i, State.WHITE);
                }
            }
            
            //row 1
            for(int i=0; i<=size; i++){
                if(i % 2 == 1){
                    this.array[i] = new Pawn(1, i, State.WHITE);
                }
            }
        } else if(color == State.COLORBLACK){
            //row 6
            for(int i=0; i<=size; i++){
                if(i % 2 == 0){
                    this.array[i] = new Pawn(6, i, State.BLACK);
                }
            }
            
            //row 7
            for(int i=0; i<=size; i++){
                if(i % 2 == 1){
                    this.array[i] = new Pawn(7, i, State.BLACK);
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
            }
        }
        return result;
    }

    public void deletePawn(int x, int y){
        int idxPawnWillDeleted = findPawn(x, y);
        if(idxPawnWillDeleted == this.length){
            this.length = this.length - 1;
        } else{
            Pawn tempPawn = new Pawn(this.array[idxPawnWillDeleted].getX(), this.array[idxPawnWillDeleted].getY(), this.array[idxPawnWillDeleted].getState());
            this.array[idxPawnWillDeleted].setX(this.array[length-1].getX());
            this.array[idxPawnWillDeleted].setX(this.array[length-1].getX());
            this.array[idxPawnWillDeleted].setState(this.array[length-1].getState());
            
            this.length = this.length -1;
        }
        
    }
}
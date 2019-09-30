// Class yang mewakili pemilihan langkah yang akan dilakukan oleh AI kita
public class ChosenMove {
    public int idpawn;
    //Nomor dari pawn yang bergerak
    public int move;
    // Ada 8
    /*

     */
    public int value;
    //Poin dari board setelah pawn bergerak

    public ChosenMove(int _idpawn, int _move, int _value) {
        idpawn=_idpawn;
        move=_move;
        value=_value;
    }

    public void CopyChosenMove(ChosenMove c) {
        idpawn= c.idpawn;
        move= c.move;
        value= c.value;
    }

    public void printIdPawn() {
        System.out.println("idpawn " + this.idpawn);
    }

    public void printMove() {
        System.out.println("move " + this.move);
    }
}

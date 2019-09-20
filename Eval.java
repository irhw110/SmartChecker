public class Eval {
		public int EvalBoard(Board b, int side) {

		}

		public int EvalPetak(int x, int y, Board b, ) {
				/* kalau disamping */
		}

		public int EvalPetakSamping(int x, Board b) {
				if (x == 0 || x == Board.GetWidth()) {
						return 50;
				} else {
						return 0;
				}
		}

		/* Semakin dekat menjadi raja semakin tinggi nilai */
		public int EvalKedekatanMenjadiRaja(int x, int y, Board b) {
				/* setiap mendekati menjadi raja nilai akan meningkat 5 */
				int nilaiMax = b.GetHeight()*5;
				int jarakKeRaja;
				/* kalau dah raja tidak ngaruh nilainya */
				if (b.GetPawn(x,y) == STATE.WKING || b.GetPawn(x,y) == STATE.BKING) {
						return 0;
				/* evaluasi bukan raja */
				} else {
						if (b.GetPawn(x,y) == STATE.WHITE) {
								jarakKeRaja = y;
						} else {
								jarakKeRaja = b.GetHeight() - y;
						}
						return nilaiMax - (jarakKeRaja*5);
				}
		}

		// Driver
    public static void main(String args[]) {
        Eval b = new Eval();
        System.out.println(b.cek());
    }
}

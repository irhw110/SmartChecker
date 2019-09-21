public class Eval {
		public int EvalBoard(Board b, int side) {

		}

		public int EvalPetak(int x, int y, Board b, ) {
				/* kalau disamping */
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

		/* fungsi untuk mengecek apakah x y ada ancaman atau tidak */
		public int EvalAmanDariMusuh(int x, int y, Board b) {
				int nilaiEvaluasi = 0;
				/* cek apakah disamping, samping pasti aman */
				if (x == 0 || x == (Board.GetWidth()-1)) {
						return 50;
				}

				/* cek apakah diujung atas atau bawah, ujung atas atau bawah pasti aman */
				if (y == 0 || y == (Board.GetHeight()-1)) {
						return 50;
				}

				/* x,y adalah bidak putih */
				if (b.GetPawn(x,y) == STATE.WHITE || b.GetPawn(x,y) == STATE.WKING) {
						if (b.GetPawn(x+1,y-1) == STATE.BLACK || b.GetPawn(x-1,y-1) == STATE.BLACK || b.GetPawn(x+1,y-1) == STATE.BKING ||
						b.GetPawn(x-1,y-1) == STATE.BKING || b.GetPawn(x-1,y+1) == STATE.BKING || b.GetPawn(x+1,y+1) == STATE.BKING) {
								nilaiEvaluasi += AmanDariDiMakanMusuh(x+1, y-1, x-1, y+1, 0, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x-1, y-1, x+1, y+1, 0, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x+1, y+1, x-1, y-1, 0, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x+1, y+1, x-1, y-1, 0, b);
						/* diagonal sekitar x y tidak ada bidak hitam */
						} else {
								return 50;
						}
				} else {
						if (b.GetPawn(x+1,y-1) == STATE.WHITE || b.GetPawn(x-1,y-1) == STATE.WHITE || b.GetPawn(x+1,y-1) == STATE.WKING ||
						b.GetPawn(x-1,y-1) == STATE.WKING || b.GetPawn(x-1,y+1) == STATE.WKING || b.GetPawn(x+1,y+1) == STATE.WKING) {
								nilaiEvaluasi += AmanDariDiMakanMusuh(x+1, y-1, x-1, y+1, 1, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x-1, y-1, x+1, y+1, 1, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x+1, y+1, x-1, y-1, 1, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x+1, y+1, x-1, y-1, 1, b);
						/* diagonal sekitar x y tidak ada bidak putih */
						} else {
								return 50;
						}
				}
		}

		/*
		- xbackup adalah x lawan diagonal dari xenemy, ybackup adalah y lawan diagonal dari yenemy
		misal ancaman kita ada di 3,4 maka backup kita ada di 2,6
	  - pengecekan dilakukan terhadap 4 diagonal posisi yang sedang dicek
	  - side 0 artinya posisi sekarang adalah bidak putih
	  - jika tidak ada ancaman artinya xenemy yenemy kosong atau warna bidaknya sama dengan posisi saat ini 
		*/
		public int AmanDariDiMakanMusuh(int xenemy, int yenemy, int xbackup, int ybackup, int side, Board b) {
				if (side == 0) {
						if (b.GetPawn(xenemy, yenemy) == STATE.BLACK || b.GetPawn(xenemy,yenemy) == STATE.BKING) {
								if (b.GetPawn(xbackup, ybackup) == STATE.NOPAWN) {
										return 0;
								} else {
										return 12.5;
								}
						} else {
								return 12.5;
						}
				} else {
						if (b.GetPawn(xenemy, yenemy) == STATE.WHITE || b.GetPawn(xenemy,yenemy) == STATE.WKING) {
								if (b.GetPawn(xbackup, ybackup) == STATE.NOPAWN) {
										return 0;
								} else {
										return 12.5;
								}
						} else {
								return 12.5;
						}
				}
		}

		// Driver
    public static void main(String args[]) {
        Eval b = new Eval();
        System.out.println(b.cek());
    }
}

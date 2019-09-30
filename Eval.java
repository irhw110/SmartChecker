public class Eval {
		public int EvalBoard(Board b) {
				// Board b2 = new Board();
				int evalPetak = 0;
				int totalEval = 0;
				// System.out.println(evalPetak);
				for (int i=0; i<8; i++) {
						for (int j=0; j<8; j++) {
								evalPetak = evalPetak + EvalKedekatanMenjadiRaja(i, j, b);
								evalPetak = evalPetak + EvalAmanDariMusuh(i,j,b);
								if (b.GetPawn(i,j) == State.BLACK || b.GetPawn(i,j) == State.BKING) {
										evalPetak = -1*evalPetak;
								}
								// b2.SetBoard(i,j,evalPetak);
								totalEval = totalEval + evalPetak;
								evalPetak = 0;
						}
						// System.out.println(totalEval);
				}
				// b2.ShowBoard();
				return totalEval;
		}

		/* Semakin dekat menjadi raja semakin tinggi nilai */
		public int EvalKedekatanMenjadiRaja(int x, int y, Board b) {
				/* setiap mendekati menjadi raja nilai akan meningkat 5 */
				int nilaiMax = b.GetHeight()*5;
				int jarakKeRaja;
				/* kalau dah raja tidak ngaruh nilainya */
				if (b.GetPawn(x,y) == State.WKING || b.GetPawn(x,y) == State.BKING || b.GetPawn(x,y) == State.NOPAWN) {
						return 0;
				/* evaluasi bukan raja */
				} else {
						if (b.GetPawn(x,y) == State.WHITE) {
								jarakKeRaja = b.GetHeight() - x;
						} else {
								jarakKeRaja = x+1;
						}
						return nilaiMax - (jarakKeRaja*5);
				}
		}

		/* fungsi untuk mengecek apakah x y ada ancaman atau tidak */
		public int EvalAmanDariMusuh(int x, int y, Board b) {
				int nilaiEvaluasi = 0;

				if (b.GetPawn(x,y) == State.NOPAWN) {
						return 0;
				}
				/* cek apakah disamping, samping pasti aman */
				if (y == 0 || y == (b.GetWidth()-1)) {
						return 48;
				}

				/* cek apakah diujung atas atau bawah, ujung atas atau bawah pasti aman */
				if (x == 0 || x == (b.GetHeight()-1)) {
						return 48;
				}

				/* x,y adalah bidak putih */
				if (b.GetPawn(x,y) == State.WHITE || b.GetPawn(x,y) == State.WKING) {
						if (b.GetPawn(x+1,y-1) == State.BLACK || b.GetPawn(x+1,y+1) == State.BLACK || b.GetPawn(x+1,y-1) == State.BKING ||
						b.GetPawn(x-1,y-1) == State.BKING || b.GetPawn(x-1,y+1) == State.BKING || b.GetPawn(x+1,y+1) == State.BKING) {
								nilaiEvaluasi += AmanDariDiMakanMusuh(x+1, y-1, x-1, y+1, 0, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x-1, y-1, x+1, y+1, 0, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x+1, y+1, x-1, y-1, 0, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x-1, y+1, x+1, y-1, 0, b);
						/* diagonal sekitar x y tidak ada bidak hitam */
						} else {
								return 48;
						}
				} else {
						if (b.GetPawn(x-1,y+1) == State.WHITE || b.GetPawn(x-1,y-1) == State.WHITE || b.GetPawn(x+1,y-1) == State.WKING ||
						b.GetPawn(x-1,y-1) == State.WKING || b.GetPawn(x-1,y+1) == State.WKING || b.GetPawn(x+1,y+1) == State.WKING) {
								nilaiEvaluasi += AmanDariDiMakanMusuh(x+1, y-1, x-1, y+1, 1, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x-1, y-1, x+1, y+1, 1, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x+1, y+1, x-1, y-1, 1, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x-1, y+1, x+1, y-1, 1, b);
						/* diagonal sekitar x y tidak ada bidak putih */
						} else {
								return 48;
						}
				}
				return nilaiEvaluasi;
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
						if (b.GetPawn(xenemy, yenemy) == State.BLACK || b.GetPawn(xenemy,yenemy) == State.BKING) {
								if (b.GetPawn(xbackup, ybackup) == State.NOPAWN) {
										return 0;
								} else {
										return 12;
								}
						} else {
								return 12;
						}
				} else {
						if (b.GetPawn(xenemy, yenemy) == State.WHITE || b.GetPawn(xenemy,yenemy) == State.WKING) {
								if (b.GetPawn(xbackup, ybackup) == State.NOPAWN) {
										return 0;
								} else {
										return 12;
								}
						} else {
								return 12;
						}
				}
		}

		// Driver
    public static void main(String args[]) {
				Eval e = new Eval();
				Board b2 = new Board();
				b2.MovePawn(20,31);
				b2.MovePawn(51,40);
				b2.MovePawn(22,33);
				b2.MovePawn(53,42);
				b2.ShowBoard();
				int total = e.EvalBoard(b2);
				System.out.println(total);
    }
}

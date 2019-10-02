public class Eval {
		public static int EvalBoard(Board b) {
				// Board b2 = new Board();
				int evalPetak = 0;
				int totalEval = 0;
				// System.out.println(evalPetak);
				for (int i=0; i<8; i++) {
						for (int j=0; j<8; j++) {
								evalPetak = evalPetak + EvalKedekatanMenjadiRaja(i, j, b);
								evalPetak = evalPetak + EvalAmanDariMusuh(i,j,b);
								evalPetak = evalPetak + BentengDariRaja(i,j,b);
								/* evaluasi kalau raja */
								if (b.getPawn(i,j) == State.WKING || b.getPawn(i,j) == State.BKING) {
									evalPetak = evalPetak + 15;
								}
								if (b.getPawn(i,j) == State.WHITE || b.getPawn(i,j) == State.WKING) {
									evalPetak = -1*evalPetak;
								}
								// b2.SetBoard(i,j,evalPetak);
								totalEval = totalEval + evalPetak;
								evalPetak = 0;
						}
						// System.out.println(totalEval);
				}

				/* evaluasi selisih pawn */
				int selisihPawn = b.getBlackPawns().getLength() - b.getWhitePawns().getLength();
				totalEval += selisihPawn*100;
				// b2.ShowBoard();
				return totalEval;
		}

		/* Semakin dekat menjadi raja semakin tinggi nilai */
		public static int EvalKedekatanMenjadiRaja(int x, int y, Board b) {
				/* setiap mendekati menjadi raja nilai akan meningkat 5 */
				int nilaiMax = b.getHeight()*5;
				int jarakKeRaja;
				/* kalau dah raja tidak ngaruh nilainya */
				if (b.getPawn(x,y) == State.WKING || b.getPawn(x,y) == State.BKING || b.getPawn(x,y) == State.NOPAWN) {
						return 0;
				/* evaluasi bukan raja */
				} else {
						if (b.getPawn(x,y) == State.WHITE) {
								jarakKeRaja = b.getHeight() - x;
						} else {
								jarakKeRaja = x+1;
						}
						return nilaiMax - (jarakKeRaja*5);
				}
		}

		/* fungsi untuk mengecek apakah x y ada ancaman atau tidak */
		public static int EvalAmanDariMusuh(int x, int y, Board b) {
				int nilaiEvaluasi = 0;

				if (b.getPawn(x,y) == State.NOPAWN) {
						return 0;
				}
				/* cek apakah disamping, samping pasti aman */
				if (y == 0 || y == (b.getWidth()-1)) {
						return 12;
				}

				/* cek apakah diujung atas atau bawah, ujung atas atau bawah pasti aman */
				if (x == 0 || x == (b.getHeight()-1)) {
						return 12;
				}

				/* x,y adalah bidak putih */
				if (b.getPawn(x,y) == State.WHITE || b.getPawn(x,y) == State.WKING) {
						if (b.getPawn(x+1,y-1) == State.BLACK || b.getPawn(x+1,y+1) == State.BLACK || b.getPawn(x+1,y-1) == State.BKING ||
						b.getPawn(x-1,y-1) == State.BKING || b.getPawn(x-1,y+1) == State.BKING || b.getPawn(x+1,y+1) == State.BKING) {
								nilaiEvaluasi += AmanDariDiMakanMusuh(x+1, y-1, x-1, y+1, 0, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x-1, y-1, x+1, y+1, 0, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x+1, y+1, x-1, y-1, 0, b);
								nilaiEvaluasi += AmanDariDiMakanMusuh(x-1, y+1, x+1, y-1, 0, b);
						/* diagonal sekitar x y tidak ada bidak hitam */
						} else {
								return 48;
						}
				} else {
						if (b.getPawn(x-1,y+1) == State.WHITE || b.getPawn(x-1,y-1) == State.WHITE || b.getPawn(x+1,y-1) == State.WKING ||
						b.getPawn(x-1,y-1) == State.WKING || b.getPawn(x-1,y+1) == State.WKING || b.getPawn(x+1,y+1) == State.WKING) {
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
		public static int AmanDariDiMakanMusuh(int xenemy, int yenemy, int xbackup, int ybackup, int side, Board b) {
				if (side == 0) {
						if (b.getPawn(xenemy, yenemy) == State.BLACK || b.getPawn(xenemy,yenemy) == State.BKING) {
								if (b.getPawn(xbackup, ybackup) == State.NOPAWN) {
										return 0;
								} else {
										return 4;
								}
						} else {
								return 4;
						}
				} else {
						if (b.getPawn(xenemy, yenemy) == State.WHITE || b.getPawn(xenemy,yenemy) == State.WKING) {
								if (b.getPawn(xbackup, ybackup) == State.NOPAWN) {
										return 0;
								} else {
										return 4;
								}
						} else {
								return 4;
						}
				}
		}

		public static int BentengDariRaja(int x, int y, Board b) {
			if (x==0) {
				if (b.getPawn(x,y) == State.WHITE || b.getPawn(x,y) == State.WKING) {
					return 5;
				} else {
					return 0;
				}
			} else if (x == (b.getHeight()-1)) {
				if (b.getPawn(x,y) == State.BLACK || b.getPawn(x,y) == State.BKING) {
					return 5;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		}

		// Driver
    public static void main(String args[]) {
				// int[] a = RandomBot.moveTo(0,0,2);
				// System.out.println(a[0]);
				// System.out.println(a[1]);
				// Eval e = new Eval();
				// Board b2 = new Board();
				// b2.movePawn(2,0,3,1);
				// b2.movePawn(5,1,4,0);
				// b2.movePawn(2,2,3,3);
				// b2.movePawn(5,3,4,2);
				// b2.showBoard();
				// Board b3 = new Board(b2);
				// b3.showBoard();
    }
}

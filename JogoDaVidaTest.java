import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JogoDaVidaTest {
	
	public int vivos(int testBoard[][], int i, int j) {
		int vivos = 0;
		
		if(!JogoDaVida.emptyBoard(testBoard)) {
			for(int l = 0; l < 6; l++) {
				for(int c = 0; c < 6; c++) {
					if(i == 0 && j == 0) {
						if(testBoard[0][1] == 1) vivos++;
						if(testBoard[1][0] == 1) vivos++;
						if(testBoard[1][1] == 1) vivos++;
					}
					else if(i == 0 && j == 5) {
						if(testBoard[0][4] == 1) vivos++;
						if(testBoard[1][4] == 1) vivos++;
						if(testBoard[1][5] == 1) vivos++;
					}
					else if(i == 5 && j == 0) {
						if(testBoard[4][0] == 1) vivos++;
						if(testBoard[4][1] == 1) vivos++;
						if(testBoard[5][1] == 1) vivos++;
						
					}
					else if(i == 5 && j == 5) {
						if(testBoard[4][5] == 1) vivos++;
						if(testBoard[4][4] == 1) vivos++;
						if(testBoard[5][4] == 1) vivos++;
					}
					else if(i == 0 && (j > 0 && j < 5)) {
						if(testBoard[i][j-1] == 1) vivos++;
						if(testBoard[i][j+1] == 1) vivos++;
						if(testBoard[i+1][j-1] == 1) vivos++;
						if(testBoard[i+1][j] == 1) vivos++;
						if(testBoard[i+1][j+1] == 1) vivos++;			
					}
					else if(i == 5 && (j > 0 && j < 5)) {
						if(testBoard[i][j-1] == 1) vivos++;
						if(testBoard[i][j+1] == 1) vivos++;
						if(testBoard[i-1][j-1] == 1) vivos++;
						if(testBoard[i-1][j] == 1) vivos++;
						if(testBoard[i-1][j+1] == 1) vivos++;
					}
					else if(j == 0 && (i > 0 && i < 5)) {
						if(testBoard[i-1][j] == 1) vivos++;
						if(testBoard[i+1][j] == 1) vivos++;
						if(testBoard[i-1][j+1] == 1) vivos++;
						if(testBoard[i][j+1] == 1) vivos++;
						if(testBoard[i+1][j+1] == 1) vivos++;
						
					}
					else if(j == 5 && (i > 0 && i < 5)) {
						if(testBoard[i-1][j] == 1) vivos++;
						if(testBoard[i+1][j] == 1) vivos++;
						if(testBoard[i-1][j-1] == 1) vivos++;
						if(testBoard[i][j-1] == 1) vivos++;
						if(testBoard[i+1][j-1] == 1) vivos++;
					}
					else {
						if(testBoard[i-1][j-1] == 1) vivos++;
						if(testBoard[i-1][j] == 1) vivos++;
						if(testBoard[i-1][j+1] == 1) vivos++;
						if(testBoard[i][j-1] == 1) vivos++;
						if(testBoard[i][j+1] == 1) vivos++;
						if(testBoard[i+1][j-1] == 1) vivos++;
						if(testBoard[i+1][j] == 1) vivos++;
						if(testBoard[i+1][j+1] == 1) vivos++;
					}
				}
			}	
		}
		
		return vivos;	
	}

	public int vizinhos(int i, int j) {
		if(i == 0 && j == 0 || i == 0 && j == 5 || i == 5 && j == 5 || i == 5 && j == 5) {
			return 3;
		}
		else if((i == 0 && (j > 0 && j < 5)) || (i == 5 && (j > 0 && j < 5)) || (j == 0 && (i > 0 && i < 5)) || (j == 5 && (i > 0 && i < 5))) {
			return 5;
		}
				
		else {
			return 8;
		}
	}
	
	@Test
	void ClassesEq_CT1() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean morto = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 3 && vivos(testBoard, i, j) == 0) {
					//System.out.println("[" + i + "][" + j + "]" + " = " + testBoard[i][j]);
					JogoDaVida.newState(auxBoard, testBoard);
					
					if(JogoDaVida.failure(testBoard, auxBoard) == false) morto = false;
					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) morto = false;	
				}
			}
		}
		assertEquals(true, morto);
	}
	@Test
	void ClassesEq_CT2() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean morto = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 5 && vivos(testBoard, i, j) == 2) {
					JogoDaVida.newState(auxBoard, testBoard);
					
					if(JogoDaVida.failure(testBoard, auxBoard) == false) morto = false;
					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) morto = false;	
				}
			}
		}
		assertEquals(true, morto);
	}
	@Test
	void ClassesEq_CT3() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean vivo = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 8 && vivos(testBoard, i, j) == 4) {
					JogoDaVida.newState(auxBoard, testBoard);
					
					if(JogoDaVida.failure(testBoard, auxBoard) == false) vivo = false;
					
					int C = testBoard[i][j];
					if(C <= 0 || C > 1) vivo = false;	
				}
			}
		}
		assertEquals(true, vivo);
	}
	@Test
	void ClassesEq_CT4() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean morto = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 8 && vivos(testBoard, i, j) == 3) {
					JogoDaVida.newState(auxBoard, testBoard);
					
					if(JogoDaVida.failure(testBoard, auxBoard) == false) morto = false;
					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) morto = false;	
				}
			}
		}
		assertEquals(true, morto);
	}
	@Test
	void ClassesEq_CT5() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(-2);
		auxBoard = testBoard;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 2 && vivos(testBoard, i, j) == -1) {
					JogoDaVida.newState(auxBoard, testBoard);
					
					if(JogoDaVida.failure(testBoard, auxBoard) == false) invalido = false;
					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);	
	}
	@Test
	void ClassesEq_CT6() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(3);
		auxBoard = testBoard;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 4 && vivos(testBoard, i, j) == -1) {
					JogoDaVida.newState(auxBoard, testBoard);
					
					if(JogoDaVida.failure(testBoard, auxBoard) == false) invalido = false;
					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);
	}
	@Test
	void ClassesEq_CT7() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(3);
		auxBoard = testBoard;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 6 && vivos(testBoard, i, j) == -1) {
					JogoDaVida.newState(auxBoard, testBoard);
					
					if(JogoDaVida.failure(testBoard, auxBoard) == false) invalido = false;
					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);
	}
	@Test
	void ClassesEq_CT8() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(3);
		auxBoard = testBoard;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 9 && vivos(testBoard, i, j) == 9) {
					JogoDaVida.newState(auxBoard, testBoard);
					
					if(JogoDaVida.failure(testBoard, auxBoard) == false) invalido = false;
					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);
	}
}
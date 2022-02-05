package project;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JogoDaVidaTest {
	
	public static int vivos(int testBoard[][], int i, int j) {
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

	public static int vizinhos(int i, int j) {
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
	
	//TestSet-Func
	@Test
	public void classesEquivalencia_CT1() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean morto = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 3 && vivos(testBoard, i, j) == 0) {
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) morto = false;	
				}
			}
		}
		assertEquals(true, morto);
	}
	@Test
	public void classesEquivalencia_CT2() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean morto = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 5 && vivos(testBoard, i, j) == 2) {					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) morto = false;	
				}
			}
		}
		assertEquals(true, morto);
	}
	@Test
	public void classesEquivalencia_CT3() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean vivo = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
				
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 8 && vivos(testBoard, i, j) == 4) {				
					int C = testBoard[i][j];
					if(C <= 0 || C > 1) vivo = false;	
				}
			}
		}
		assertEquals(true, vivo);
	}
	@Test
	public void classesEquivalencia_CT4() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean morto = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 8 && vivos(testBoard, i, j) == 3) {
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) morto = false;	
				}
			}
		}
		assertEquals(true, morto);
	}
	@Test
	public void classesEquivalencia_CT5() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(-2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 2 && vivos(testBoard, i, j) == -1) {					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);	
	}
	@Test
	public void classesEquivalencia_CT6() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(3);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 4 && vivos(testBoard, i, j) == -1) {
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);
	}
	@Test
	public void classesEquivalencia_CT7() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(3);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 6 && vivos(testBoard, i, j) == -1) {
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);
	}
	@Test
	public void classesEquivalencia_CT8() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(3);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 9 && vivos(testBoard, i, j) == 9) {				
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);
	}
	
	@Test
	public void valorLimite_CT1() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean morto = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 3 && vivos(testBoard, i, j) == 0) {					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) morto = false;	
				}
			}
		}
		assertEquals(true, morto);
	}
	@Test
	public void valorLimite_CT2() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean morto = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 5 && vivos(testBoard, i, j) == 1) {
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) morto = false;	
				}
			}
		}
		assertEquals(true, morto);
	}
	@Test
	public void valorLimite_CT3() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean vivo = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
				
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 8 && vivos(testBoard, i, j) == 7) {
					int C = testBoard[i][j];
					if(C <= 0 || C > 1) vivo = false;	
				}
			}
		}
		assertEquals(true, vivo);
	}
	@Test
	public void valorLimite_CT4() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean morto = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 8 && vivos(testBoard, i, j) == 8) {			
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) morto = false;	
				}
			}
		}
		assertEquals(true, morto);
	}
	@Test
	public void valorLimite_CT5() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(-2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 2 && vivos(testBoard, i, j) == -1) {					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);	
	}
	@Test
	public void valorLimite_CT6() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(3);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 4 && vivos(testBoard, i, j) == 9) {					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);
	}
	@Test
	public void valorLimite_CT7() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(3);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 6 && vivos(testBoard, i, j) == 9) {					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);
	}
	@Test
	public void valorLimite_CT8() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(3);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 7 && vivos(testBoard, i, j) == 9) {					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);
	}
	@Test
	public void valorLimite_CT9() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(3);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 9 && vivos(testBoard, i, j) == 9) {
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);
	}
	
	@Test
	public void causaEfeito_CT1() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean morto = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 5 && vivos(testBoard, i, j) == 2) {
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) morto = false;	
				}
			}
		}
		assertEquals(true, morto);
	}
	@Test
	public void causaEfeito_CT2() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean morto = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 5 && vivos(testBoard, i, j) == 9) {
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) morto = false;	
				}
			}
		}
		assertEquals(true, morto);
	}
	@Test
	public void causaEfeito_CT3() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean vivo = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
				
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 2 && vivos(testBoard, i, j) == 1) {
					int C = testBoard[i][j];
					if(C <= 0 || C > 1) vivo = false;	
				}
			}
		}
		assertEquals(true, vivo);
	}
	@Test
	public void causaEfeito_CT4() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean morto = true;
		
		testBoard = JogoDaVida.initialRandom(4);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 2 && vivos(testBoard, i, j) == 1) {
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) morto = false;	
				}
			}
		}
		assertEquals(true, morto);
	}
	@Test
	public void causaEfeito_CT5() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 2 && vivos(testBoard, i, j) == 9) {				
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);	
	}
	@Test
	public void causaEfeito_CT6() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(4);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 5 && vivos(testBoard, i, j) == 9) {
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);
	}
	@Test
	public void causaEfeito_CT7() {
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean invalido = true;
		
		testBoard = JogoDaVida.initialRandom(4);
		auxBoard = testBoard;
		
		JogoDaVida.newState(auxBoard, testBoard);
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(vizinhos(i, j) == 4 && vivos(testBoard, i, j) == 2) {					
					int C = testBoard[i][j];
					if(C < 0 || C >= 1) invalido = false;	
				}
			}
		}
		assertEquals(true, invalido);
	}
	//TestSet-Func
	
	//TestSet-Estr
	@Test
	public void estrutural_CT1(){
		int testBoard[][] = new int[6][6];
		boolean falso = true;
		
		testBoard = JogoDaVida.initialRandom(100);
		
		if(JogoDaVida.emptyBoard(testBoard) == true) falso = false;
		
		assertEquals(true, falso);
	}
	
	@Test
	public void estrutural_CT2(){
		int testBoard[][] = new int[6][6];
		boolean verdadeiro = true;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				testBoard[i][j] = 0;
			}
		}
		
		if(JogoDaVida.emptyBoard(testBoard) == false) verdadeiro = false;
		
		assertEquals(true, verdadeiro);
	}
	
	@Test
	public void estrutural_CT3(){
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean falso = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = testBoard;

		if(JogoDaVida.failure(testBoard, auxBoard) == false) falso = false;
		
		assertEquals(true, falso);
	}
	
	@Test
	public void estrutural_CT4(){
		int testBoard[][] = new int[6][6];
		int auxBoard[][] = new int [6][6]; 
		boolean verdadeiro = true;
		
		testBoard = JogoDaVida.initialRandom(2);
		auxBoard = JogoDaVida.initialRandom(4);

		if(JogoDaVida.failure(testBoard, auxBoard) == true) verdadeiro = false;
		
		assertEquals(true, verdadeiro);
		
	}
	//TestSet-Estr
}
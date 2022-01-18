import java.util.Random;
import java.util.Scanner;

public class JogoDaVida {

	public static int[][] initialRandom(int x) {
		Random random = new Random();
		int board[][] = new int [6][6];
		int number = Math.abs(x);
		
		if(x < 0) {
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					board[i][j] = x - random.nextInt(number);
				}
			}
		}
		else {
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					board[i][j] = random.nextInt(x);
				}
			}
		}
			
		return board;
	}

	public static void newState(int auxBoard[][], int board[][]) {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				// quina = 3 vizinhos
				if(i == 0 && j == 0 || i == 0 && j == 5 || i == 5 && j == 0 || i == 5 && j == 5) {
					board[i][j] = corner(i, j, auxBoard);
				}
				// parede = 5 vizinhos
				else if(i == 0 || i == 5 || j == 0 || j == 5) {
					board[i][j] = wall(i, j, auxBoard);
				}
				// 8 vizinhos
				else {
					board[i][j] = core(i, j, auxBoard);
				}	
			}
		}
	}
	
	public static int corner(int i, int j, int[][] auxBoard) {
		int vivos = 0;
		
		if(i == 0 && j == 0) {
			if(auxBoard[0][1] == 1) vivos++;
			if(auxBoard[1][0] == 1) vivos++;
			if(auxBoard[1][1] == 1) vivos++;
		}
		else if(i == 0 && j == 5) {
			if(auxBoard[0][4] == 1) vivos++;
			if(auxBoard[1][4] == 1) vivos++;
			if(auxBoard[1][5] == 1) vivos++;
		}
		else if(i == 5 && j == 0) {
			if(auxBoard[4][0] == 1) vivos++;
			if(auxBoard[4][1] == 1) vivos++;
			if(auxBoard[5][1] == 1) vivos++;
			
		}
		else if(i == 5 && j == 5) {
			if(auxBoard[4][5] == 1) vivos++;
			if(auxBoard[4][4] == 1) vivos++;
			if(auxBoard[5][4] == 1) vivos++;
		}

		if(vivos < 2) return 0; //morre por solidão
		else if(vivos == 2) return auxBoard[i][j]; //mantém o estado atual
		else return 1; //vive
	}
	
	public static int wall(int i, int j, int[][] auxBoard) {
		int vivos = 0;
		
		if(i == 0 && (j > 0 && j < 5)) {
			if(auxBoard[i][j-1] == 1) vivos++;
			if(auxBoard[i][j+1] == 1) vivos++;
			if(auxBoard[i+1][j-1] == 1) vivos++;
			if(auxBoard[i+1][j] == 1) vivos++;
			if(auxBoard[i+1][j+1] == 1) vivos++;			
		}
		else if(i == 5 && (j > 0 && j < 5)) {
			if(auxBoard[i][j-1] == 1) vivos++;
			if(auxBoard[i][j+1] == 1) vivos++;
			if(auxBoard[i-1][j-1] == 1) vivos++;
			if(auxBoard[i-1][j] == 1) vivos++;
			if(auxBoard[i-1][j+1] == 1) vivos++;
		}
		else if(j == 0 && (i > 0 && i < 5)) {
			if(auxBoard[i-1][j] == 1) vivos++;
			if(auxBoard[i+1][j] == 1) vivos++;
			if(auxBoard[i-1][j+1] == 1) vivos++;
			if(auxBoard[i][j+1] == 1) vivos++;
			if(auxBoard[i+1][j+1] == 1) vivos++;
			
		}
		else if(j == 5 && (i > 0 && i < 5)) {
			if(auxBoard[i-1][j] == 1) vivos++;
			if(auxBoard[i+1][j] == 1) vivos++;
			if(auxBoard[i-1][j-1] == 1) vivos++;
			if(auxBoard[i][j-1] == 1) vivos++;
			if(auxBoard[i+1][j-1] == 1) vivos++;
		}
		
		if(vivos < 2 || vivos > 3) return 0; //morre por solidão ou super população
		else if(vivos == 2) return auxBoard[i][j]; //mantém o estado atual
		else return 1; //vive
	}
	
	public static int core(int i, int j, int[][] auxBoard) {
		int vivos = 0;
		
		if(auxBoard[i-1][j-1] == 1) vivos++;
		if(auxBoard[i-1][j] == 1) vivos++;
		if(auxBoard[i-1][j+1] == 1) vivos++;
		if(auxBoard[i][j-1] == 1) vivos++;
		if(auxBoard[i][j+1] == 1) vivos++;
		if(auxBoard[i+1][j-1] == 1) vivos++;
		if(auxBoard[i+1][j] == 1) vivos++;
		if(auxBoard[i+1][j+1] == 1) vivos++;
		
		if(vivos < 2 || vivos > 3) return 0; //morre por solidão ou super população
		else if(vivos == 2) return auxBoard[i][j]; //mantém o estado atual
		else return 1; //vive 
	}
	
	public static boolean emptyBoard(int[][] board) {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(board[i][j] != 0) {
					return false;
				}	
			}
		}
		
		return true;
	}
	
	public static boolean failure(int[][] board, int[][] auxBoard) {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(board[i][j] != auxBoard[i][j]) {
					return false;
				}	
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int board[][] = new int [6][6]; 
		
		board = initialRandom(2);
		
		System.out.println("Estado inicial:");
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(board[i][j] + "  ");
			}
			System.out.println("\n");
		}
		
		while(true) {
			int auxBoard[][] = new int [6][6]; 
		
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					auxBoard[i][j] = board[i][j];
				}
			}
			
			newState(auxBoard, board);
			
			System.out.println("Novo Estado:");
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					System.out.print(board[i][j] + "  ");
				}
				System.out.println("\n");
			}
			
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in); 
		    System.out.println("Pressione: \n(1) para continuar \n(Outro) para terminar o programa");
		    int read = input.nextInt();
		   
		    if(read != 1) {
		    	System.out.println("Finalizando...");
		    	break;
		    }	
		    else if(emptyBoard(board) == true) {
		    		System.out.println("Todas as células são 0 \nO jogo chegou ao fim!");
		    		break;		
		    }
		    else if(failure(board, auxBoard) == true) {
		    	System.out.println("O jogo chegou a um estado de impasse \nFinalizando...");
	    		break;
		    }
		}
	}
}

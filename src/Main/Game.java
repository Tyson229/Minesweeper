package Main;

public class Game {
	public String[][] field = new String[12][12];
	public String[][] display = new String[12][12];
	
	private String unknown = " ? ";
	private String mine = " * ";
	private String empty = "   ";
	
	public Game() {
		for(int x = 0; x < field.length; x++) {
			for(int y = 0 ; y < field[0].length; y++) {
				// This is just to create border for the field
				if((x == 0 || x == field.length-1) && (y == 0 || y == field[0].length-1)) {
					field[x][y] = empty;
					display[x][y] = empty;
				}else if (x == 0 || x == field[0].length-1 && y < field[0].length-1) {
					field[x][y] = " " + y + " ";
					display[x][y] = " " + y + " ";
				}else if (y == 0 || y == field[0].length-1 && x < field[0].length-1) {
					field[x][y] = " " + x + " ";
					display[x][y] = " " + x + " ";
				}else {
					field[x][y] = unknown;
					display[x][y] = unknown;
				}
			}
		}
	}
	
	public void printField() {
		for(int x = 0 ; x < display.length; x++) {
			for(int y = 0 ; y < display[0].length ; y++) {
				System.out.print(display[x][y]);
			}
			System.out.println("");
		}
		
	}
	
	public void generateMine(int n) {
		int m = 0;
		int x,y;
		while(m < n) {
			// Get random number for x
			// Get random number for y
			x =  (int) (10*Math.random());
			y =  (int) (10*Math.random());
			
			// Check if mine has been landed here
			if(field[x][y].equals(mine))
				continue;
			
			if(field[x][y].equals(unknown))	{
				m++;
				field[x][y] = mine;
			}
		}
	}
	
	public boolean isNotValid (int x , int y) {
		if(!field[x][y].equals(unknown))
			return false;
		return true;
	}
	
	public boolean stepOnMine(int x, int y) {
		if(field[x][y].equals(mine))
			return true;
		return false;
	}
	
	public void detect(int x, int y) {
		int noMines = 0;
		for(int a = x -1 ; a <= x + 1 ; a++) {
			for(int b = y - 1 ; b <= y + 1 ; b++) {
				if(field[a][b].equals(mine)) {
					noMines++;
				} else if(field[a][b].equals(unknown)) {
					field[a][b] = empty;
					display[a][b] = empty;
				}
			}
		}
		if(noMines > 0) {
			field[x][y] = " " + noMines + " ";
			display[x][y] = " " + noMines + " ";			
		} else {
			field[x][y] = empty;
			display[x][y] = empty;
		}
	}
}


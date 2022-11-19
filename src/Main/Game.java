package Main;

public class Game {
	private String[][] field ;
	private String[][] display;
	
	private String unknown = " ? ";
	private String mine = " * ";
	private String empty = "   ";
	
	private int clearedTiles = 0;
	private int noMines = 0;
	
	// Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";
  
    // Declaring the color
    // Custom declaration
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m	";
	public Game(int row, int col) {
		this.field = new String[row+2][col+2];
		this.display = new String[row+2][col+2];
		
		for(int x = 0; x < field.length; x++) {
			for(int y = 0 ; y < field[0].length; y++) {
				// This is just to create border for the field
				if(x == 0 || x == field.length-1) {
					// For the corner
					if(y == 0 || y == field[0].length-1) {
						field[x][y] = empty;
						display[x][y] = empty;
					}
					// For the heading of columns
					if(y > 0 && y < field[0].length-1) {
						if(y > 9) {
							field[x][y] = " " + y;
							display[x][y] = " " + y;	
						}else {
							field[x][y] = " " + y + " ";
							display[x][y] = " " + y + " ";
						}
					}
				}
				// For the heading of the rows
				else if (x > 0 && x < field.length-1 && (y == 0 || y == field[0].length-1)) {
					if(x > 9) {
						field[x][y] = " " + x ;
						display[x][y] = " " + x ;
					} else {
						field[x][y] = " " + x + " ";
						display[x][y] = " " + x + " ";
					}
				}
				else {
					field[x][y] = unknown;
					display[x][y] = unknown;
				}
			}
		}
	}
	
	public void printField() {
		for(int x = 0 ; x < display.length; x++) {
			for(int y = 0 ; y < display[0].length ; y++) {
				if(display[x][y].equals(mine))
					System.out.print(ANSI_RED + "* " + ANSI_RESET);
				else if(!display[x][y].equals(empty) && !display[x][y].equals(unknown))
					System.out.print(ANSI_GREEN + display[x][y] + ANSI_RESET);
				else
					System.out.print(display[x][y]);
			}
			System.out.println("");
		}
		
	}
	
	public void generateMine(int n, int row, int col) {
		noMines = n;
		int m = 0;
		int x,y;
		while(m < n) {
			// Get random number for x
			// Get random number for y
			x =  (int) (row*Math.random());
			y =  (int) (col*Math.random());
			
			// Check if mine has been landed here
			if(field[x][y].equals(mine))
				continue;
			
			if(field[x][y].equals(unknown))	{
				m++;
				field[x][y] = mine;
			}
		}
	}
	
	public boolean isValid (int row, int col, int x , int y) {
		if(x < 1 || x > row || y < 1 || y > col)
			return false;
		if(!display[x][y].equals(unknown))
			return false;
		return true;
	}
	
	public boolean stepOnMine(int x, int y) {
		if(field[x][y].equals(mine)) {
			field[x][y] = mine;
			display[x][y] = mine;
			return true;
		}
		return false;
	}
	
	public void detect(int x, int y) {
		int noMines = 0;
		for(int a = x -1 ; a <= x + 1 ; a++) {
			for(int b = y - 1 ; b <= y + 1 ; b++) {
				if(field[a][b].equals(mine)) {
					noMines++;
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
		this.clearedTiles+=1;
	}
	

	
	public void clearMine(int row, int col,int x, int y) {
		if(field[x][y].equals(mine)) {
			int a =  (int) (row*Math.random());
			int b =  (int) (col*Math.random());
			while(field[a][b].equals(mine)) {
				a =  (int) (row*Math.random());
				b =  (int) (col*Math.random());
			}
			field[x][y] = unknown;
			field[a][b] = mine;
		}
	}
	
	public boolean allTilesCleared() {
		if((field.length-2)*(field[0].length-2)-noMines == clearedTiles)
			return true;
		return false;
	}
}


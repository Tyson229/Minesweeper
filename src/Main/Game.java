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
}

package Main;

import java.util.Scanner;

public class GameRunner {
	
	private Game game;
	private int x;
	private int y;
	
	private boolean isRunning = true;
	private boolean isWin = false;
	
	public GameRunner() {}
	public void init(int row , int col) {
		this.game = new Game(row,col);
		Scanner scanner = new Scanner(System.in);
		
		// Start the game
		game.generateMine(row,row,col);
		game.printField();
		System.out.println();
		
		getInput(scanner,row,col);
		// First move will never lose	
		game.clearMine(row,col,x,y); 	
		game.detect(x, y);
		game.printField();
		
		while(this.isRunning)	{
			getInput(scanner,row,col);
			if(game.stepOnMine(x, y)) {
				this.isRunning = false;
				continue;
			}
			game.detect(x, y);
			game.printField();
			if(game.allTilesCleared()) {
				this.isWin = true;
				this.isRunning = false;
			}
		}
			
		if(!this.isRunning) {
			if(this.isWin){
				System.out.println("*** You Won! ***");
			}
			else {
				game.printField();
				System.out.println("Boom! You Lose");
			}
		}
		scanner.close();
	}
	
	private void getInput (Scanner scanner,int row, int col) {
		
		boolean isValid = true;
		do {
			System.out.print("Enter x: ");
			this.x = scanner.nextInt();
			System.out.print("Enter y: ");
			this.y = scanner.nextInt();
			
			isValid = this.game.isValid(row, col, x, y);
			if(!isValid)
				System.out.println("Invalid input, please choose tile that has ? on it");
		}while(!isValid);

	}
}

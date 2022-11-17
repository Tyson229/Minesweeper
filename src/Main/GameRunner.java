package Main;

import java.util.Scanner;

public class GameRunner {
	
	public GameRunner() {
		
	}
	
	public void init() {
		Game game = new Game();
		game.generateMine(10);
		game.printField();
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		
		int x,y;
		
		boolean isRun = true;
		boolean isWon = false;
		
		boolean isNotValid = true;
		while(isRun && isWon == false)
		{
			do {
				System.out.print("Enter x: ");
				x = scanner.nextInt();
				System.out.print("Enter y: ");
				y = scanner.nextInt();
				
				isNotValid = game.isNotValid(x, y);
				if(isNotValid)
					System.out.println("Invalid input, please choose tile that has ? on it");
			}while(isNotValid);
			
		if(game.stepOnMine(x, y))
			isRun = false;
		else {
			game.detect(x, y);
			game.printField();
		}
		}
		
		if(isRun == false) {
			if(isWon){
				System.out.println("You Won!");
			}
			else {
				System.out.println("Boom! You Lose");
			}
		}
		
	}
	
}

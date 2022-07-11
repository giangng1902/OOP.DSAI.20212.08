package console;
import java.util.Scanner;

import board.Board;
import gem.*;
import square.*;
import player.*;
public class Console {
	static Player playingPlayer;
	static Board board;
	//static Scanner keyboard = new Scanner(System.in);
	
	public static void aPhase(Board board,Player playingPlayer, int position, int direction) {
		// 
		SmallGem gem = new SmallGem();
		playingPlayer.setPointInHand(board.getSquare(position).getPoint());
		board.getSquare(position).removeGem();
		int curPosition = position;
		while (playingPlayer.getPointInHand() > 0) {
			curPosition += direction;
			board.getSquare(curPosition).addGem(gem);
			playingPlayer.setPointInHand(playingPlayer.getPointInHand() - 1);
			//playingPlayer.pointInHand -= 1;
		}
	}
	

	
	public static void turn(Board board,Player playingPlayer, int position, int direction) { // thực hiện một lượt chơi khi đã có hướng và ô
		if (playingPlayer instanceof Player1) {
			playingPlayer = (Player1) playingPlayer;
		} else if (playingPlayer instanceof Player2) {
			playingPlayer = (Player2) playingPlayer;
		}
		
		while(true) {
			if(board.getSquare(position).getPoint() > 0) {
				int curPosition = position;
				int phasePoint = board.getSquare(position).getPoint();	 // number of gems in chosen square
				aPhase(board, playingPlayer, position, direction);
				curPosition = position + direction*phasePoint;
				while(true) {
					if(board.getSquare(curPosition + direction).getPoint() != 0 && !board.getSquare(curPosition + direction).getClass().getSimpleName().equals("HalfCircle")) {
						// Next square != halfcircle && point != 0 => continue
						System.out.println();
						int nextPosition = curPosition + direction;
						phasePoint = board.getSquare(nextPosition).getPoint();
						aPhase(board, playingPlayer, nextPosition, direction);
						curPosition = nextPosition + direction*phasePoint;
					}
					
					else if(board.getSquare(curPosition + direction).getClass().getSimpleName().equals("HalfCircle")) {
						// next square is a halfcircle and point != 0 => stop turn
						System.out.println("Stop because the next square is the half circle");
						break;
					}
					
					
					else if (board.getSquare(curPosition + direction).getPoint() == 0 && board.getSquare(curPosition + 2*direction).getPoint() != 0 ) {
						// next square has 0 gems
						while (board.getSquare(curPosition + direction).getPoint() == 0 && board.getSquare(curPosition + 2*direction).getPoint() != 0 && !board.getSquare(curPosition + direction).getClass().getSimpleName().equals("HalfCircle")) {
							System.out.println("Player " + playingPlayer.getName() + " take " + board.getSquare(curPosition + 2*direction).getPoint() + " gems");
							playingPlayer.bonusPoint(board.getSquare(curPosition + 2*direction).getPoint());
							board.getSquare(curPosition + 2*direction).removeGem();
							curPosition += 2*direction;
						}
						
						break;
					}
					else {
						System.out.println("You get 0 gem!");
						break;
					}
					
				}
				break;
			}
			else {
				System.out.println("The chosen square does not have any gems to disperse");
				break;
			}
		}
	}
	public static void aTurn(Board board, Player playingPlayer) { // thực hiện lượt chơi bao gồm: chọn ô, chọn hướng, chơi
 		if (playingPlayer instanceof Player1) {
			playingPlayer = (Player1) playingPlayer;
		} else if (playingPlayer instanceof Player2) {
			playingPlayer = (Player2) playingPlayer;
		}
		
 		// Choose square
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please choose a square in " + playingPlayer.getRange());
		int chosenSquare = keyboard.nextInt();
		while (playingPlayer.getRange().contains(chosenSquare) == false || board.getSquare(chosenSquare).getPoint() == 0) {
			System.out.println("You cannot choose this square!");
			System.out.println("Please choose another square in " + playingPlayer.getRange());
			chosenSquare = keyboard.nextInt();
		}
		
		// choose direction
		System.out.println("Please choose direction:");
		System.out.println("1: counter clockwise");
		System.out.println("-1: clockwise");
		int chosenDirection = keyboard.nextInt();
		while (chosenDirection != 1 && chosenDirection != -1) {
			System.out.println("This number is not available!");
			System.out.println("Please choose direction:");
			System.out.println("1: counter clockwise");
			System.out.println("-1: clockwise");
			chosenDirection = keyboard.nextInt();
		}
		
		turn(board, playingPlayer, chosenSquare, chosenDirection);
	}
	public static boolean stopTurn(Squares curSquare, int direction) { // kiểm tra điều kiện dừng lại một lượt chơi
		int curPosition = curSquare.getPosition();
		int nextPosition = curPosition + direction;
		if (playingPlayer.getPointInHand() == 0 && board.getSquare(nextPosition).getPoint() == 0) {
			return true;
		}
		return false;
	}
	public static boolean stopGame(Board board) { // kiểm tra điều kiện dừng game
		if (board.getSquare(0).getPoint() == 0 && board.getSquare(6).getPoint() == 0) {
			return true;
		}
		return false;
	}
	public static void takeTurn(Player1 player1, Player2 player2) { // đổi người thực hiện lượt chơi
		if (playingPlayer == player1 ) {
			playingPlayer = player2;
		} else if (playingPlayer == (player2)) {
			playingPlayer = player1;
		}
		System.out.println("Player " + playingPlayer.infor() + " start turn");
	}
	public static void gameResult(Player1 player1, Player2 player2) {  // kết quả game
		if (player1.getTotalPoint() > player2.getTotalPoint()) {
			System.out.println(player1.infor() + " wins");
		} else if (player1.getTotalPoint() < player2.getTotalPoint()) {
			System.out.println(player2.infor() + " wins");
		} else {
			System.out.println("Draw");
		}
	}
	
	
	public static boolean checkNeedRefill(Board board, Player playingPlayer) { 
		// if all squares which playing player can choose are empty => true
		
		int sum = 0;
		
		if (playingPlayer instanceof Player1) {
			playingPlayer = (Player1) playingPlayer;
		} else if (playingPlayer instanceof Player2) {
			playingPlayer = (Player2) playingPlayer;
		}
		
		for (int i:playingPlayer.getRange()) {
			sum += board.getSquare(i).getPoint();
		}
		if (sum != 0) {
			return false;
		}
		System.out.println(playingPlayer.infor() + " need to refill your square!");
		return true;
	}
	public static void refillGems(Board board, Player playingPlayer) {
		SmallGem gem = new SmallGem();
		// downcast class
		if (playingPlayer instanceof Player1) {
			playingPlayer = (Player1) playingPlayer;
		} else if (playingPlayer instanceof Player2) {
			playingPlayer = (Player2) playingPlayer;
		}
		
		// if all squares which playing player can choose are empty ==> refill 1 gem to all squares
		for (int i:playingPlayer.getRange()) {
			board.getSquare(i).addGem(gem);
		}
		playingPlayer.minusPoint(5);
		
		
	}

	public static void main(String[] args) {
		
		Board board = new Board();
		
		Scanner keyboard = new Scanner(System.in);
		
		// Players initialization
		System.out.println("Enter the first player's name: ");
		String name1 = keyboard.next();
		Player1 player1 = new Player1(name1);
		
		System.out.println("Enter the second player's name: ");
		String name2 = keyboard.next();
		Player2 player2 = new Player2(name2);
		
		// who plays first
		playingPlayer = player1;
		board.print();
		
		// Start game
		while (stopGame(board) == false) {
			
			if (checkNeedRefill(board, playingPlayer) == true) {
				refillGems(board, playingPlayer);
			}
			
			aTurn(board, playingPlayer);
			board.print();
			System.out.println("Player " + player1.infor() + " has " + player1.getTotalPoint() + " gems");
			System.out.println("Player " + player2.infor() + " has " + player2.getTotalPoint() + " gems");
			System.out.println("=======================================================");
			takeTurn(player1, player2);
			
		}
		
		gameResult(player1, player2);
		
	}
	
}
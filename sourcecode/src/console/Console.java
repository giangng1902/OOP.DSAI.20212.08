package console;
import java.util.List;
import java.util.Scanner;

import board.Board;
import gem.*;
import square.*;
import player.*;
public class Console {
	public static Player playingPlayer;
	public static Board board;
	
	public static void aPhase(Board board,Player playingPlayer, int position, int direction) { //done // một lần rải sải
		SmallGem gem = new SmallGem();
		playingPlayer.pointInHand = board.getSquare(position).getPoint();
		board.getSquare(position).removeGem();
		int curPosition = position;
		while (playingPlayer.pointInHand > 0) {
			curPosition += direction;
			board.getSquare(curPosition).addGem(gem);
			playingPlayer.pointInHand -= 1;
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
				int phasePoint = board.getSquare(position).getPoint();	
				aPhase(board, playingPlayer, position, direction);
				curPosition = position + direction*phasePoint;
				while(true) {
					if(board.getSquare(curPosition + direction).getPoint() != 0 && !board.getSquare(curPosition + direction).getClass().getSimpleName().equals("HalfCircle")) {
						System.out.println();
						int nextPosition = curPosition + direction;
						phasePoint = board.getSquare(nextPosition).getPoint();
						aPhase(board, playingPlayer, nextPosition, direction);
						curPosition = nextPosition + direction*phasePoint;
					}
					else if (board.getSquare(curPosition + direction).getPoint() == 0 && board.getSquare(curPosition + 2*direction).getPoint() != 0 ) {
						System.out.println("Player " + playingPlayer.getName() + " take " + board.getSquare(curPosition + 2*direction).getPoint() + " gems");
						playingPlayer.bonusPoint(board.getSquare(curPosition + 2*direction).getPoint());
						board.getSquare(curPosition + 2*direction).removeGem();
						break;
					}
					else if(board.getSquare(curPosition + direction).getClass().getSimpleName().equals("HalfCircle")) {
						System.out.println("Stop because the next square is the half circle");
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
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please choose a square in " + playingPlayer.getRange());
		int chosenSquare = keyboard.nextInt();
		while (playingPlayer.getRange().contains(chosenSquare) == false) {
			System.out.println("Please choose a square in " + playingPlayer.getRange());
			chosenSquare = keyboard.nextInt();
		}
		
		System.out.println("Please choose direction:");
		System.out.println("1: counter clockwise");
		System.out.println("-1: clockwise");
		int chosenDirection = keyboard.nextInt();
		while (chosenDirection != 1 && chosenDirection != -1) {
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
		if (playingPlayer.pointInHand == 0 && board.getSquare(nextPosition).getPoint() == 0) {
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
	/*
	public void checkReFill(Player playingPlayer) {
		SmallGem gem = new SmallGem();

		if (playingPlayer.equals("player1")) {
			int i = 0;
			boolean refill = true;
			while(i < 5) {
				if (board.getSquare(i).getPoint() == 0) {
					refill = false;
				}
				i++;
			}
			if(refill == true) {
				for(int j = 0; j < 5; j++) {
					board.getSquare(j).addGem(gem);	
				}
				if (playingPlayer.getTotalPoint() >=5 ) {
					playingPlayer.minusPoint(5);
				}
				else {
					System.out.println("Player 1 borrow 5 gems from player 2");

				}
			}
		}
	}
	*/
	
	public static boolean checkNeedRefill(Player playingPlayer) { // kiểm tra điều kiện bổ sung sỏi của người đnag thực hiện lượt chơi
		int sum = 0;
		
		if (playingPlayer instanceof Player1) {
			playingPlayer = (Player1) playingPlayer;
		} else if (playingPlayer instanceof Player2) {
			playingPlayer = (Player2) playingPlayer;
		}
		for (int i:playingPlayer.getRange()) {
			sum += board.getSquare(i).getPoint();
		}
		if (sum == 0) {
			return true;
		}
		
		return false;
	}
	public static void refillGems(Board board, Player playingPlayer) {
		SmallGem gem = new SmallGem();
		// downcast class
		if (playingPlayer instanceof Player1) {
			playingPlayer = (Player1) playingPlayer;
		} else if (playingPlayer instanceof Player2) {
			playingPlayer = (Player2) playingPlayer;
		}
		
		for (int i:playingPlayer.getRange()) {
			board.getSquare(i).addGem(gem);
		}
		playingPlayer.minusPoint(5);
		
		
	}

	public static void main(String[] args) {
		
		Board board = new Board();
		
		Scanner keyboard = new Scanner(System.in);
		
		// khoi tao nguoi choi
		System.out.println("Enter the first player's name: ");
		String name1 = keyboard.next();
		Player1 player1 = new Player1(name1);
		
		System.out.println("Enter the second player's name: ");
		String name2 = keyboard.next();
		Player2 player2 = new Player2(name2);
		
		// nguoi choi dau tien
		playingPlayer = player2;
		//System.out.println(playingPlayer == player2);
		
		// bat dau tro choi
		while (stopGame(board) == false) {
			if (checkNeedRefill(playingPlayer)) {
				refillGems(board, playingPlayer);
			}
			aTurn(board, playingPlayer);
			board.print();
			takeTurn(player1, player2);
			
		}
		
		gameResult(player1, player2);
		
	}
	
}
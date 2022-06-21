package console;
import java.util.List;

import javax.swing.JOptionPane;

import board.Board;
import gem.*;
import square.*;
import player.Player;
public class Console {
	public static Player playingPlayer;
	public static Board board;
	
	public static void aPhase(Board board,Player playingPlayer, int position, int direction) { //done
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
	
	/*public static void turn(Board board,Player playingPlayer, int position, int direction) {		
		while(true) {
			if(board.getSquare(position).getPoint() > 0) {
				playingPlayer.pointInHand = board.getSquare(position).getPoint();
				int currentPosition = position + direction*board.getSquare(position).getPoint();
				aPhase(board, playingPlayer,position, direction);
				int nextPosition = currentPosition  + direction;
				board.print();
				//System.out.println("Next Position: " + nextPosition + " " + board.getSquare(nextPosition).getPoint());
				while(true) { 
					
					
					if(board.getSquare(nextPosition).getPoint() != 0 && (playingPlayer.pointInHand == 0) && !board.getSquare(nextPosition).getClass().getSimpleName().equals("HalfCircle")){							
							System.out.println("Before: " + nextPosition);
							aPhase(board, playingPlayer, nextPosition, direction);
							currentPosition += direction*board.getSquare(nextPosition).getPoint() + direction;		
							board.print();
							System.out.println("Current point: " + currentPosition);
							System.out.println("Point of the chosen square: " + board.getSquare(nextPosition + direction).getPoint());
							nextPosition += direction*board.getSquare(nextPosition + direction).getPoint();
							System.out.println("After: " + nextPosition);

					}
					else if(board.getSquare(nextPosition).getClass().getSimpleName().equals("HalfCircle")){
						System.out.println("*******");
						if(board.getSquare(nextPosition).getPoint() != 0) {
							
							break;
							
						}
						else {
							if(board.getSquare(nextPosition + direction ).getPoint() == 0) {								
								break;
							}
							else {							
								System.out.println("Player 1 won " +  board.getSquare(nextPosition + direction).getPoint() +" gems from square " + board.getSquare(nextPosition + direction).getPosition());
								playingPlayer.bonusPoint(board.getSquare(nextPosition + direction).getPoint());
							}
						}
					}
					else {
						System.out.println("Player 1 won " +  board.getSquare(nextPosition + direction).getPoint() +" gems from square " + board.getSquare(nextPosition + direction).getPosition());
						playingPlayer.bonusPoint(board.getSquare(nextPosition + direction).getPoint());
						break;
					}
		
				}
				//System.out.println("***********");
				board.print();
				break;
			}
			else {
				System.out.println("The chosen square does not have any gems to disperse.");
				break;
			}
		}
		System.out.println("End turn");
		System.out.println("----------\n");
	}*/
	
	public static void turn(Board board,Player playingPlayer, int position, int direction) {
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
						System.out.println("Player " + playingPlayer.getName() + " won " + board.getSquare(curPosition + 2*direction).getPoint() + " gems");
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
	
	public static boolean stopTurn(Squares curSquare, int direction) {
		int curPosition = curSquare.getPosition();
		int nextPosition = curPosition + direction;
		if (playingPlayer.pointInHand == 0 && board.getSquare(nextPosition).getPoint() == 0) {
			return true;
		}
		return false;
	}
	public static boolean stopGame(List<Squares> board) {
		if (board.get(0).getPoint() == 0 && board.get(6).getPoint() == 0) {
			return true;
		}
		return false;
	}
	public static void takeTurn(Player player1, Player player2) {
		if (playingPlayer == player1) {
			playingPlayer = player2;
		} else if (playingPlayer == player2) {
			playingPlayer = player1;
		}
	}
	public static void gameResult(Player player1, Player player2) {
		if (player1.getTotalPoint() > player2.getTotalPoint()) {
			System.out.println(player1.infor() + " wins");
		} else if (player1.getTotalPoint() < player2.getTotalPoint()) {
			System.out.println(player2.infor() + " wins");
		} else {
			System.out.println("Draw");
		}
	}
	
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
		
	public static void main(String[] args) {
		Board board = new Board();
		Player player1 = new Player("player 1");
		
		turn(board, player1, 4, -1);
		board.print();
		turn(board, player1, 8, -1);
		board.print();
		turn(board, player1, 2, 1);
		board.print();
		turn(board, player1, 7, 1);
		board.print();
		turn(board, player1, 2, -1);
		board.print();
		turn(board, player1, 11, -1);
		board.print();
		turn(board, player1, 5, 1);
		board.print();
		System.out.println(player1.getTotalPoint());
	}
	
}
package console;
import java.util.List;

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
	
	public static void turn(Board board,Player playingPlayer, int position, int direction) {		
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
								board.getSquare(nextPosition + direction).removeGem();
							}
						}
					}
					else {
						System.out.println("Player 1 won " +  board.getSquare(nextPosition + direction).getPoint() +" gems from square " + board.getSquare(nextPosition + direction).getPosition());
						board.getSquare(nextPosition + direction).removeGem();
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
		if (player1.getPoint() > player2.getPoint()) {
			System.out.println(player1.infor() + " wins");
		} else if (player1.getPoint() < player2.getPoint()) {
			System.out.println(player2.infor() + " wins");
		} else {
			System.out.println("Draw");
		}
	}
	
	public static void wonPoint(int position) {
		
	}
	public static void main(String[] args) {
		Board board = new Board();
		Player player1 = new Player("aa");
		
		turn(board, player1, 4, -1);
		turn(board, player1, 8, -1);
		turn(board, player1, 2, 1);
		turn(board, player1, 7, 1);
		
	}
	
}
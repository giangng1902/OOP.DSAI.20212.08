package console;
import java.util.List;

import board.Board;
import gem.*;
import square.*;
import player.Player;
public class Console {
	public static Player playingPlayer;
	public static Board board;
	
	public static void aPhase(int position, int direction) {
		SmallGem gem = new SmallGem();
		Squares curSquare = board.getSquare(position);
		board.getSquare(position).removeGem();
		int curPosition = position;
		while (playingPlayer.pointInHand > 0) {
			curPosition += direction;
			board.getSquare(curPosition).addGem(gem);
			playingPlayer.pointInHand -= 1;
			
		}
	}
	/*
	public static Board aPhase(Board board, int position, int direction, SmallGem gem) {
		int curPosition = position;
		board.getSquare(position).removeGem(gem);
		while (board.getSquare(position).getPoint() != 0) {
			curPosition += direction;
			board.getSquare(curPosition).addGem(gem);
			board.getSquare(position).removeGem(gem);
		}
		return board;
	}
	*/
	public static void turn(int position, int direction) {
		playingPlayer.pointInHand = board.getSquare(position).getPoint();
		int curPosition = position;
		int nextPosition = curPosition + direction;
		while (board.getSquare(nextPosition ).getPoint() != 0 || playingPlayer.pointInHand != 0) {
			aPhase(curPosition, direction);
			
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
		if (player1.getPoint() > player2.getPoint()) {
			System.out.println(player1.infor() + " wins");
		} else if (player1.getPoint() < player2.getPoint()) {
			System.out.println(player2.infor() + " wins");
		} else {
			System.out.println("Draw");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board();
		
		
		board.print();
	}
	
}
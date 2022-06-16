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
	/*

	
	public static void turn(Board board,Player playingPlayer, int position, int direction) {
		playingPlayer.pointInHand = board.getSquare(position).getPoint();
		int curPosition = position;
		int nextPosition = curPosition + direction;
		while (board.getSquare(nextPosition ).getPoint() != 0 || playingPlayer.pointInHand != 0) {
			aPhase(board, playingPlayer,curPosition, direction);
			
		}
	}
	*/
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
		Player player1 = new Player("aa");
		aPhase(board, player1,3, 1);
		
		board.print();
	}
	
}
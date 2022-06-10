package player;
import java.util.*;

import board.Board;
import gem.SmallGem;
import square.NormalSquare;
import square.Squares;

public class Player {
	public int point;
	public String name;
	private int direction; // 1: counterclockwise, -1: clockwise
	public static List<Integer> range = new ArrayList<Integer>();
	public int pointInHand = 0;
	
	public Player() {
		this.point = 0;
		this.pointInHand = 0;
	}
	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	public boolean stopTurn(Board board, Squares curSquare, int direction) {
		int curPosition = curSquare.getPosition();
		int nextPosition = curPosition + direction;
		if (pointInHand == 0 && board.getPointAtSquare(nextPosition) == 0) {
			return true;
		}
		return false;
	}
	
	public void aPhase(Board board, int position, int direction) {
		SmallGem gem = new SmallGem();
		pointInHand = board.getPointAtSquare(position);
		board.getSquare(position).removeGem();
		int curPosition = position;
		while (pointInHand != 0) {
			curPosition += direction;
			board.getSquare(curPosition).addGem(gem);
			pointInHand -= 1;
		}
	}
	public void turn(Board board, int position, int direction) {
		pointInHand = board.getPointAtSquare(position);
		int curPosition = position;
		int nextPosition = curPosition + direction;
		while (board.getPointAtSquare(nextPosition ) != 0 || pointInHand != 0) {
			
		}
	}
}
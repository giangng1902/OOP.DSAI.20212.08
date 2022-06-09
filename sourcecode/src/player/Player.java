package player;
import java.util.*;

import square.NormalSquare;
import square.Squares;

public class Player {
	protected int point;
	private String name;
	private int direction; // 1: counterclockwise, -1: clockwise
	public static List<Integer> range = new ArrayList<Integer>();
	public int pointInHand = 0;
	
	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	public boolean stopTurn(List<Squares> board, Squares curSquare, int direction) {
		int curPosition = curSquare.getPosition();
		int nextPosition = curPosition + direction;
		if (pointInHand == 0 && board.get(nextPosition).getPoint() == 0) {
			return true;
		}
		return false;
	}
	/*
	public void turn(NormalSquare normalSquare, int direction) {
		pointInHand = normalSquare.getPoint();
		int currentPosition = normalSquare.getPosition(); 
		if (direction == 1) {  // counter clockwise
			while 
		}
	}
	*/
}

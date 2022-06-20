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
	public Player(String name) {
		this.point = 0;
		this.name = name;
		this.pointInHand = 0;
	}
	public int getPoint() {
		return point;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public static List<Integer> getRange() {
		return range;
	}
	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String infor() {
		String infor = getName();
		return infor;
	}
}
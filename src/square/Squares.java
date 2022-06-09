package square;
import java.util.*;

import gem.Gem;
import player.Player;

public class Squares {
	private int point = squarePoint();
	private static int position;
	public static final int MAX_POINTS = 70;
	private List<Gem> square = new ArrayList<Gem>();
	
	public static void setPosition(int position) {
		Squares.position = position;
	}
	public int getPoint() {
		return point;
	}
	public int squarePoint() {
		int sum = 0;
		for (Gem gem : square) {
			sum += gem.point;
		}
		return sum;
	}
	
	public boolean canBeChosen() { // check dieu kien chon o, o co the chon tra ve true
		if (Player.range.contains(Squares.position)) {
			if (point > 0) {
				return true;
			}
		}
		return false;
	}
	
}

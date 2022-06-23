package player;

import java.util.Arrays;
import java.util.List;

public class Player2 extends Player {
	private List<Integer> range = Arrays.asList(new Integer[] { 7, 8, 9, 10, 11 });
	public Player2(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public List<Integer> getRange() {
		return range;
	}
}

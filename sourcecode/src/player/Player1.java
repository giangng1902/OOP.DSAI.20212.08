package player;

import java.util.Arrays;
import java.util.List;

public class Player1 extends Player{
	private List<Integer> range = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 });
	public Player1(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public List<Integer> getRange() {
		return range;
	}
	
}

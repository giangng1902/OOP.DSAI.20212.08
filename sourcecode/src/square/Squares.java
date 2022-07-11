package square;
import java.util.*;
import gem.*;
public abstract class Squares {
	private int position;
	public static final int MAX_POINTS = 60;
	private ArrayList<Gem> square = new ArrayList<Gem>();
	
	public Squares(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		while (position < 0) {
			position += 12;
		}
		return position % 12;
	}

	public int getPoint() {
		int sum = 0;
		for (Gem gem:square) {
			sum += gem.getPoint();
		}
		return sum;
	}
	
	public void addGem(Gem gem) {
		square.add(gem);
	}
	public void removeGem() {
		while (square.size() !=0) {
			square.remove(0);
		}
	}
}
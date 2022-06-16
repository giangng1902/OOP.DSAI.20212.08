package square;
import java.util.*;
import player.Player;
import gem.*;
public abstract class Squares {
	private int point;
	private int position;
	public static final int MAX_POINTS = 60;
	private List<Gem> square = new ArrayList<Gem>();
	
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
		return square.size();
	}
	
	public boolean canBeChosen() { // check dieu kien chon o, o co the chon tra ve true
		if (Player.range.contains(getPosition()) && getPoint() > 0) {
			return true;
		}
		return false;
	}
	
	public void addGem(Gem gem) {
		square.add(gem);
	}
	public void removeGem() {
		
	}
}
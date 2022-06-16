package square;

import java.util.ArrayList;
import java.util.List;

import gem.Gem;
import gem.SmallGem;

public class NormalSquare extends Squares{
	private List<Gem> normalSquare = new ArrayList<Gem>();
	public NormalSquare(int position) {
		// TODO Auto-generated constructor stub
		super(position);
	}
	public void removeGem() {
		for (Gem gem: normalSquare) {
			normalSquare.remove(gem);
		}
	}

}
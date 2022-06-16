package test;

import gem.SmallGem;
import square.NormalSquare;
import square.Squares;

public class SquareTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NormalSquare square = new NormalSquare(-32);
		SmallGem gem = new SmallGem();
		square.addGem(gem);
		square.addGem(gem);
		square.addGem(gem);
		square.addGem(gem);
		square.removeGem();
		System.out.println(square.getPoint());
	}

}

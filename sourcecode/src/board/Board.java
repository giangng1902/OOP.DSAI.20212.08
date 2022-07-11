package board;

import java.util.ArrayList;
import gem.BigGem;
import gem.SmallGem;
import square.HalfCircle;
import square.NormalSquare;
import square.Squares;

public class Board {
	private ArrayList<Squares> board = new ArrayList<Squares>();
	public Board() {
		HalfCircle halfCircle0 = new HalfCircle(0);
		HalfCircle halfCircle6 = new HalfCircle(6);
		NormalSquare normalSquare1 = new NormalSquare(1);
		NormalSquare normalSquare2 = new NormalSquare(2);
		NormalSquare normalSquare3 = new NormalSquare(3);
		NormalSquare normalSquare4 = new NormalSquare(4);
		NormalSquare normalSquare5 = new NormalSquare(5);
		NormalSquare normalSquare7 = new NormalSquare(7);
		NormalSquare normalSquare8 = new NormalSquare(8);
		NormalSquare normalSquare9 = new NormalSquare(9);
		NormalSquare normalSquare10 = new NormalSquare(10);
		NormalSquare normalSquare11 = new NormalSquare(11);
		board.add(halfCircle0);
		board.add(normalSquare1);
		board.add(normalSquare2);
		board.add(normalSquare3);
		board.add(normalSquare4);
		board.add(normalSquare5);
		board.add(halfCircle6);
		board.add(normalSquare7);
		board.add(normalSquare8);
		board.add(normalSquare9);
		board.add(normalSquare10);
		board.add(normalSquare11);
		
		BigGem bigGem = new BigGem();
		board.get(0).addGem(bigGem);
		board.get(6).addGem(bigGem);
		SmallGem smallGem = new SmallGem();
		
		for (int i = 1; i <= 5; i++) {
			normalSquare1.addGem(smallGem);
			normalSquare2.addGem(smallGem);
			normalSquare3.addGem(smallGem);
			normalSquare4.addGem(smallGem);
			normalSquare5.addGem(smallGem);
			normalSquare7.addGem(smallGem);
			normalSquare8.addGem(smallGem);
			normalSquare9.addGem(smallGem);
			normalSquare10.addGem(smallGem);
			normalSquare11.addGem(smallGem);
		}
		
	}
	public Squares getSquare(int position) {
		while (position < 0) {
			position += 12;
		}
		while (position >= 12) {
			position -= 12;
		}
		return board.get(position);
	}

	public void print() {
		System.out.println("===================");
		for (Squares square: board) {
			
			System.out.println("Square " + board.indexOf(square) + ":" + square.getPoint());
		}
	}
}
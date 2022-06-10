package console;
import java.util.List;

import board.Board;
import gem.*;
import square.*;

public class Console {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board();

		System.out.println(board.getPointAtSquare(0));

	}
	public boolean stopGame(List<Squares> board) {
		if (board.get(0).getPoint() == 0 && board.get(6).getPoint() == 0) {
			return true;
		}
		return false;
	}
	
}
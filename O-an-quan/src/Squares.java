public class Squares {
	private int point = point();
	private static int position;
	public static final int MAX_POINTS = 70;
	private Gem[] square = new Gem[MAX_POINTS];
	
	public static void setPosition(int position) {
		Squares.position = position;
	}

	public int point() {
		int cap = 0;
		for (int i = 0; i < MAX_POINTS; i++) {
			if (square[i] != null) {
				cap += 1;
			}
		}
		return cap;
	}
	
	
}

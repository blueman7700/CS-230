package Pathfinding;

public class ManhattenDistance {

	//min cost to move from one tile to another
	private static int MIN_COST = 1;

	public ManhattenDistance() {}

	public int getCost(int sX, int sY, int fX, int fY) {

		return MIN_COST * (Math.abs(sX - fX) + Math.abs(sY - fY));
	}
}


public class Floor extends Tile {
	Item contents;
	
	private static String sprite = "sprites/floor.png";
	
	public Item getContent() {
		return contents;
	}
	
	public void setContent(Item i) {
		contents = i;
	}

	public String getSprite() {
		return sprite;
	}
}

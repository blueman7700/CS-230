
public class Floor extends Tile{
	
	private Item contents;
	public Floor() {
		this.walkable = true;
	}
	public boolean hasContents() {
		return !(this.contents == null);
	}
	public Item getContents() {
		return this.contents;
	}
	
}

/**
 * This class is the Tile superclass, for all items that can be picked up by the user
 * The class is empty but certain Tiles will contain type Item, which all pickups are a
 * subclass of. Making it an easier method of containing an item in a tile.
 *
 * @author Nathan Preston
 * @version 1.0
 */
public class Item {

    //Item name
    protected String name = new String();


    public Item(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
    	return "sprites/token.png";
    }
    
}

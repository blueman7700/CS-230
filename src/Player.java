import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * <b>Name: </b>Player.java
 * <br>
 * <p>
 * Player object.
 * </p>
 * <br><b>Created:</b> 17/11/2019
 * <br><b>Last Modified:</b> 22/11/2019
 * <br> - no copyright
 * <hr>
 *
 * @author Oliver Morris
 * @version 0.1
 * <br>
 * <br><b>History:</b>
 * <br>
 * <ul>
 * 	<li>Version 0.1 (17/11/2019)</li>
 * </ul>
 */

public class Player extends Entity {

    private Manager gm;

    private int numTokens;
    private boolean alive;
    private LinkedList<Item> inventory;

    /**
     * creates a new instance of Player.
     *
     * @param x        x coordinate of the player.
     * @param y        y coordinate of the player.
     */

    public Player(int x, int y, Manager gm) {

        this.xPos = x;
        this.yPos = y;
        this.filePath = "sprites/player.png";
        inventory = new LinkedList<>();
        numTokens = 0;
        alive = true;
        this.gm = gm;
    }

    /**
     * creates a string containing player information
     *
     * @return player as String
     */

    @Override
    public String toString() {

        return String.format("Player is at %d, %d.\nPlayer Inventory:\n%s", getxPos(), getyPos(), inventory.toString());
    }

    /**
     * move the player in the specified direction
     *
     * @param type the type of movement that the player will make
     */

    @Override
    public void move(MoveType type) {

        //TODO: implement all move conditions

        Tile nextTile;
        int newX = 0;
        int newY = 0;

        //get the position of the tile in the direction of movement
        switch (type) {
            case UP:
                newX = this.getxPos();
                newY = this.getyPos() + 1;
                break;
            case DOWN:
                newX = this.getxPos();
                newY = this.getyPos() - 1;
                break;
            case LEFT:
                newX = this.getxPos() - 1;
                newY = this.getyPos();
                break;
            case RIGHT:
                newX = this.getxPos() + 1;
                newY = this.getyPos();
                break;
            default:
                System.out.println("Something Went Wrong! Wrong Move Type!");
                break;
        }

        nextTile = gm.getMap().getTile(newX, newY);

        //check if the next tile is walkable
        if (nextTile.getWalkable()) {
            //check if the tile is an instance of floor
            if (nextTile instanceof Floor) {
                if (((Floor) nextTile).getContents() == null) {
                    addItemToInv(((Floor) nextTile).getContents());
                }
                this.setPosition(newX, newY);

                //check if the tile is an instance of interactable
            } else if (nextTile instanceof Interactable) {

                //interact with the tile
                ((Interactable) nextTile).interact(this);

            }
        }
    }

    /**
     * adds a specified item to the list.
     *
     * @param item item to add to the list.
     */

    public void addItemToInv(Item item) {

        inventory.add(item);
    }

    /**
     * removes a specified item from the list.
     *
     * @param item item to remove from the list.
     * @throws NoSuchElementException thrown if the specified item does not exist.
     */

    public Item removeFromInv(Item item) {

        boolean found = false;
        int index = 0;

        //iterate through inventory looking for specified item
        for (int i = 0; i < inventory.size(); i++) {

            if (inventory.get(i) == item) {

                //if item has been found set found to true and record index
                found = true;
                index = i;
            }
        }

        //check if the item has been found in the list
        if (!found) {

            //if item has not been found throw exception
            return null;
        } else {

            //remove item from inventory
            inventory.remove(index);
            return item;
        }
    }

    /**
     * increment the token counter by 1
     */

    public void addToken() {

        numTokens++;
    }

    /**
     * check if the player is alive
     *
     * @return alive
     */

    public Boolean isAlive() {

        return alive;
    }

}

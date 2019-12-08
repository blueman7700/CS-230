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
    private static String filePath = "sprites/player.png";

    /**
     * creates a new instance of Player.
     *
     * @param x        x coordinate of the player.
     * @param y        y coordinate of the player.
     */
    public Player(int x, int y, Manager gm) {

        this.xPos = x;
        this.yPos = y;
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

        Tile nextTile;
        int newX = 0;
        int newY = 0;
        Boolean hasItem = false;
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

        //get the tile the player will move to
        nextTile = gm.getMap().getTile(newX, newY);

        //check if the game has been won
        if (wonGame(nextTile)) {
            setPosition(newX, newY);
            gm.win();
        //check if the next tile is a fire tile
        }else if (nextTile instanceof Fire) {
            //search inventory for the correct item
            for (Item i : inventory) {
                if (i instanceof FireBoots) {
                    hasItem = true;
                    //no need to continue the loop
                    break;
                } 
            }
            if(hasItem) {
                setPosition(newX, newY);
            } else {
            	gm.lose();
            }
        //check if the next tile is a water tile
        }else if (nextTile instanceof Water) {
            //search inventory for correct item
            for (Item i : inventory) {
                if (i instanceof Flippers) {
                    hasItem = true;
                    //no need to continue the loop
                    break;
                } 
            }
            if(hasItem) {
                setPosition(newX, newY);
            } else {
            	gm.lose();
            }
        //check if the next tile is a door
        }else if (nextTile instanceof TokenDoor || nextTile instanceof KeyDoor){
            //attempt to open the door
            gm.getMap().openDoor(nextTile, inventory, numTokens);
            nextTile = gm.getMap().getTile(newX, newY);

            //if the door has been opened then move through
            if (nextTile instanceof Floor) {
                setPosition(newX, newY);
            }
        //check if the next tile is a teleporter and move the player accordingly
        }else if(nextTile instanceof Teleporter) {
        	switch (type) {
				case UP:
					newX = ((Teleporter)nextTile).getPartner().getxPos();
					newY = ((Teleporter)nextTile).getPartner().getyPos() + 1;
					break;
				case DOWN:
					newX = ((Teleporter)nextTile).getPartner().getxPos();
					newY = ((Teleporter)nextTile).getPartner().getyPos() - 1;
					break;
				case LEFT:
					newX = ((Teleporter)nextTile).getPartner().getxPos() - 1;
					newY = ((Teleporter)nextTile).getPartner().getyPos();
					break;
				case RIGHT:
					newX = ((Teleporter)nextTile).getPartner().getxPos() + 1;
					newY = ((Teleporter)nextTile).getPartner().getyPos();
					break;
			}

			//move the player to the new position
			Tile newTile = gm.getMap().getTile(newX, newY);
        	if (newTile.getWalkable()) {
        		setPosition(newX, newY);
			}

        } else if (nextTile instanceof Floor) {

            //check if the tile is an instance of floor
            if ((nextTile).contains()) {
                //checks to see what the contents is then adds to inventory
                if (((Floor) nextTile).getKey() != null) {
                    addItemToInv(((Floor) nextTile).getKey());
                }
                if (((Floor) nextTile).getToken() != null) {
                    numTokens++;
                }
                if (((Floor) nextTile).getFlippers() != null) {
                    addItemToInv(((Floor) nextTile).getFlippers());
                }
                if (((Floor) nextTile).getFireBoots() != null) {
                    addItemToInv(((Floor) nextTile).getFireBoots());
                }
            }
            this.setPosition(newX, newY);
        }
    }

    /**
     * check if the game has been won.
     *
     * @param tile tile to check.
     * @return true if tile is an instance of Goal, else false.
     */
    private boolean wonGame(Tile tile) {

        return (tile instanceof Goal);
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
    public void removeFromInv(Item item) {

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
        if (found) {
            //if item has been found, remove it from the inventory
            inventory.remove(index);
        }
    }

    /**
     * get the number of tokens the player has.
     * @return number of tokens.
     */
    public int getTokens() {
        return numTokens;
    }
    
    /**
     * sets the number of tokens
     * @param t number of tokens
     */
    public void setTokens(int t) {
    	this.numTokens = t;
    }

    /**
     * check if the player is alive
     *
     * @return alive
     */
    public Boolean isAlive() {

        return alive;
    }

    /**
     * gets the players inventory
     * @return the players inventory
     */
    public LinkedList<Item> getInv(){
    	return this.inventory;
    }
}

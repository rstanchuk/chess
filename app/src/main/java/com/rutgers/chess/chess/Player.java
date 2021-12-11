package chess;

/**
 * Player is a class represents one of the participants playing a chess game.
 * 
 * @author Roman Stanchuk
 * @author Ivan Pomajulca
 */

public class Player {
	public String name;

	/**
	 * constructor
	 * @param name String name of player
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * get the name of the player
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the name of the player
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * toString implementation
	 * @return String
	 */
	public String toString() {
		return name;
	}

}

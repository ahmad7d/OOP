/**
 * The PlayerFactory class is responsible for creating instances of different player types based on input.
 */
public class PlayerFactory {

    /**
     * Builds and returns a player instance based on the specified player type.
     *
     * @param type The type of player to be created.
     * @return A player instance corresponding to the specified type.
     */
    public Player buildPlayer(String type) {
        String playerType = type.toLowerCase();

        if (playerType.equals(Constants.HUMAN_PLAYER)) {
            return new HumanPlayer();
        }
        if (playerType.equals(Constants.WHATEVER_PLAYER)) {
            return new WhateverPlayer();
        }
        if (playerType.equals(Constants.CLEVER_PLAYER)) {
            return new CleverPlayer();
        }
        if (playerType.equals(Constants.GENIUS_PLAYER)) {
            return new GeniusPlayer();
        }
        return null;
    }
}

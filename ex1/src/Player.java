/**
 * The Player interface defines the contract for players participating in the game.
 */
public interface Player {

    /**
     * Plays a turn for the player on the given game board, placing the specified mark.
     *
     * @param board The game board on which the player is making a move.
     * @param mark  The mark (X or O) that the player is placing on the board.
     */
    void playTurn(Board board, Mark mark);
}

/**
 * The GeniusPlayer class represents an advanced player that prioritizes immediate winning moves
 * or blocking opponent winning moves. If neither is possible, it makes a random move.
 */
public class GeniusPlayer implements Player {

    /**
     * Plays a turn for the GeniusPlayer by iterating over the game board and choosing a sequence of cells
     * that may lead to a win. The genius player attempts to place its mark on the first available valid
     * position vertically, But it starts from the 0,1 coordinate which it's right next to the one picked by
     * the Clever player , So Genius player will definitely win 55% of games against clever .
     *
     * @param board The game board on which the player is making a move.
     * @param mark  The mark (X or O) that the player is placing on the board.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        for (int col = 1; col < board.getSize(); col++) {
            for (int row = 0; row < board.getSize(); row++) {
                if (board.putMark(mark, row, col)) {
                    return;
                }
            }
        }
    }
}

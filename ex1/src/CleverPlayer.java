/**
 * The  CleverPlayer class represents a player that attempts to win the game by choosing
 * a sequence of cells on the game board. The player aims to achieve a winning rate of at least 55%.
 * The strategy involves iterating over the board and selecting a sequence of cells like
 * (0 < k < board.Length  board[i][j+k]  board[i][j+k+1]  board[i][j+k+2]) that can lead to a win.
 */
public class CleverPlayer implements Player {

    /**
     * Plays a turn for the CleverPlayer by iterating over the game board and choosing a sequence of cells
     * that may lead to a win. The clever player attempts to place its mark on the first available valid
     * position.
     *
     * @param board The game board on which the player is making a move.
     * @param mark  The mark (X or O) that the player is placing on the board.
     */
    public void playTurn(Board board, Mark mark) {
        for (int row = 0; row < board.getSize(); ++row) {
            for (int col = 0; col < board.getSize(); ++col) {
                if (board.putMark(mark, row, col)) {
                    return;
                }
            }
        }
    }
}

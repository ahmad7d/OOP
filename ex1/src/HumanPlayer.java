/**
 * The HumanPlayer class represents a player controlled by a human. It prompts the user to input
 * coordinates for their move, checks the validity of the input, and plays the chosen move on the game board.
 */
public class HumanPlayer implements Player {
    private static KeyboardInput scanner = null;

    /**
     * Constructs a HumanPlayer and initializes the scanner for keyboard input.
     */
    public HumanPlayer() {
        scanner = KeyboardInput.getObject();
    }

    /**
     * Plays a turn for the HumanPlayer by prompting the user to input coordinates for their move,
     * checking the validity of the input, and placing the mark on the game board.
     *
     * @param board The game board on which the player is making a move.
     * @param mark  The mark (X or O) that the player is placing on the board.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        System.out.println(Constants.playerRequestInputString(mark == Mark.X ? "X" : "O"));

        int playerCoordinatesInput = scanner.readInt();

        int row = playerCoordinatesInput / 10;
        int col = playerCoordinatesInput % 10;

        while (!isValidPlayerCoordinatesInput(board, row, col)) {
            playerCoordinatesInput = scanner.readInt();
            row = playerCoordinatesInput / 10;
            col = playerCoordinatesInput % 10;
        }
        board.putMark(mark, row, col);
    }

    /**
     * Checks if the provided player coordinates input is valid.
     *
     * @param board The game board.
     * @param row   The row index provided by the user input.
     * @param col   The column index provided by the user input.
     * @return True if the input is valid, false otherwise.
     */
    private boolean isValidPlayerCoordinatesInput(Board board, int row, int col) {
        if (!(row >= Constants.ZERO && col >= Constants.ZERO && row < board.getSize() && col <
                board.getSize())) {
            System.out.println(Constants.INVALID_COORDINATE);
            return false;
        }
        if (board.getMark(row, col) != Mark.BLANK) {
            System.out.println(Constants.OCCUPIED_COORDINATE);
            return false;
        }
        return true;
    }
}

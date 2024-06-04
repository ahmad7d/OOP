/**
 * The Board class represents a two-dimensional game board for a simple game.
 * It provides functionality for initializing the board, getting the size of the board,
 * placing marks on the board, retrieving marks from specific positions, and checking
 * the validity of a given position.
 */
public class Board {

    private Mark[][] board;

    /**
     * Initializes a new Board instance with the default size (BOARD_DEFAULT_SIZE).
     */
    public Board() {
        this.board = boardInit(Constants.BOARD_DEFAULT_SIZE);
    }

    /**
     * Initializes a new  Board instance with a specified size.
     *
     * @param num The size of the board.
     */
    public Board(int num) {
        this.board = boardInit(num);
    }

    /**
     * Private method used for initializing the game board with a given size.
     *
     * @param size The size of the board.
     * @return The initialized game board.
     */
    private Mark[][] boardInit(int size) {
        this.board = new Mark[size][size];
        for (int row = 0; row < size; ++row) {
            for (int col = 0; col < size; ++col) {
                this.board[row][col] = Mark.BLANK;
            }
        }
        return this.board;
    }

    /**
     * Retrieves the size of the game board.
     *
     * @return The size of the board.
     */
    public int getSize() {
        return this.board.length;
    }

    /**
     * Places a mark on the game board at the specified position (row, col).
     * Checks if the position is valid and if the cell is empty before placing the mark.
     *
     * @param mark The mark to be placed (Mark.X or Mark.O).
     * @param row  The row index of the position.
     * @param col  The column index of the position.
     * @return True if the mark is successfully placed, false otherwise.
     */
    public boolean putMark(Mark mark, int row, int col) {
        if (!isValidMark(row, col)) {
            return false;
        }
        if (getMark(row, col) != Mark.BLANK) {
            return false;
        }
        this.board[row][col] = mark;
        return true;
    }

    /**
     * Retrieves the mark at the specified position (row, col) on the game board.
     *
     * @param row The row index of the position.
     * @param col The column index of the position.
     * @return The mark at the specified position or Mark. BLANK if the position is invalid.
     */
    public Mark getMark(int row, int col) {
        if (isValidMark(row, col)) {
            return this.board[row][col];
        }
        return Mark.BLANK;
    }

    /**
     * Checks if a given position (row, col) is valid on the game board.
     *
     * @param row The row index of the position.
     * @param col The column index of the position.
     * @return True if the position is valid, false otherwise.
     */
    private boolean isValidMark(int row, int col) {
        return row >= Constants.ZERO && col >= Constants.ZERO && row < this.board.length && col <
                this.board[row].length;
    }
}

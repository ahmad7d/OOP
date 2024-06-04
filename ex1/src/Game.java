/**
 * The Game class represents a game instance where two players (PlayerX and PlayerO) take turns
 * placing their marks on a game board. The game aims to determine a winner based on a specified win streak
 * and checks for victory conditions horizontally, vertically, and diagonally. The game can end when there
 * is a winner or the board is full.
 */


public class Game {
    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private Player currPlayer;
    private final Renderer renderer;
    private final int size;
    private final int winStreak;


    /**
     * Default constructor for the Game class. (Default constructor mean that not getting board size or
     * winStreak, so we set the default values of them)
     *
     * @param playerX  The first player (X) in the game.
     * @param playerO  The second player (O) in the game.
     * @param renderer The renderer used to display the game board.
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {

        this.winStreak = Constants.DEFAULT_WIN_STREAK;
        this.playerX = playerX;
        this.playerO = playerO;
        this.currPlayer = playerX; // based on the game rule : X player is first
        this.board = new Board();
        this.renderer = renderer;
        this.size = Constants.DEFAULT_SIZE;

    }

    /**
     * Constructor for the Game class with custom size, win streak, and renderer.
     *
     * @param playerX   The first player (X) in the game.
     * @param playerO   The second player (O) in the game.
     * @param size      The size of the game board.
     * @param winStreak The number of consecutive marks needed to win.
     * @param renderer  The renderer used to display the game board.
     */
    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.winStreak = winStreak < Constants.MINIMUM_WIN_STREAK_PARAMETER || winStreak > size ? size :
                winStreak;
        this.playerX = playerX;
        this.playerO = playerO;
        this.currPlayer = playerX;
        this.board = new Board(size);
        this.renderer = renderer;
        this.size = size;
    }

    /**
     * Checks if a given position is out of the game board.
     *
     * @param row The row index.
     * @param col The column index.
     * @return True if the position is out of the board, false otherwise.
     */
    private boolean isOutOfBoard(int row, int col, int size) {
        return row >= size || col >= size || row < Constants.ZERO || col < Constants.ZERO;
    }


    /**
     * Checks if the game has reached a win condition by examining horizontal sequences.
     *
     * @param board The game board.
     * @param mark  The mark (X or O) to check for win conditions.
     * @param row   The row index of the last placed mark.
     * @param col   The column index of the last placed mark.
     * @return The length of the winning streak or 0 if there is no winner yet.
     */
    private int checkingHorizontal(Board board, Mark mark, int row, int col) {
        if (isOutOfBoard(row, col + 1, this.getBoardSize())) { // out of board
            return 0;
        }
        int counter = 0;
        for (int j = col; j < this.getBoardSize(); ++j) {
            if (board.getMark(row, j) == mark) {
                counter++;
            } else break;
        }
        return counter == this.getWinStreak() ? counter : 0;
    }


    /**
     * Checks if the game has reached a win condition by examining vertical sequences.
     *
     * @param board The game board.
     * @param mark  The mark (X or O) to check for win conditions.
     * @param row   The row index of the last placed mark.
     * @param col   The column index of the last placed mark.
     * @return The length of the winning streak or 0 if there is no winner yet.
     */
    private int checkingVertical(Board board, Mark mark, int row, int col) {
        if (isOutOfBoard(row + 1, col, this.getBoardSize())) { // out of board
            return 0;
        }
        int counter = 0;
        for (int i = row; i < this.getBoardSize(); ++i) {
            if (board.getMark(i, col) == mark) {
                counter++;
            } else break;
        }
        return counter == this.getWinStreak() ? counter : 0;

    }


    /**
     * Checks if the game has reached a win condition by examining right diagonal sequences.
     *
     * @param board The game board.
     * @param mark  The mark (X or O) to check for win conditions.
     * @param row   The row index of the last placed mark.
     * @param col   The column index of the last placed mark.
     * @return The length of the winning streak or 0 if there is no winner yet.
     */
    private int checkRightDiagonal(Board board, Mark mark, int row, int col) {
        int counter = 0;
        for (int i = row, j = col; i < this.getBoardSize() && j < this.getBoardSize(); ++i, ++j) {
            if (!isOutOfBoard(i, j, this.getBoardSize())){
                if (board.getMark(i ,j) == mark){
                    counter++;
                }
                else break;
            }
        }
        return counter == this.getWinStreak() ? counter : 0;
    }


    /**
     * Checks if the game has reached a win condition by examining left diagonal sequences.
     *
     * @param board The game board.
     * @param mark  The mark (X or O) to check for win conditions.
     * @param row   The row index of the last placed mark.
     * @param col   The column index of the last placed mark.
     * @return The length of the winning streak or 0 if there is no winner yet.
     */
    private int checkLeftDiagonal(Board board, Mark mark, int row, int col) {
        int counter = 0;
        for (int i = row, j = col; i < this.getBoardSize() && j < this.getBoardSize(); ++i, --j) {
            if (!isOutOfBoard(i, j, this.getBoardSize())){
                if (board.getMark(i ,j) == mark){
                    counter++;
                }
                else break;
            }
        }
        return counter == this.getWinStreak() ? counter : 0;
    }


    /**
     * Checks if there is a winning condition on the game board for each coordinate.
     *
     * @param board The game board.
     * @param mark  The mark (X or O) to check for win conditions.
     * @return The winning mark (X or O) or Mark. BLANK if there is no winner yet.
     */
    private Mark isWinCondetition(Board board, Mark mark) {
        for (int row = 0; row < this.getBoardSize(); ++row) {
            for (int col = 0; col < this.getBoardSize(); ++col) {
                if (board.getMark(row, col) == mark){
                    if (checkingHorizontal(board, mark, row, col) >= this.getWinStreak()) {
                        return mark;
                    }
                    if (checkingVertical(board, mark, row, col) >= this.getWinStreak()) {
                        return mark;
                    }
                    if (checkRightDiagonal(board, mark, row, col) >= this.getWinStreak()) {
                        return mark;
                    }
                    if (checkLeftDiagonal(board, mark, row, col) >= this.getWinStreak()) {
                        return mark;
                    }
                }
            }
        }
        return Mark.BLANK;
    }


    /**
     * Checks if all cells on the game board are filled.
     *
     * @param board The game board.
     * @return True if all cells are filled, false otherwise.
     */
    private boolean isFullCells(Board board) {
        for (int row = 0; row < this.getBoardSize(); ++row) {
            for (int col = 0; col < this.getBoardSize(); ++col) {
                if (board.getMark(row, col) == Mark.BLANK) {
                    return false;
                }
            }
        }
        return true;


    }

    /**
     * return the win streak required to win the game.
     *
     * @return The win streak.
     */
    public int getWinStreak() {
        return this.winStreak;
    }


    public int getBoardSize(){
        return this.size;
    }

    /**
     * Runs the game until there is a winner or the board is full.
     *
     * @return The winning mark (X or O) or Mark. BLANK if the game is a draw.
     */
    public Mark run() {

        Mark winnerMark = isWinCondetition(board, this.currPlayer == this.playerX ? Mark.X : Mark.O);

        while (winnerMark == Mark.BLANK && !isFullCells(board)) {
            this.currPlayer.playTurn(this.board, this.currPlayer == this.playerX ? Mark.X : Mark.O);
            this.renderer.renderBoard(board);
            winnerMark = isWinCondetition(board, this.currPlayer == playerX ? Mark.X : Mark.O);
            this.currPlayer = this.currPlayer == this.playerX ? playerO : playerX;
        }
        return winnerMark;
    }

}
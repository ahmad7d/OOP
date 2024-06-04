/**
 * The Renderer interface defines the contract for rendering game boards.
 */
public interface Renderer {

    /**
     * Renders the provided game board.
     *
     * @param board The game board to be rendered.
     */
    void renderBoard(Board board);
}

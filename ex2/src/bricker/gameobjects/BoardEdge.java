package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents an edge of the game board.
 */
public class BoardEdge extends GameObject {

    /**
     * Constructs a new BoardEdge object.
     *
     * @param topLeftCorner The position of the top-left corner of the edge, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    The width and height of the edge in window coordinates.
     * @param renderable    The renderable object for rendering the edge. Can be null, in which case
     *                      the edge will not be rendered.
     */
    public BoardEdge(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }
}

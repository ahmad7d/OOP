import java.util.Random;


/**
 * The WhateverPlayer class represents a player that makes random moves on the game board.
 * It implements the Player interface.
 */
public class WhateverPlayer implements Player {

    private final Random rand;

    /**
     * Constructs a WhateverPlayer with a new instance of the Random class.
     */
    public WhateverPlayer() {
        this.rand = new Random();
    }


    /**
     * Makes a random move on the game board by selecting random coordinates until a valid move is found.
     *
     * @param board The game board where the move is to be made.
     * @param mark  The player's mark (X or O).
     */
    @Override
    public void playTurn(Board board, Mark mark) {

        int randomRow = rand.nextInt(board.getSize());
        int randomColumn = rand.nextInt(board.getSize());

        // keep looping until getting valid random coordinates
        while (!board.putMark(mark, randomRow, randomColumn)) {
            randomRow = rand.nextInt(board.getSize());
            randomColumn = rand.nextInt(board.getSize());
        }
    }
}
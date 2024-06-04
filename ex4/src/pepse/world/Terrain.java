package pepse.world;

import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.ColorSupplier;
import pepse.util.Constants;
import pepse.util.NoiseGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
/**
 * Represents the terrain in the game world.
 */
public class Terrain {

    /**
     * Callback function used to retrieve the ground height at a given x-coordinate.
     */
    public Function<Float , Float> function = this::getGroundHeightAt;

    private final NoiseGenerator noiseGenerator;
    float groundHeightAtX0;
    private final Vector2 windowDimensions;
    private final NoiseGenerator noise;
    private final List<Block> blocksObjects = new ArrayList<>();
    private List<Color> brickColors = new ArrayList<>();
    /**
     * Constructs a new Terrain instance with the specified window dimensions and seed.
     *
     * @param windowDimensions The dimensions of the window.
     * @param seed             The seed for generating terrain.
     */
    public Terrain(Vector2 windowDimensions, int seed){

        this.groundHeightAtX0 = windowDimensions.y() * ( (float) Constants.TWO /
                Constants.ORBIT_ELLIPSE_B_AXIS);
        this.windowDimensions = windowDimensions;
        int x = Constants.ORBIT_ELLIPSE_A_AXIS;
        this.noise = new NoiseGenerator(seed, Constants.ZERO_INIT);
        this.noiseGenerator = new NoiseGenerator(seed, (int)groundHeightAtX0);
        this.brickColors.add(Constants.FIRST_BROWN);
    }

    /**
     * Gets the ground height at the specified x-coordinate.
     *
     * @param x The x-coordinate.
     * @return The ground height at the specified x-coordinate.
     */
    public float getGroundHeightAt(float x) {
        float noise = (float) noiseGenerator.noise(x, Constants.BLOCK_SIZE * Constants.NOISE_FACTOR);
        return groundHeightAtX0 + noise;
    }

    /**
     * Creates blocks in the specified range along the x-axis.
     *
     * @param minX The minimum x-coordinate.
     * @param maxX The maximum x-coordinate.
     * @return A list of created blocks.
     */
    public List<Block> createInRange(int minX, int maxX){
        int newMaxX = maxX / Constants.BLOCK_SIZE;
        newMaxX *= Constants.BLOCK_SIZE;
        for (float i = minX; i<newMaxX; i+=Constants.BLOCK_SIZE) {
            createColumnBlocks(i, (float) (Math.floor(getGroundHeightAt(i) / Constants.BLOCK_SIZE )
                    * Constants.BLOCK_SIZE));
        }
        return this.blocksObjects;
    }
    /**
     * Creates a column of blocks starting at the specified x-coordinate and extending downwards
     * to a certain depth.
     *
     * @param i          The x-coordinate of the column.
     * @param firstDepth The initial depth (y-coordinate) of the first block in the column.
     */
    private void createColumnBlocks(float i, float firstDepth) {

        for (float depth=firstDepth ; depth<this.windowDimensions.y() ; depth+=Constants.BLOCK_SIZE){
            Block block = new Block(new Vector2(i, depth),
                    new RectangleRenderable(ColorSupplier.approximateColor(
                    Constants.FIRST_BROWN)));
            block.setTag(Constants.BLOCK_TAG);
            this.blocksObjects.add(block);
        }

    }
}

package pepse;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.TextRenderable;
import pepse.util.Constants;
import pepse.world.*;
import pepse.world.daynight.Night;
import pepse.world.daynight.Sun;
import pepse.world.daynight.SunHalo;
import pepse.world.trees.Flora;


/**
 * The main game manager for the Pepse game.
 */
public class PepseGameManager extends GameManager {
    // Energy Object
    private Energy energyObject;
    // Terrain Object
    private Terrain terrainObject;

    /**
     * Initializes the Pepse game with all necessary game objects and components.
     *
     * @param imageReader       The image reader for loading images.
     * @param soundReader       The sound reader for loading sounds.
     * @param inputListener     The user input listener for capturing user input.
     * @param windowController  The window controller for managing the game window.
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener,
                               WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);

        initializeBackgroundObjects(windowController);
        initializeTerrainAndEnergy(windowController);
        initializeAvatarAndFlora(windowController, imageReader, inputListener);
    }

    /**
     * Initializes background objects such as the sun, night, and sky.
     *
     * @param windowController The window controller for obtaining window dimensions.
     */
    private void initializeBackgroundObjects(WindowController windowController) {
        // Adding sky
        gameObjects().addGameObject(Sky.create(windowController.getWindowDimensions()));

        // Adding sun
        GameObject sunObject = Sun.create(windowController.getWindowDimensions(), Constants.ROTATE_CYCLE);
        gameObjects().addGameObject(sunObject);

        // Adding sun halo
        GameObject sunHalo = SunHalo.create(sunObject);
        gameObjects().addGameObject(sunHalo);

        // Adding night
        GameObject nightObject = Night.create(windowController.getWindowDimensions(),
                Constants.NIGHT_CYCLE_LENGTH);
        gameObjects().addGameObject(nightObject);
    }

    /**
     * Initializes terrain and energy counter objects.
     *
     * @param windowController The window controller for obtaining window dimensions.
     */
    private void initializeTerrainAndEnergy(WindowController windowController) {
        // Generating terrain
        this.terrainObject = new Terrain(windowController.getWindowDimensions(), Constants.SEED);
        for (Block block : this.terrainObject.createInRange(Constants.ZERO_INIT,
                (int) windowController.getWindowDimensions().x())) {
            gameObjects().addGameObject(block);
        }

        // Adding energy counter
        this.energyObject = new Energy(Constants.ENERGY_LOCATION, Constants.ENERGY_DIMENSIONS,
                new TextRenderable(Constants.ENERGY_MSG));
        gameObjects().addGameObject(this.energyObject);
    }

    /**
     * Initializes the avatar and flora objects.
     *
     * @param windowController The window controller for obtaining window dimensions.
     * @param imageReader The image reader for loading images.
     * @param inputListener The user input listener for capturing user input.
     */
    private void initializeAvatarAndFlora(WindowController windowController, ImageReader imageReader,
                                          UserInputListener inputListener) {
        // Adding avatar
        Avatar avatar = new Avatar(windowController.getWindowDimensions().mult(Constants.FLOAT_RANDOM2),
                inputListener, imageReader, this.energyObject);
        gameObjects().addGameObject(avatar, Layer.DEFAULT);

        // Generating flora (trees, fruits and logs)
        Flora flora = new Flora(this.terrainObject.function, Constants.ONE, gameObjects(), this.energyObject,
                avatar);
        flora.creatInRange(Constants.ZERO_INIT, (int) windowController.getWindowDimensions().x());
    }


    /**
     * Main method to start the Pepse game.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new PepseGameManager().run();
    }

}

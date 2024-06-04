import danogl.util.Vector2;
import bricker.main.CONSTANTS;
import bricker.main.BrickerGameManager;

public class Main {

    public static void main(String[] args) {
        BrickerGameManager ballGame;
//        if (args.length == CONSTANTS.MAX_ARGS_NUMBERS){
//            ballGame = new Game(CONSTANTS.GAME_NAME, new Vector2(CONSTANTS.DEFAULT_WIDTH_RESOLUTION,
//                    CONSTANTS.DEFAULT_HEIGHT_RESOLUTION), args[CONSTANTS.FIRST_ARG] ,
//                    args[CONSTANTS.SECOND_ARG]);
//
//        }
//        else {
//            ballGame = new Game(CONSTANTS.GAME_NAME, new Vector2(CONSTANTS.DEFAULT_WIDTH_RESOLUTION,
//                    CONSTANTS.DEFAULT_HEIGHT_RESOLUTION));
//        }
        ballGame = new BrickerGameManager(CONSTANTS.GAME_NAME, new Vector2(CONSTANTS.DEFAULT_WIDTH_RESOLUTION,
                CONSTANTS.DEFAULT_HEIGHT_RESOLUTION));

        ballGame.run();

    }
}

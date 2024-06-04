/**
 * Contains constant values used throughout the application.
 */
package ascii_art;

import java.awt.*;

/**
 * Constants related to image processing and ASCII art generation in the application.
 */
public class Constants {

    /**
     * min distance brightness.
     */
    public static final int MIN_DISTANCE = 1;
    /**
     * min value brightness.
     */
    public static final double MIN_VALUE = 1.0;

    /**
     * dim of matrix
     */
    public static final double MATRIX_DIM = 16;

    /**
     * color white.
     */
    public static final Color WHITE_COLOR = Color.WHITE;

    /**
     * Initialize zero  
     */
    public static final int INIT_ZERO = 0;

    /**
     * 2 value
     */
    public static final int TWO = 2;
    /**
     * 1 value
     */
    public static final int ONE = 1;
    /**
     * min valid resolution
     */
    public static final int MIN_VALID_RESOLUTION = 1;

    /**
     * zero elements in charsSet
     */
    public static final int EMPTY_CHARSET = 0;

    /**
     * one as one char to add
     */
    public static final int SINGLE_ADD_TOKEN = 1;

    /**
     * three as multiple chars to add (range)
     */
    public static final int RANGE_ADD_TOKEN = 3;

    /**
     * TWO as valid two elements token
     */
    public static final int VALID_TWO_TOKENS = 2;

    /**
     * Split between both begin\end range
     */
    public static final int SPLIT = '-';

    /**
     *  0 as first element
     */
    public static final int FIRST = 0;

    /**
     *  1 as second element
     */
    public static final int SECOND = 1;
    /**
     *  3 as third element
     */
    public static final int THIRD = 2;



    // Image Constants:

    /**
     * ASCII value of the first printable character.
     */
    public static final int FIRST_ASCII_CHAR = 32;

    /**
     * ASCII value of the last printable character.
     */
    public static final int LAST_ASCII_CHAR = 127;

    /**
     * ASCII value of zero printable character.
     */
    public static final int ZERO_ASCII_CHAR = '0';

    /**
     * ASCII value of nine printable character.
     */
    public static final int NINE_ASCII_CHAR = '9';

    /**
     * Default resolution for image processing.
     */
    public static final int DEFAULT_RESOLUTION = 128;

    /**
     * Default size of the character set used for ASCII art.
     */
    public static final int DEFAULT_CHARSET_SIZE = 10;

    /**
     * Default path for loading images.
     */
    public static final String DEFAULT_IMAGE_PATH = "cat.jpeg";

    /**
     * First line prefix in the shell interface.
     */
    public static final String FIRST_LINE = ">>>";

    /**
     * Command to exit the shell interface.
     */
    public static final String EXIT = "exit";

    /**
     * Command to display the character set.
     */
    public static final String CHARS = "chars";

    /**
     * Command to set the output mode.
     */
    public static final String OUTPUT_MODE = "output";

    /**
     * Error message for an invalid output mode format.
     */
    public static final String INVALID_OUTPUT_MODE_MSG =
            "Did not change output method due to incorrect format.";

    /**
     * Output mode for displaying ASCII art in the console.
     */
    public static final String CONSOLE_MODE = "console";

    /**
     * Output mode for displaying ASCII art in HTML format.
     */
    public static final String HTML_MODE = "html";

    /**
     * Default filename for the output file in HTML mode.
     */
    public static final String OUTPUT_FILENAME = "out.html";

    /**
     * Default font mode for the output file in HTML mode.
     */
    public static final String OUTPUT_FILE_FONT_MODE = "Courier New";

    /**
     * Command to add characters to the character set.
     */
    public static final String ADD = "add";

    /**
     * Flag indicating an add operation for character manipulation.
     */
    public static final boolean IS_ADD_OPERATION = true;

    /**
     * Flag indicating a remove operation for character manipulation.
     */
    public static final boolean IS_REMOVE_OPERATION = false;

    /**
     * Command to remove characters from the character set.
     */
    public static final String REMOVE = "remove";

    /**
     * Command to increase resolution.
     */
    public static final String RESOLUTION_UP = "up";

    /**
     * Command to adjust resolution.
     */
    public static final String RES_COMMAND = "res";

    /**
     * Command to decrease resolution.
     */
    public static final String RESOLUTION_DOWN = "down";


    /**
     * Command to add a space character to the character set.
     */
    public static final String SPACE = "space";

    /**
     * Default space character.
     */
    public static final char SPACE_CHAR = ' ';
    /**
     * single dot
     */
    public static final char DOT = '.';

    /**
     * Default space string.
     */
    public static final String SPACE_STRING = " ";

    /**
     * Command to manipulate all characters in the character set.
     */
    public static final String ALL = "all";

    /**
     * Command to run the ASCII art algorithm.
     */
    public static final String RUN_ALGORITHM = "asciiArt";

    /**
     * Error message for an incorrect format when adding characters to the character set.
     */
    public static final String ADD_ERROR_MESSAGE = "Did not add due to incorrect format.";

    /**
     * Error message for an incorrect format when removing characters from the character set.
     */
    public static final String REMOVE_ERROR_MESSAGE = "Did not remove due to incorrect format.";

    /**
     * Error message for an incorrect resolution format.
     */
    public static final String RESOLUTION_ERROR_MESSAGE_FORMAT = "Did not change resolution due to " +
            "incorrect format.";

    /**
     * Error message for exceeding resolution boundaries.
     */
    public static final String RESOLUTION_ERROR_MESSAGE_MAX_MIN = "Did not change resolution " +
            "due to exceeding boundaries.";

    /**
     * Message indicating the successful change of resolution.
     */
    public static final String RESOLUTION_NEW_RATE_MESSAGE = "Resolution set to ";

    /**
     * Error message for a problem with the image file.
     */
    public static final String IMAGE_ERROR_MESSAGE = "Did not execute due to problem with image file.";

    /**
     * Error message for an empty character set.
     */
    public static final String EMPTY_CHARSET_ERROR_MSG = "Did not execute. Charset is empty.";

    /**
     * Error message for an incorrect command.
     */
    public static final String INVALID_COMMAND = "Did not execute due to incorrect command.";

    /**
     * Command to specify an image.
     */
    public static final String IMAGE = "image";

    /**
     * Newline character.
     */
    public static final String NEW_LINE = "\n";

    // Images colors :

    /**
     * Red factor
     */
    public static final double RED_FACTOR = 0.2126;

    /**
     * Green factor
     */
    public static final double GREEN_FACTOR = 0.7152;

    /**
     * Blue factor
     */
    public static final double BLUE_FACTOR = 0.0722;

    /**
     * RGB factor
     */
    public static final double RGB_FACTOR = 255;

}

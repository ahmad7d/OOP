package ascii_art;


import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;
import image.Resize;
import image_char_matching.SubImgCharMatcher;

import java.awt.*;
import java.io.IOException;


/**
 * The Shell class represents a user interface for interacting with ASCII art.
 * It provides functionality to manipulate images and output ASCII art.
 */
public class Shell implements User {

    private final SubImgCharMatcher subImgCharMatcher;
    private AsciiOutput outputMode;
    private Image defaultImage = new Image(Constants.DEFAULT_IMAGE_PATH);
    private Image image = getNewImage();


    private final char[] defaultCharSet = new char[Constants.DEFAULT_CHARSET_SIZE];
    private int imageResolution;


    /**
     * Constructs a new Shell instance with default settings.
     * Initializes the character set, image resolution, output mode, and character matcher.
     *
     * @throws IOException if an I/O error occurs while initializing the character set.
     */
    public Shell() throws IOException{
        initializeCharSet();
        this.imageResolution = Constants.DEFAULT_RESOLUTION;
        this.outputMode = new ConsoleAsciiOutput();
        this.subImgCharMatcher = new SubImgCharMatcher(this.defaultCharSet);
    }


    /**
     * Initializes the default character set with numeric characters.
     * The character set includes characters from '0' to '9'.
     */
    private void initializeCharSet(){
        int index = Constants.INIT_ZERO;
        for (char c=Constants.ZERO_ASCII_CHAR ; c<=Constants.NINE_ASCII_CHAR ; ++c){
            this.defaultCharSet[index++] = c;
        }
    }



    /**
     * Manipulates the character set based on the input tokens and operation.
     *
     * @param tokens        The input tokens to manipulate the character set.
     * @param isAddOperation   A boolean indicating whether the operation is addition or removal.
     */
    private void manipulateCharacterSet(String tokens, boolean isAddOperation){
        switch (tokens){
            case Constants.ALL:{
                if (isAddOperation){
                    for (int character=Constants.FIRST_ASCII_CHAR ;
                         character<Constants.LAST_ASCII_CHAR ; ++character){
                        subImgCharMatcher.addChar((char)character);
                    }
                }
                else this.subImgCharMatcher.removeAllChars(); // Remove all characters
                break;
            }
            case Constants.SPACE:
                if (isAddOperation){
                    subImgCharMatcher.addChar(Constants.SPACE_CHAR);
                }
                else subImgCharMatcher.removeChar(Constants.SPACE_CHAR);
                break;
            default:
                // handle single char to add
                if (tokens.length() == Constants.SINGLE_ADD_TOKEN) {
                    Character requiredCharacter = tokens.charAt(Constants.FIRST);
                    if (isAddOperation) {
                            subImgCharMatcher.addChar(requiredCharacter);
                    }
                    else subImgCharMatcher.removeChar(requiredCharacter);
                }
                // handle more than one char to add
                else if (tokens.length() == Constants.RANGE_ADD_TOKEN &&
                        tokens.charAt(Constants.SECOND) == Constants.SPLIT) {
                    char startChar = tokens.charAt(Constants.FIRST);
                    char endChar = tokens.charAt(Constants.THIRD);
                    int start = Math.min(startChar, endChar);
                    int end = Math.max(startChar, endChar);
                    for (int character = start; character <= end; character++) {
                        if (isAddOperation) {
                            subImgCharMatcher.addChar((char) character);
                        } else subImgCharMatcher.removeChar((char) character);
                    }
                }
                else System.out.println(isAddOperation ? Constants.ADD_ERROR_MESSAGE :
                            Constants.REMOVE_ERROR_MESSAGE);
                break;
        }
    }


    /**
     * The main method that runs the shell functionality.
     * Reads user input, processes commands, and performs corresponding actions.
     */
    public void run()  {
        while (true){
            System.out.print(Constants.FIRST_LINE + Constants.SPACE_STRING);
            String input = KeyboardInput.readLine();
            String[] tokens = input.split(String.valueOf(Constants.SPACE_CHAR));
            switch (tokens[Constants.FIRST]){
                case Constants.EXIT:
                    return;
                case Constants.CHARS:{
                    this.subImgCharMatcher.printAllChars();
                    break;
                }
                case Constants.ADD:{
                    if (tokens.length == Constants.VALID_TWO_TOKENS){
                        this.manipulateCharacterSet(tokens[Constants.SECOND], Constants.IS_ADD_OPERATION);
                    }
                    else System.out.println(Constants.ADD_ERROR_MESSAGE);
                    break;
                }
                case Constants.REMOVE:{
                    if (tokens.length == Constants.VALID_TWO_TOKENS) {
                        this.manipulateCharacterSet(tokens[Constants.SECOND], Constants.IS_REMOVE_OPERATION);
                    }
                    else System.out.println(Constants.REMOVE_ERROR_MESSAGE);
                    break;
                }
                case Constants.RES_COMMAND:
                    if (isValidResolutionAdjustmentCommand(tokens)) {
                        this.adjustResolution(tokens[Constants.SECOND]);
                    }
                    else System.out.println(Constants.RESOLUTION_ERROR_MESSAGE_FORMAT);
                    break;
                case Constants.IMAGE:
                    // adding try method to catch Exception if accrued in Image constructor .
                    try {this.tryAddImage(input);} catch (IOException e) {
                        System.out.println(Constants.IMAGE_ERROR_MESSAGE);
                    }
                    break;
                case Constants.OUTPUT_MODE:
                    if (isValidOutputCommand(tokens)){
                        this.setOutputMode(tokens[Constants.SECOND]);
                    }
                    else System.out.println(Constants.INVALID_OUTPUT_MODE_MSG);
                    break;
                case Constants.RUN_ALGORITHM:
                    if (this.subImgCharMatcher.getCharSetSize() <= Constants.EMPTY_CHARSET){
                        System.out.println(Constants.EMPTY_CHARSET_ERROR_MSG);
                    }
                    else runAsciiArtAlgorithm();
                    break;
                default:
                    System.out.println(Constants.INVALID_COMMAND);
                    break;
            }
        }
    }


    /**
     * Checks if the provided tokens represent a valid output mode command.
     *
     * @param tokens The tokens parsed from the user input.
     * @return True if the tokens represent a valid output mode command, false otherwise.
     */
    private boolean isValidOutputCommand(String[] tokens) {
        return tokens.length == Constants.VALID_TWO_TOKENS &&
                (tokens[Constants.SECOND].equals(Constants.CONSOLE_MODE) ||
                        tokens[Constants.SECOND].equals(Constants.HTML_MODE));
    }

    /**
     * Checks if the provided tokens represent a valid resolution adjustment command.
     *
     * @param tokens The tokens parsed from the user input.
     * @return True if the tokens represent a valid resolution adjustment command, false otherwise.
     */
    private boolean isValidResolutionAdjustmentCommand(String[] tokens) {
        return tokens.length == Constants.VALID_TWO_TOKENS &&
                (tokens[Constants.SECOND].equals(Constants.RESOLUTION_UP) ||
                        tokens[Constants.SECOND].equals(Constants.RESOLUTION_DOWN));
    }


    /**
     * Tries to add a new image based on the user input.
     * Extracts the file name from the input, creates a new Image object,
     * and updates the default image and current image accordingly.
     * Adjusts the image resolution if it exceeds the width of the new image.
     *
     * @param input The user input containing the image file name.
     * @throws IOException If an I/O error occurs while reading the image file.
     */
    private void tryAddImage(String input) throws IOException {
        String fileName = input.substring(Constants.IMAGE.length()).trim();
        this.defaultImage = new Image(fileName);
        this.image = this.getNewImage();
        if (this.imageResolution > this.image.getWidth()){
            this.imageResolution = this.image.getWidth();
        }
    }

    /**
     * Runs the ASCII art algorithm to convert the current image to ASCII representation.
     * Creates an instance of AsciiArtAlgorithm using the current image, image resolution,
     * and default character set.
     * Calls the run() method of AsciiArtAlgorithm to generate the ASCII representation of the image.
     * Outputs the result image using the selected output mode.
     */
    private void runAsciiArtAlgorithm() {
        AsciiArtAlgorithm asciiArtAlgorithm =
                new AsciiArtAlgorithm(this.image, this.imageResolution,
                        this.subImgCharMatcher.getDefaultCharSet());
        char[][] resultImage = asciiArtAlgorithm.run();
        this.outputMode.out(resultImage);
    }


    /**
     * Sets the output mode for displaying ASCII art.
     * If the output mode is set to console mode, it creates a new instance of ConsoleAsciiOutput.
     * If the output mode is set to HTML mode, it creates a new instance of HtmlAsciiOutput
     * with the specified output filename and font mode.
     *
     * @param outputMode The desired output mode, either console mode or HTML mode.
     */
    private void setOutputMode(String outputMode) {
        switch (outputMode){
            case Constants.CONSOLE_MODE:
                this.outputMode = new ConsoleAsciiOutput();
                break;

            case Constants.HTML_MODE:
                this.outputMode = new HtmlAsciiOutput(Constants.OUTPUT_FILENAME,
                        Constants.OUTPUT_FILE_FONT_MODE);
                break;
        }
    }


    /**
     * Adjusts the resolution of the image based on the specified token.
     * If the token is "RESOLUTION_UP", it increases the image resolution by a factor of 2,
     * provided that the new resolution does not exceed the image width.
     * If the token is "RESOLUTION_DOWN", it decreases the image resolution by a factor of 2,
     * ensuring that the new resolution is not less than the minimum valid resolution.
     *
     * @param token The token indicating whether to adjust the resolution up or down.
     */
    private void adjustResolution(String token) {
            switch (token){

                case Constants.RESOLUTION_UP:
                    if (this.imageResolution * Constants.TWO <= this.image.getWidth()){
                        this.imageResolution *= Constants.TWO;
                        System.out.println(Constants.RESOLUTION_NEW_RATE_MESSAGE +
                                this.imageResolution + Constants.DOT);
                    }
                    else {
                        System.out.println(Constants.RESOLUTION_ERROR_MESSAGE_MAX_MIN);
                    }
                    break;

                case Constants.RESOLUTION_DOWN:
                    if (this.imageResolution > Math.max(Constants.MIN_VALID_RESOLUTION,
                            image.getWidth()/image.getHeight())){
                        this.imageResolution /= Constants.TWO;
                        System.out.println(Constants.RESOLUTION_NEW_RATE_MESSAGE +
                                this.imageResolution + Constants.DOT);
                    }
                    else {
                        System.out.println(Constants.RESOLUTION_ERROR_MESSAGE_MAX_MIN);
                    }
                    break;
        }
    }


    /**
     * Generates a new resized image based on the default image dimensions.
     *
     * @return The new resized image.
     */
    private Image getNewImage() {
        // Resize the default image to match its height and width
        Resize resizedImage = new Resize(this.defaultImage, this.defaultImage.getHeight(),
                this.defaultImage.getWidth());
        // Get the resized color pixel array
        Color[][] newArrayPixels= resizedImage.resize_image();

        // Create and return a new Image instance with the resized pixel array
        return new Image(newArrayPixels, resizedImage.getNewHeight(), resizedImage.getNewWidth());
    }


    /**
     * The entry point of the program.
     *
     * @param args The command-line arguments.
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        // Create a new instance of the Shell class
        Shell shell = new Shell();
        // Run the shell interface
        shell.run();
    }

}

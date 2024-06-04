package image;

import ascii_art.Constants;

import java.awt.*;
/**
 * The Resize class is responsible for resizing an image to a specified height and width.
 * It ensures that the new dimensions are powers of 2 for optimization purposes.
 */

public class Resize {
    // The image to be resized.
    private final Image image;
    // Original dimensions of the image.
    private final int defaultHeight;
    private final int defaultWidth;
    // Resized dimensions of the image.
    private final int newHeight;
    private final int newWidth;
    /**
     * Constructor for the Resize class.
     *
     * @param image   The Image object to be resized.
     * @param height  The desired height of the resized image.
     * @param width   The desired width of the resized image.
     */
    public Resize(Image image, int height, int width){
        this.defaultHeight = height;
        this.defaultWidth = width;
        this.newHeight = getNextPowerOfTwo(height);
        this.newWidth = getNextPowerOfTwo(width);
        this.image = image;
    }
    /**
     * Resizes the image to the specified height and width, ensuring that the new dimensions
     * are powers of 2. The resized image is stored in a 2D array of Color objects.
     *
     * @return The 2D array of Color objects representing the resized image.
     */
    public Color[][] resize_image()  {
        int diffHeight = (this.newHeight - this.defaultHeight)/ Constants.TWO;
        int diffWidth = (this.newWidth - this.defaultWidth)/ Constants.TWO;

        // pixels of image
        Color[][] pixels = new Color[this.newHeight][this.newWidth];

        for (int row = Constants.INIT_ZERO; row < this.newHeight; row++) {
            for (int col = Constants.INIT_ZERO; col < this.newWidth; col++) {

                if(row < diffHeight || col < diffWidth || row >= (this.defaultHeight + diffHeight)
                        || col >= (this.defaultWidth + diffWidth)){
                    pixels[row][col] = Constants.WHITE_COLOR;
                }
                else {
                    pixels[row][col] = image.getPixel(row - diffHeight, col - diffWidth);
                }
            }
        }
        return pixels;

    }
    /**
     * Checks if a given number is a power of 2 and returns the next power of 2 if it is not.
     *
     * @param n The input number.
     * @return The next power of 2.
     */
    private static int getNextPowerOfTwo(int n){
        int new_n;
        new_n = (int) Math.pow(Constants.TWO, Math.ceil(Math.log(n) / Math.log(Constants.TWO)));
        return new_n;
    }

    /**
     * Gets the new height of the resized image.
     *
     * @return The new height.
     */
    public int getNewHeight(){
        return this.newHeight;
    }
    /**
     * Gets the new width of the resized image.
     *
     * @return The new width.
     */
    public int getNewWidth(){
        return this.newWidth;
    }

}

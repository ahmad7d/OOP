package image;

import ascii_art.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A package-private class of the package image.
 * @author Dan Nirel
 */
public class Image {

    private final Color[][] pixelArray;
    private final int width;
    private final int height;
    /**
     * Constructs an Image object by loading an image from the specified file.
     *
     * @param filename The path to the image file.
     * @throws IOException If an error occurs during image loading.
     */

    public Image(String filename) throws IOException {
        BufferedImage im = ImageIO.read(new File(filename));
        width = im.getWidth();
        height = im.getHeight();


        pixelArray = new Color[height][width];
        for (int i = Constants.INIT_ZERO; i < height; i++) {
            for (int j = Constants.INIT_ZERO; j < width; j++) {
                pixelArray[i][j]=new Color(im.getRGB(j, i));
            }
        }
    }
    /**
     * Constructs an Image object with a given pixel array, width, and height.
     *
     * @param pixelArray The array representing the pixels of the image.
     * @param width      The width of the image.
     * @param height     The height of the image.
     */
    public Image(Color[][] pixelArray, int width, int height) {
        this.pixelArray = pixelArray;
        this.width = width;
        this.height = height;
    }
    /**
     * Retrieves the width of the image.
     *
     * @return The width of the image.
     */
    public int getWidth() {
        return width;
    }
    /**
     * Retrieves the height of the image.
     *
     * @return The height of the image.
     */
    public int getHeight() {
        return height;
    }
    /**
     * Retrieves the color of a specific pixel in the image.
     *
     * @param x The x-coordinate of the pixel.
     * @param y The y-coordinate of the pixel.
     * @return The Color object representing the pixel color.
     */
    public Color getPixel(int x, int y) {
        return pixelArray[x][y];
    }
    /**
     * Saves the image to a file with the specified filename.
     *
     * @param fileName The desired filename (excluding extension) for the saved image.
     */
    public void saveImage(String fileName){
        // Initialize BufferedImage, assuming Color[][] is already properly populated.
        BufferedImage bufferedImage = new BufferedImage(pixelArray[0].length, pixelArray.length,
                BufferedImage.TYPE_INT_RGB);
        // Set each pixel of the BufferedImage to the color from the Color[][].
        for (int x = Constants.INIT_ZERO; x < pixelArray.length; x++) {
            for (int y = Constants.INIT_ZERO; y < pixelArray[x].length; y++) {
                bufferedImage.setRGB(y, x, pixelArray[x][y].getRGB());
            }
        }
        File outputfile = new File(fileName+".jpeg");
        try {
            ImageIO.write(bufferedImage, "jpeg", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

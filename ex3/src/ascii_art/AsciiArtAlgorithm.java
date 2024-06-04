package ascii_art;

import image.GreyPixel;
import image.Image;
import image.SubImages;
import image_char_matching.SubImgCharMatcher;
import java.awt.*;
import java.util.*;

/**
 * Represents an algorithm for generating ASCII art from an image.
 */
public class AsciiArtAlgorithm {
    private final Image image;
    private final int resolution;
    private final SubImages sub_images;
    private final SubImgCharMatcher subImgCharMatcher;

    private final GreyPixel greyPixel;


    /**
     * Constructs an AsciiArtAlgorithm object with the specified image, resolution, and character set.
     *
     * @param image     The image to generate ASCII art from.
     * @param resolution The resolution to use for the ASCII art.
     * @param charset   The character set to use for mapping brightness to characters.
     */
    public AsciiArtAlgorithm(Image image, int resolution, char[] charset) {
        this.image = image;
        this.resolution = resolution;
        this.subImgCharMatcher = new SubImgCharMatcher(charset);
        this.sub_images = new SubImages(this.image, resolution);
        this.greyPixel = new GreyPixel();
    }


    /**
     * Runs the ASCII art algorithm to convert the image into ASCII characters.
     * It partitions the image into sub-images, calculates the average brightness
     * for each sub-image, and maps it to a corresponding ASCII character.
     * The resulting ASCII art is stored in a 2D array.
     *
     * @return A 2D array representing the ASCII art of the image.
     */
    public char[][] run() {
        int width = image.getWidth();
        int height = image.getHeight();
        int newWidth = height / resolution;

        // Initialize the final image array
        char[][] finalImage = new char[width / newWidth][resolution];

        // Partition the image into sub-images
        LinkedHashSet<Color[][]> subImages = sub_images.partition();

        // Initialize row and column indices
        int row = Constants.INIT_ZERO;
        int col = Constants.INIT_ZERO;


        // Iterate through each sub-image and map brightness to ASCII characters
        while (row < width / newWidth && col < resolution) {
            for (Color[][] subImage : subImages) {
                // Check if the limits are reached
                if (row == width / newWidth || col == resolution) {
                    break;
                }
                // Create a new image from the sub-image
                Image newSubImage = new Image(subImage, newWidth, newWidth);
                // Calculate the average brightness of the sub-image
                double brightness = greyPixel.Avg_Grey(newSubImage);
                // Map the brightness to an ASCII character
                char subImageChar = subImgCharMatcher.getCharByImageBrightness(brightness);
                // Store the ASCII character in the final image array
                finalImage[row][col] = subImageChar;
                col++;
                // Update row and column indices
                if (col == resolution) {
                    col = Constants.INIT_ZERO;
                    row++;
                }
            }
        }
        return finalImage;
    }
}


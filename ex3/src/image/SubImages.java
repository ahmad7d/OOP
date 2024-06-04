package image;

import ascii_art.Constants;

import java.awt.*;
import java.util.LinkedHashSet;
/**
 * The SubImages class is responsible for dividing an image into smaller sub-images based on a specified
 * resolution.
 * Each sub-image is represented as a 2D array of Color objects, capturing the pixel information within
 * the specified
 * resolution. The partitioning process is achieved by iterating over the rows and columns of the original
 * image and
 * extracting color information for each sub-image. The resulting set of sub-images is stored in a
 * LinkedHashSet for
 * efficient storage and retrieval.
 */
public class SubImages {
    // The original image to be partitioned.
    private final Image image;
    // The resolution for each sub-image.
    private final int imageResolution;
    /**
     * Constructor for the SubImages class.
     *
     * @param image          The Image object to be partitioned.
     * @param resolution     The desired resolution for each sub-image.
     */
    public SubImages(Image image,int resolution){
        this.image = image;
        this.imageResolution = resolution;
    }
    /**
     * Partitions the original image into smaller sub-images based on the specified resolution.
     *
     * @return A LinkedHashSet containing 2D arrays of Color objects representing the sub-images.
     */
    public LinkedHashSet<Color[][]> partition(){
        int width = this.image.getWidth();
        int height = this.image.getHeight();

        // Calculate the length of each row in the sub-images.
        int row_length = (height/ this.imageResolution);
        // Initialize a LinkedHashSet to store the resulting sub-images.
        LinkedHashSet<Color[][]> subImages = new LinkedHashSet<>();

        // Iterate over the original image to extract sub-images.
        for (int i = Constants.INIT_ZERO; i < width; i+=row_length) {
            for (int j = Constants.INIT_ZERO; j < height; j+=row_length) {
                // Create a 2D array to represent the current sub-image.
                Color[][] subImage = new Color[row_length][row_length];
                // Populate the sub-image with color information from the original image.
                for (int k = Constants.INIT_ZERO; k < row_length; k++) {
                    for (int l = Constants.INIT_ZERO; l < row_length; l++) {
                        subImage[k][l]=this.image.getPixel(k+i,l+j);
                    }
                }
                // Add the sub-image to the LinkedHashSet.
                subImages.add(subImage);
            }
        }
        // Return the set of partitioned sub-images.
        return subImages;
    }

}

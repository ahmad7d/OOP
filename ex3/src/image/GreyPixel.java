package image;

import ascii_art.Constants;

import java.awt.*;
/**
 * The GreyPixel class specializes in computing the average grayscale value of an image.
 * It utilizes weighted factors for accurate representation.
 */

public class GreyPixel {
    /**
     * Default constructor for the GreyPixel class.
     */
    public GreyPixel(){
    }
    /**
     * Calculates the average grayscale value of the given image using weighted factors.
     *
     * @param sumImage The Image object for which the average grayscale is to be calculated.
     * @return The average grayscale value.
     */
    public double Avg_Grey(Image sumImage){

        double greyPixel ;
        int width = sumImage.getWidth();
        int height = sumImage.getHeight();
        double sumGreys = Constants.INIT_ZERO;

        for (int i = Constants.INIT_ZERO; i < width; i++) {
            for (int j = Constants.INIT_ZERO; j < height; j++) {
                Color indexColor = sumImage.getPixel(i,j);

                greyPixel = indexColor.getRed() * Constants.RED_FACTOR + indexColor .getGreen() *
                        Constants.GREEN_FACTOR + indexColor.getBlue() * Constants.BLUE_FACTOR;

                sumGreys += greyPixel;
            }
        }

        return (sumGreys/(width * height)) / Constants.RGB_FACTOR;
    }
}
 
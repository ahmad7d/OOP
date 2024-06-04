package ascii_output;

/**
 * Output a 2D array of chars to the console.
 * @author Dan Nirel
 */public class ConsoleAsciiOutput implements AsciiOutput{

    /**
     * Outputs a 2D array of characters to the console.
     * Each row of the array is printed on a separate line.
     * Characters in each row are separated by spaces.
     *
     * @param chars The 2D array of characters to be output.
     *              Each row represents a line, and each element in the row represents a character.
     */
    @Override
    public void out(char[][] chars) {
        for (int y = 0; y < chars.length ; y++) {
            for (int x = 0; x < chars[y].length; x++) {
                System.out.print(chars[y][x] + " ");
            }
            System.out.println();
        }
    }
}

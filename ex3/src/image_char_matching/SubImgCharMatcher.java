/**
 * The SubImgCharMatcher class efficiently matches characters based on brightness within a predefined set.
 * It utilizes CharConverter for binary analysis. The core method, getCharByImageBrightness, iterates through
 * the charset, calculating and normalizing brightness values to find the character with the closest
 * brightness
 * to a target. Dynamic character set changes are supported via addChar and removeChar methods. Crucial
 * normalization is facilitated by methods like getMax and getMin, ensuring adaptability in handling
 * dynamic changes.
 * The adjustCharMap method normalizes the brightness values in the char_map after changes. In essence,
 * SubImgCharMatcher
 * provides a streamlined, adaptable solution for brightness-driven character matching with a focus on
 * efficiency in
 * matrix conversions and brightness normalization.
 */
package image_char_matching;

import ascii_art.Constants;

import java.util.*;



/**
 * SubImgCharMatcher class extends CharConverter and provides functionality for character matching
 * based on image brightness.
 */
public class SubImgCharMatcher extends CharConverter{

    private final Set<Character> charSet;
    private final HashMap<Character, Double> charMap;

    /**
     * Constructor for SubImgCharMatcher.
     *
     * @param charSet The initial character set.
     */
    public SubImgCharMatcher(char[] charSet){
        this.charSet = new HashSet<>();
        this.charMap = new HashMap<>();
        for (char c : charSet){
            this.charSet.add(c);
            boolean[][] matrix = convertToBoolArray(c);
            double currBrightness = brightness_of_char(matrix);
            this.charMap.put(c, currBrightness);
        }
        this.adjustCharMap();



    }
    /**
     * Retrieves the character from the character set that best matches the specified image brightness.
     *
     * @param brightness The brightness value of the image.
     * @return The character from the character set that best matches the specified brightness.
     */
    public char getCharByImageBrightness(double brightness){
        return calculate_close_brightness(brightness);
    }



    /**
     * Adjusts the brightness values in char_map after changes to the character set.
     */
    public void adjustCharMap() {
        double max_brightness = get_Max(charMap);
        double min_brightness = get_Min(charMap);

        double curr_brightness;
        for(Character key : this.charMap.keySet()){
            curr_brightness = this.charMap.get(key);
            curr_brightness = (curr_brightness - min_brightness) / (max_brightness - min_brightness);
            this.charMap.put(key,curr_brightness);
        }
    }

    /**
     * Adds a character to the character set and updates its brightness value in char_map.
     *
     * @param c The character to be added.
     */
    public void addChar(char c){
        if (!charSet.contains(c) && !charMap.containsKey(c)){
            charSet.add(c);
            boolean[][] matrix = convertToBoolArray(c);
            double brightness = brightness_of_char(matrix);
            charMap.put(c, brightness);
        }
    }
    /**
     * Removes a character from the character set and char_map.
     *
     * @param c The character to be removed.
     */

    public void removeChar (char c){
        charSet.remove(c);
        charMap.remove(c);

    }


    /**
     * Removes all characters from the character set and char_map.
     */
    public void removeAllChars() {
        Iterator<Character> iterator = this.charSet.iterator();
        while (iterator.hasNext()) {
            Character c = iterator.next();
            iterator.remove(); // Remove the current element from the iterator
            charMap.remove(c); // Remove the corresponding entry from char_map
        }
    }
    /**
     * Gets the default character set as an array.
     *
     * @return The default character set.
     */
    public char[] getDefaultCharSet() {

        char[] defaultCharSet = new char[this.getCharSetSize()];

        int index = Constants.INIT_ZERO;
        for (char c : this.charSet){
            defaultCharSet[index++] = c;
        }
        return defaultCharSet;
    }
    /**
     * Gets the size of the character set.
     *
     * @return The size of the character set.
     */

    public int getCharSetSize(){
        return this.charSet.size();
    }
    /**
     * Prints all characters in the character set.
     */
    public void printAllChars(){
        for (char c : this.charSet){
            System.out.print(c + Constants.SPACE_STRING);
        }
        System.out.print(Constants.NEW_LINE);
    }
    /**
     * Calculates and returns the character with the closest brightness to the target brightness.
     *
     * @param brightness The target brightness.
     * @return The character with the closest brightness.
     */

    private char calculate_close_brightness(double brightness) {
        double minDistance = Constants.MIN_DISTANCE;
        Character minKey = null;

        for (Character key : this.charMap.keySet()) {
            double currDistance = Math.abs(charMap.get(key) - brightness);

            if (currDistance <= minDistance) {
                if (currDistance < minDistance || minKey == null) {
                    minKey = key;
                    minDistance = currDistance;
                } else if (currDistance == minDistance && key <= minKey) {
                    minKey = key;
                }
            }
        }
        return minKey;
    }
    /**
     * Finds and returns the maximum brightness value in the char_map.
     *
     * @param hashMap The character-to-brightness map.
     * @return The maximum brightness value.
     */

    private static double get_Max(HashMap<Character, Double> hashMap) {
        double max_value = Constants.INIT_ZERO;
        for (Character key : hashMap.keySet()) {
            if (hashMap.get(key)> max_value) {
                max_value = hashMap.get(key);
            }
        }
        return max_value;
    }
    /**
     * Finds and returns the minimum brightness value in the char_map.
     *
     * @param hashMap The character-to-brightness map.
     * @return The minimum brightness value.
     */

    private static double get_Min(HashMap<Character, Double> hashMap) {
        double min_value = Constants.MIN_VALUE;
        for (Character key : hashMap.keySet()) {
            if (hashMap.get(key) < min_value) {
                min_value = hashMap.get(key);
            }
        }
        return min_value;
    }
    /**
     * Calculates and returns the average brightness value of a character represented by a boolean matrix.
     *
     * @param matrix The boolean matrix representing the character.
     * @return The average brightness value.
     */
    private double brightness_of_char(boolean[][] matrix){
        double sum=Constants.INIT_ZERO;
        double avg;

        for (int i = Constants.INIT_ZERO; i < Constants.MATRIX_DIM; i++) {
            for (int j = Constants.INIT_ZERO; j < Constants.MATRIX_DIM; j++){
                if(matrix[i][j]){
                    sum += Constants.ONE;
                }
            }
        }
        avg= sum/(Constants.MATRIX_DIM * Constants.MATRIX_DIM);
        return avg;
    }

}

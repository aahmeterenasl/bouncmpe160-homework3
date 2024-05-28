/**
 * Class consist of methods that aims to make some operations easier, specifically to turn numbers and alphabetic strings to each other
 * @author Ahmet Eren Aslan, Student ID: 2021400126
 * @since Date: 04.05.2023
 */
public class Utilities {

    /**
     * Returns a number's alphabetic string equivalent
     * @param num Number wanted to be turned into alphabetic string
     * @param mode Changes the output string. "column" for non-capital strings and "lake" for capital ones
     * @return input number's alphabetic string equivalent
     */
    public static String numberToAlphabeticString(int num,String mode){
        // Checks for mode
        if(mode.equals("column".strip())) {
            // Returns the number's equivalent with non-capital strings
            if (num < 26) {
                return "" + (char) (num + (int) 'a');
            } else {
                return "" + (char) (num/26 - 1 + (int) 'a') + "" + (char) (num % 26 + (int) 'a');
            }
        }
        else if (mode.equals("lake".strip())){
            // Returns the number's equivalent with capital strings
            if (num < 26) {
                return "" + (char) (num + (int) 'A');
            } else {
                return "" + (char) (num / 26 - 1 + (int) 'A') + "" + (char) (num % 26 + (int) 'A');
            }
        }
        return "";
    }

    /**
     * Returns a alphabetic string's number equivalent
     * @param alphabeticString Number wanted to be turned into alphabetic string
     * @param mode Represents the input string. "column" for non-capital strings and "lake" for capital ones
     * @return input alphabetic string's number equivalent
     */
    public static int alphabeticStringToNumber(String alphabeticString,String mode){
        int returnValue = 0;
        // Checks for mode
        if(mode.equals("column".strip())) {
            // Returns the non-capital string's equivalent
            for (int i = 0; i < alphabeticString.length(); i++) {
                returnValue += ((int)alphabeticString.charAt(i) - (int)'a' + 1) * Math.pow(26,alphabeticString.length()-i-1);
            }
            return returnValue - 1;
        }
        else if (mode.equals("lake".strip())){
            // Returns the capital string's equivalent
            for (int i = 0; i < alphabeticString.length(); i++) {
                returnValue += ((int)alphabeticString.charAt(i) - (int)'A' + 1) * Math.pow(26,alphabeticString.length()-i-1);
            }
            return  returnValue - 1;
        }
        return -1;
    }



}

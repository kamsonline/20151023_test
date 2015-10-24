package program1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to Match for possible words for the given number.
 * It takes a phone number of 10 digits long.
 * <p/>
 * ASSUMPTIONS:
 * Converts the numbers to corresponding alphabets, e.g. 2 - a, b or c and 3 - d, e or f etc.
 * NOTE: 0 and 1 will be ignored since there is no matching alphabet.
 * Because of the above reason, the number of characters in the resulting word may not be 10 characters long.
 * Behaviour will be very similar to http://spellmynumber.mnim.org/ *
 */
public class WordMatcher {
    static Map<Character, String> letterMap = new HashMap<>();

    static {
        letterMap.put('0', "");
        letterMap.put('1', "");
        letterMap.put('2', "abc");
        letterMap.put('3', "def");
        letterMap.put('4', "ghi");
        letterMap.put('5', "jkl");
        letterMap.put('6', "mno");
        letterMap.put('7', "pqrs");
        letterMap.put('8', "tuv");
        letterMap.put('9', "wxyz");
    }

    /**
     * Method to convert the input phone number to list of matching words from the
     * word list using the supplied wordDictionaryContainer.
     *
     * @param phoneNumber             the phone number
     * @param wordDictionaryContainer which performs the matching of word against a list of dictionary words it holds.
     * @return the list of words.
     */
    public static ArrayList<String> convertDigitToWords(String phoneNumber,
                                                        WordDictionaryContainer wordDictionaryContainer) {
        return processNumbersToWords(phoneNumber, 0, null, wordDictionaryContainer);
    }

    /**
     * Method to recursively formulate the possible permutations of words from the phoneNumber and check them agains the
     * words collection in the supplied wordDictionaryContainer
     *
     * @param phoneNumber             the phone number
     * @param index                   the index which is used for recursive functionality
     * @param resultingCharacters     the array of char which is used in recursive functionality
     * @param wordDictionaryContainer the object to check if the identified combination of words is actually a
     *                                word from the word list.
     * @return the list of words
     */
    private static ArrayList<String> processNumbersToWords(String phoneNumber, int index, char[] resultingCharacters,
                                                           WordDictionaryContainer wordDictionaryContainer) {
        ArrayList<String> result = new ArrayList<String>();
        if (resultingCharacters == null) {
            resultingCharacters = new char[phoneNumber.length()];
        }

        // While processing the numbers recursively, once the end of phone number digit is reached,
        // return the characters as a string and return the preceding call.
        if (index == phoneNumber.length()) {
            String value = new String(resultingCharacters);

            // NOTE for numbers 0 and 1, there is no matching characters, so replace them with empty characters.
            value = value.replaceAll("\u0000", "");
            if (wordDictionaryContainer.isWordExistInDictionary(value)) {
                result.add(value);
            }
            return result;
        }

        // Call the method recursively to process each and every permutations of the possible words
        String letters = letterMap.get(phoneNumber.charAt(index));
        if (!letters.isEmpty()) {
            for (int j = 0; j < letters.length(); j++) {
                resultingCharacters[index] = letters.charAt(j);
                result.addAll(processNumbersToWords(phoneNumber, index + 1, resultingCharacters, wordDictionaryContainer));
            }
        } else {
            result.addAll(processNumbersToWords(phoneNumber, index + 1, resultingCharacters, wordDictionaryContainer));
        }

        return result;
    }
}

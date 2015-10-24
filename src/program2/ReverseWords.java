package program2;

/**
 * Class to reverse the words in a sentence.
 * <p/>
 * Assumptions:
 * - space is assumed to be delimiter.
 * - Thus "This is a test." will be reversed as "test. a is this" and not as ".test a is this"
 * - Also "he is 30-year-old" will be reversed as "30-year-old is he" and not as "old-year-30 is he".
 * - Also "(This is a dog)" will be reversed as "dog) a is (This" and not as ")dog a is This("
 */
public class ReverseWords {

    private static String inputString = "This is a test";
    private static String DELIMITTER = " ";

    public static void main(String[] args) throws Exception {
        ReverseWords rw = new ReverseWords();
        System.out.println(rw.reverseWords(inputString));
    }

    /**
     * Method to reverse the words for the supplied input string.
     *
     * @param input the input string
     * @return the reversed words as String for the supplied input string.
     */
    public String reverseWords(String input) {
        // Get the first index of the delimitter for the supplied input string
        int index = input.indexOf(DELIMITTER);
        // If the index is -1, then it is the end of the string and thus return the input string.
        if (index == -1) {
            return input;
        } else {
            StringBuilder reversedString = new StringBuilder();
            // Call recursively until there is a delimitter and append the strings using the delimitter.
            return reversedString
                    .append(reverseWords(input.substring(index + 1)))
                    .append(DELIMITTER)
                    .append(input.substring(0, index))
                    .toString();
        }
    }
}

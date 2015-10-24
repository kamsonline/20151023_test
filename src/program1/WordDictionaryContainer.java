package program1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class to read the word list file which contains comma separated list of words.
 * ASSUMPTION: This class is assuming that the list of words is sorted in alphabetical order (as this was meant to test for words in dictionary).
 * This class also provides a method to test whether the supplied word is contained in the word list.
 */
public class WordDictionaryContainer {
    private String[] wordList;
    private static final String WORD_LIST_FILE = "src/program1/wordList.txt";
    private static final String DELIMITTER = ",";

    public WordDictionaryContainer() {
        wordList = getListOfWords();
    }

    /**
     * Method to load the list of words from the file.
     *
     * @return the array of words.
     */
    private static String[] getListOfWords() {

        BufferedReader br = null;
        String[] wordList = null;

        try {
            br = new BufferedReader(new FileReader(WORD_LIST_FILE));
            String line = null;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                wordList = line.split(DELIMITTER);
                return wordList; // Assuming only one line of content for this test file..
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return wordList;
    }

    /**
     * Method to check whether the input word is contained in the word list.
     * It usues the Arrays.binarySearch to search through the list of words.
     *
     * @param inputString the input string to test
     * @return the boolean indicating whether the string is part of the list of words or not.
     */
    public boolean isWordExistInDictionary(String inputString) {
        return Arrays.binarySearch(wordList, inputString) > 0;
    }
}
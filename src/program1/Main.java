package program1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // creating the instance of WordDictionaryContainer will load the word lists into memory.
        WordDictionaryContainer wordDictionaryContainer = new WordDictionaryContainer();

        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        //  prompt for the phone number
        System.out.print("Enter 10 digit phone number: ");

        // get their input as a String
        String phoneNumber = scanner.next();
        if (phoneNumber.isEmpty() || phoneNumber.length() != 10 || !phoneNumber.matches("[0-9]+")) {
            System.out.println("Invalid input. Please enter 10 digit phone number.");
        } else {
            ArrayList<String> result = WordMatcher.convertDigitToWords(phoneNumber, wordDictionaryContainer);
            if (result == null || result.isEmpty()) {
                System.out.println("No words found!");
            } else {
                System.out.println("\nFound " + result.size() + " word(s)");
            }
            result.forEach(str -> System.out.println(str));
        }
    }
}

### Program1

Takes any 10-digit phone number and produces a list of words matching ~~first~~ letters of the phone number (2 – ABC, 3 – DEF, .. 9 – WZXYZ).

Word list is taken from the source http://www-01.sil.org/linguistics/wordlists/english/wordlist/wordsEn.txt and converted to a comma separated file.

ASSUMPTIONS:
- Converts the numbers to corresponding alphabets, e.g. 2 - a, b or c and 3 - d, e or f etc.
- NOTE: 0 and 1 will be ignored since there is no matching alphabet.
- Because of the above reason, the number of characters in the resulting word may not be 10 characters long.
- Based on the clarification, any matching letter will be used for the number, not just the first letter.
- Behaviour will be very similar to http://spellmynumber.mnim.org/ *

Sample Phone Numbers for test: 0280188307, 0467678268, 0230188307

### Program2

Reverses the words in a sentence

ASSUMPTIONS:
- Space is assumed to be delimiter (not word break '\b').
- Thus "This is a test." will be reversed as "test. a is this" and not as ".test a is this"
- Also "he is 30-year-old" will be reversed as "30-year-old is he" and not as "old-year-30 is he".
- Also "(This is a dog)" will be reversed as "dog) a is (This" and not as ")dog a is This("


### Program3

Calculate the number of lines in Java code excluding comments and empty lines - Javascript solution

Similar to http://codekata.com/kata/kata13-counting-code-lines/

Solution is also available in jsfiddle in the following link. http://jsfiddle.net/kamsonline/e3mfswy6/

HOW TO TEST IN JSFIDDLE:
- Copy paste / type the java code in the text area.
- Click the 'Test' button in the bottom.
- Number of lines will be displayed above the text are.

### Program4

Bloom filter - Javascript solution

Similar to http://codekata.com/kata/kata05-bloom-filters/

Solution is also available in jsfiddle in the following link. http://jsfiddle.net/kamsonline/u5wsqbhj/

HOW TO TEST IN JSFIDDLE:
- Copy paste / type the words (comma separated list of words) and click 'Add' to add the words to the bloom filter.
- Added words will be displayed at the bottom for reference.
- Enter the word to search for in the text area and click 'Test'
- If no match, 'No Match' will be displayed. If there is a hit in the bloom filter, then 'Possible Match' will be displayed.

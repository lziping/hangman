package hangman;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class HangmanEvilTest {

	@Test
	void testPickWord() {

		ArrayList<String> sampleWordList = new ArrayList<String>();
		sampleWordList.add("test");
		sampleWordList.add("skys");
		sampleWordList.add("books");
		sampleWordList.add("booth");
		sampleWordList.add("teacher");

		HangmanEvil hangman = new HangmanEvil(sampleWordList);

		hangman.pickWord("test");

		// the given word "test" has length 4
		// then should have 4 empty underscore at start
		assertEquals(hangman.getCorrectLetters(), "____");
		// and have 2 word inside sampleWordList that come with length 4
		// then word list size should be 2
		assertEquals(hangman.wordList.size(), 2);

		// add more 4 length word
		sampleWordList.add("look");

		hangman = new HangmanEvil(sampleWordList);
		hangman.pickWord("test");

		// then word list size should be 3
		assertEquals(hangman.wordList.size(), 3);
		assertEquals(hangman.getCorrectLetters(), "____");

		hangman = new HangmanEvil(sampleWordList);
		// set the givenWord to the word with length 7
		hangman.pickWord("teacher");

		// in sampleWordList have only 1 word that have length 7
		// then currectLetters should be 7 underscore
		assertEquals(hangman.getCorrectLetters(), "_______");
		// and wordList size should be 1
		assertEquals(hangman.wordList.size(), 1);
	}

	@Test
	void testFindAndMarkLetter() {

		ArrayList<String> sampleWordList = new ArrayList<String>(Arrays.asList("heel", "help", "belt", "hair", "make"));

		HangmanEvil hangman = new HangmanEvil(sampleWordList);

		// for testing, should disable the random length behavior by pickWord with given
		// word
		hangman.pickWord("heel");
		// should found
		assertTrue(hangman.findAndMarkLetter("e"));
		assertEquals(hangman.getCorrectLetters(), "_e__");

		// should found
		assertTrue(hangman.findAndMarkLetter("l"));
		assertEquals(hangman.getCorrectLetters(), "_el_");

		// should not found
		assertFalse(hangman.findAndMarkLetter("x"));
		assertEquals(hangman.getCorrectLetters(), "_el_");

		// after this case, can't test found or not found directly because it's will
		// have multiple possibleGroups
		hangman.findAndMarkLetter("b");
		// when input "b", Hangman will have 2 family, both have 1 word
		// hangman can possibly random 2 case, which are
		// "bel_" in the case that last word was random to "belt"
		// "_el_" in the case that last word was random to "help"
		// but if player input "t" "h" and "p" will cover all the rest of possible
		// letter
		// then should have no underscore inside correct letter
		hangman.findAndMarkLetter("t");
		hangman.findAndMarkLetter("h");
		hangman.findAndMarkLetter("p");
		assertFalse(hangman.getCorrectLetters().contains("_"));
		assertTrue(hangman.getCorrectLetters().equals("belt") || hangman.getCorrectLetters().equals("help"));

		// case multiple length word, should filter out unmatched length words

		sampleWordList = new ArrayList<String>(Arrays.asList("heel", "help", "belt", "hair", "make", "pig", "sad"));
		hangman = new HangmanEvil(sampleWordList);
		// it can be random to "pig" or "sad" word, can't test it directly
		// but if input all letter that cover both 2 word should have no underscore left
		hangman.pickWord("sad");

		hangman.findAndMarkLetter("s");
		hangman.findAndMarkLetter("a");
		hangman.findAndMarkLetter("d");
		hangman.findAndMarkLetter("p");
		hangman.findAndMarkLetter("i");
		hangman.findAndMarkLetter("g");

		assertFalse(hangman.getCorrectLetters().contains("_"));
		assertTrue(hangman.getCorrectLetters().equals("pig") || hangman.getCorrectLetters().equals("sad"));

	}

	@Test
	void testPickWordString() {
		ArrayList<String> sampleWordList = new ArrayList<String>(
				Arrays.asList("heel", "help", "belt", "hair", "make", "example"));

		HangmanEvil hangman = new HangmanEvil(sampleWordList);

		hangman.pickWord("xxxx");
		assertEquals(hangman.getCorrectLetters(), "____");

		hangman = new HangmanEvil(sampleWordList);

		hangman.pickWord("example");
		assertEquals(hangman.getCorrectLetters(), "_______");
	}

}

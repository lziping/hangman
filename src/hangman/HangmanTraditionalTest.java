package hangman;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class HangmanTraditionalTest {

	@Test
	void testPickWord() {

		ArrayList<String> sampleWordList = new ArrayList<String>(
				Arrays.asList("test", "northamerica", "teacher", "example"));

		Hangman hangman = new HangmanTraditional(sampleWordList);

		// the picked word should be always contains in sampleWordList
		assertTrue(sampleWordList.contains(hangman.pickWord()));
		assertTrue(sampleWordList.contains(hangman.pickWord()));
		assertTrue(sampleWordList.contains(hangman.pickWord()));
		assertTrue(sampleWordList.contains(hangman.pickWord()));

	}

	@Test
	void testFindAndMarkLetter() {

		ArrayList<String> sampleWordList = new ArrayList<String>();
		sampleWordList.add("test");
		Hangman hangman = new HangmanTraditional(sampleWordList);
		hangman.pickWord();

		// check one char only
		String letter = "ch";
		assertFalse(hangman.findAndMarkLetter(letter));

		letter = "e";
		assertTrue(hangman.findAndMarkLetter(letter));

		// check lower case
		letter = "E";
		assertTrue(hangman.findAndMarkLetter(letter));

		letter = "S";
		assertTrue(hangman.findAndMarkLetter(letter));

		// check replacement
		hangman = new HangmanTraditional(sampleWordList);
		hangman.pickWord();
		letter = "S";
		hangman.findAndMarkLetter(letter);
		assertEquals(hangman.getCorrectLetters(), "__s_");

		hangman = new HangmanTraditional(sampleWordList);
		hangman.pickWord();
		letter = "";
		hangman.findAndMarkLetter(letter);
		assertEquals(hangman.getCorrectLetters(), "____");

		// check replacement
		hangman = new HangmanTraditional(sampleWordList);
		hangman.pickWord();
		letter = "X";
		hangman.findAndMarkLetter(letter);
		assertEquals(hangman.getCorrectLetters(), "____");

		// add to incorrect list

		// increment the guess counter

		assertEquals(hangman.guessCount, 1);

		letter = "z";
		hangman.findAndMarkLetter(letter);
		assertEquals(hangman.guessCount, 2);

	}

	@Test
	void testIsLetterInWord() {

		ArrayList<String> sampleWordList = new ArrayList<String>(Arrays.asList("example"));

		Hangman hangman = new HangmanTraditional(sampleWordList);

		hangman.pickWord(); // should always pick "example"

		assertTrue(hangman.isLetterInWord("e"));
		assertTrue(hangman.isLetterInWord("x"));
		assertFalse(hangman.isLetterInWord("r"));
	}

	@Test
	void testGameOver() {
		ArrayList<String> sampleWordList = new ArrayList<String>(Arrays.asList("example"));

		Hangman hangman = new HangmanTraditional(sampleWordList);

		hangman.pickWord();

		assertFalse(hangman.gameOver());

		hangman.findAndMarkLetter("e");
		hangman.findAndMarkLetter("x");
		hangman.findAndMarkLetter("a");
		hangman.findAndMarkLetter("m");
		hangman.findAndMarkLetter("p");
		hangman.findAndMarkLetter("l");
		hangman.findAndMarkLetter("e");

		assertTrue(hangman.gameOver());
	}

	@Test
	void testGuessed() {
		ArrayList<String> sampleWordList = new ArrayList<String>(Arrays.asList("example"));

		Hangman hangman = new HangmanTraditional(sampleWordList);

		hangman.pickWord();

		assertFalse(hangman.guessed("e"));

		hangman.findAndMarkLetter("e");

		assertTrue(hangman.guessed("e"));
	}

	@Test
	void testCheckLetters() {
		ArrayList<String> sampleWordList = new ArrayList<String>(Arrays.asList("example"));

		Hangman hangman = new HangmanTraditional(sampleWordList);

		hangman.pickWord();

		assertFalse(hangman.checkLetters());

		hangman.findAndMarkLetter("e");
		hangman.findAndMarkLetter("x");
		hangman.findAndMarkLetter("a");
		hangman.findAndMarkLetter("m");
		hangman.findAndMarkLetter("p");
		hangman.findAndMarkLetter("l");
		hangman.findAndMarkLetter("e");

		assertTrue(hangman.checkLetters());
	}

	@Test
	void testGetCorrectLetters() {
		ArrayList<String> sampleWordList = new ArrayList<String>(Arrays.asList("example"));

		Hangman hangman = new HangmanTraditional(sampleWordList);

		hangman.pickWord();

		assertEquals(hangman.getCorrectLetters(), "_______");

		hangman.findAndMarkLetter("x");

		assertEquals(hangman.getCorrectLetters(), "_x_____");

	}

	@Test
	void testGetIncorrectGuesses() {
		ArrayList<String> sampleWordList = new ArrayList<String>(Arrays.asList("example"));

		Hangman hangman = new HangmanTraditional(sampleWordList);

		hangman.pickWord();
		assertFalse(hangman.getIncorrectGuesses().contains("z"));
		hangman.findAndMarkLetter("z");
		assertTrue(hangman.getIncorrectGuesses().contains("z"));

	}

	@Test
	void testGetNumberGuesses() {
		ArrayList<String> sampleWordList = new ArrayList<String>(Arrays.asList("example"));

		Hangman hangman = new HangmanTraditional(sampleWordList);

		hangman.pickWord();

		assertEquals(hangman.getNumberGuesses(), 0);
		hangman.findAndMarkLetter("a");
		assertEquals(hangman.getNumberGuesses(), 1);
		hangman.findAndMarkLetter("b");
		assertEquals(hangman.getNumberGuesses(), 2);
		hangman.findAndMarkLetter("c");
		assertEquals(hangman.getNumberGuesses(), 3);
	}

}

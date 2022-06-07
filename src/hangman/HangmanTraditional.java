package hangman;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is a subclass of Hangman(the superclass)
 * This class will play the game of Hangman regular version
 * @author ZiPing Lin
 *
 * @author Din Huang
 */
public class HangmanTraditional extends Hangman {
	
	/**
	 * the version of the game
	 */
	final static String VERSION = "Traditional";

	/**
	 * Create instance of traditional hangman with given list of words
	 * 
	 * @param wordList list of cleaned words
	 */
	public HangmanTraditional(ArrayList<String> wordList) {
		//this.wordList = wordList;
		super(wordList);
	}

	/**
	 * Picks a random word in the word list and sets the word fro Traditional
	 * Hangman
	 * 
	 * @return Selected word
	 */

	@Override
	public String pickWord() {

		// get random word
		Random random = new Random();
		int randInt = random.nextInt(this.wordList.size());
		this.word = this.wordList.get(randInt).toLowerCase();

		// set correctly identified letters of word
		// default to "_"
		this.correctLetters = new ArrayList<String>(this.word.length());
		for (int i = 0; i < this.word.length(); i++) {
			this.correctLetters.add(Hangman.HIDDEN_LETTER_CHAR);
		}
		//System.out.println(this.correctLetters.toString());
		return this.word;
	}

	@Override
	public boolean findAndMarkLetter(String letter) {

		boolean foundLetter = false;

		// if letter is not single character, ignore

		if (letter.length() != 1)
			return foundLetter;

		// convert to lowercase
		letter = letter.toLowerCase();

		for (int i = 0; i <= this.word.length() - 1; i++) {
			if (letter.equals(this.word.charAt(i) + "")) {

				// every position letter is located, mark as correctly identified letter
				this.correctLetters.set(i, letter);
				foundLetter = true;
			}
		}

		// if letter wasn't found
		if (!foundLetter) {

			// add to list of incorrectGuesses
			if (!this.incorrectGuesses.contains(letter)) {
				this.incorrectGuesses.add(letter);
			}
		}

		// increment number of guesses
		this.guessCount++;

		return foundLetter;

	}

	@Override
	public String getVersion() {
		return HangmanTraditional.VERSION;
	}

}

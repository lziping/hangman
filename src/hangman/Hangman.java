package hangman;

import java.util.ArrayList;

/**
 * This abstract class is the superclass of Hangman Traditional and Hangman Evil
 * 
 * @author ZiPing Lin
 * @author Din Huang
 */ 
public abstract class Hangman {

	/**
	 * underscore for blank/un guessed
	 */
	public static final String HIDDEN_LETTER_CHAR = "_";

	/**
	 * list of word from dictionary
	 */
	public ArrayList<String> wordList = new ArrayList<String>();

	/**
	 * ArrayList of incorrect letter guessed
	 */
	public ArrayList<String> incorrectGuesses = new ArrayList<String>();

	/**
	 * representing the "_a__e__" on the screen
	 */
	public ArrayList<String> correctLetters;

	/**
	 * only for Traditional version
	 */
	public String word;

	/**
	 * amount of guess attempted
	 */
	public int guessCount;

	/**
	 * only for Traditional version
	 * 
	 * @param letter letter to find in current word
	 * @return true, if found letter in current word, Otherwise, false
	 */
	public boolean isLetterInWord(String letter) {

		if (word.contains(letter)) {
			return true;
		}

		return false;
	}

	/**
	 * check is game over, by look at remaining underscore that will display on
	 * screen if no one left, it's mean game over
	 * 
	 * @return true, if no underscore left in correctLetters
	 */
	public boolean gameOver() {

		if (!this.getCorrectLetters().contains(Hangman.HIDDEN_LETTER_CHAR)) {
			return true;
		}
		return false;
	}

	/**
	 * Initialize the abstract version of pickWord force the traditional and evil
	 * mode to write their own override versions
	 */
	abstract public String pickWord();

	// constructor
	/**
	 * Constructor for this class Initialize the random word covers by underscores
	 */
	public Hangman(ArrayList<String> wordList) {
		this.wordList = wordList;

	}

	// methods
	/**
	 * find the input letter in word, and mark as correct letter when found
	 * 
	 * @param letter input letter
	 * @return true, if the letter was found
	 */
	abstract public boolean findAndMarkLetter(String letter);

	/**
	 * check is the input letter is already guessed or not
	 * 
	 * @param letter input letter
	 * @return true, if the input letter was already guessed
	 */
	public boolean guessed(String letter) {
		return this.guessedIncorrectly(letter) || this.guessedCorrectly(letter);

	}

	/**
	 * check is the input letter is already guessed and incorrect
	 * 
	 * @param letter input letter
	 * @return true, if the input letter is already guessed and incorrect
	 */
	private boolean guessedIncorrectly(String letter) {
		return this.incorrectGuesses.contains(letter);
	}

	/**
	 * check is the input letter is already guessed and correct
	 * 
	 * @param letter input letter
	 * @return true, if the input letter is already guessed and correct
	 */
	private boolean guessedCorrectly(String letter) {
		return this.correctLetters.contains(letter);

	}

	/**
	 * check is all letters have been found by look at remaining underscore in
	 * correctLetters (like check is game over)
	 * 
	 * @return true, if no underscore left in correctLetters
	 */
	public boolean checkLetters() {
		boolean lettersAllCheck = true;
		for (int i = 0; i < this.correctLetters.size(); i++) {
			String correctLetter = this.correctLetters.get(i);
			if (Hangman.HIDDEN_LETTER_CHAR.equals(correctLetter)) {
				lettersAllCheck = false;
				break;
			}
		}
		return lettersAllCheck;
	}

	/**
	 * return the correctLetters as String (ex. __a___)
	 * 
	 * @return correctLetters as String
	 */
	public String getCorrectLetters() {
		StringBuilder sb = new StringBuilder();
		for (String correctLetter : this.correctLetters) {
			sb.append(correctLetter);
		}

		return sb.toString();
	}

	/**
	 * return String containing an array of incorrectly guessed letters and the
	 * count of incorrectly guessed letters
	 * 
	 * @return String containing information
	 */
	public String getIncorrectGuesses() {
		StringBuilder sb = new StringBuilder();
		if (this.incorrectGuesses.size() > 0) {

		}

		for (String incorrectGuesses : this.incorrectGuesses) {
			sb.append(incorrectGuesses);
		}

		return sb.toString();

	}

	/**
	 * get the current guessed count
	 * 
	 * @return count of guess times
	 */
	public int getNumberGuesses() {
		return this.guessCount;
	}

	/**
	 * return version name of hangman
	 * 
	 * @return name of hangman version
	 */
	abstract public String getVersion();

}


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import dictionary.Dictionary;
import hangman.Hangman;
import hangman.HangmanEvil;
import hangman.HangmanTraditional;

/**
 * This class will control and process the Hangman Game
 * 
 * @author ZiPing Lin
 * 
 * @author Din Huang
 */

public class HangmanGame {

	/**
	 * Play hangmamn with a dictionary file of words with the given fileName.
	 * 
	 * @param fileName for dictionary file
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		HangmanGame hangmanGame = new HangmanGame();

		hangmanGame.playGame("words_clean.txt");
	}

	/**
	 * This class will control the game 
	 * @param fileName the name of the dictionary file
	 * @throws FileNotFoundException if file name is not found
	 */
	private void playGame(String fileName) throws FileNotFoundException {

		// create hangman dictionary
		ArrayList<String> wordList = Dictionary.readFileCleanWords(fileName);

		boolean gameOver = false;
		Scanner scanner = new Scanner(System.in);

		String ans;

//		while (!gameOver) {
		Hangman hangman = this.getHangmanVersion(wordList);

		System.out.println("Welcom to Hangman!\n");
//			System.out.println("Hangman version : " + hangman.getVersion());

		boolean gameWon = false;
		String chosenLetter;

		hangman.pickWord();

		this.printRound(hangman, "Guess a letter");

		while (!gameWon) {
			if (scanner.hasNext()) {
				chosenLetter = scanner.nextLine();
				chosenLetter = chosenLetter.trim();

				if (chosenLetter.length() != 1 || !Pattern.matches("[a-z]", chosenLetter)) {
					this.printRound(hangman, "please input one valid char only!");

				} else {
					if (hangman.guessed(chosenLetter)) {
						System.out.println("You already guessed that letter!");
					} else {
						hangman.findAndMarkLetter(chosenLetter);

						if (hangman.gameOver()) {
							this.printRound(hangman, "Well Done!");
							
							System.out.println("Total gyesses : "+hangman.guessCount);

								
								System.out.println("Total Incorrect guesses :"+hangman.incorrectGuesses.size());
							
							gameWon = true;

							break;
						} else {
							this.printRound(hangman, "Guess a letter");
					

						}
					}
				}
				if(hangman.incorrectGuesses.size()!=0) {
					System.out.println("Incorrect guesses"+hangman.incorrectGuesses);
				}
			}
		}

		System.out.println("Hangman version : " + hangman.getVersion());

		scanner.close();
	}

	/**
	 * this method will get the version of hangman played at the end of game 
	 * @param wordList of the dictionary used
	 * @return the version of the game
	 */
	private Hangman getHangmanVersion(ArrayList<String> wordList) {

		// randomly determine which version to play
		boolean game = new Random().nextBoolean();
		Hangman hangman = game ? new HangmanTraditional(wordList) : new HangmanEvil(wordList);

		return hangman;
	}

	/**
	 * This will print the display of the information of the game status during the game
	 * @param hangman the class of game version
	 * @param s String status, EX: during the game - guess a letter, gameover - well done
	 */
	private void printRound(Hangman hangman, String s) {
		System.out.println(s);
		
		//iterate to get the result string
		for (int i = 0; i < hangman.getCorrectLetters().length(); i++) {

			System.out.print(hangman.getCorrectLetters().charAt(i)+" ");
		}
		System.out.println(" ");
		
		

	}

}
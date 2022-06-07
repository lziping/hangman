package hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * This is subclass of Hangman Class, This class will represent and play the
 * game in Hangman Evil version
 * 
 * 
 * @author ZiPing Lin
 *
 * @author Din Huang
 */
public class HangmanEvil extends Hangman {

	/**
	 * the version of this hangman
	 */
	final static String VERSION = "Evil";

	/**
	 * create instance of evil hangman with given list of words.
	 * 
	 * @param wordList list of words
	 */
	public HangmanEvil(ArrayList<String> wordList) {
		//this.wordList = wordList;
		super(wordList);
		
		
	}

	/**
	 * Takes given word and sets the length of the word for Evil Hangman.
	 * 
	 * @param givenWord to use for game
	 * @return Selected word represented by "_" String characters
	 */
	public String pickWord(String givenWord) {
		this.word = "";
		this.correctLetters = new ArrayList<String>(givenWord.length());
		for (int i = 0; i < givenWord.length(); i++) {
			this.word += Hangman.HIDDEN_LETTER_CHAR;
			this.correctLetters.add(Hangman.HIDDEN_LETTER_CHAR);
		}

		// partition word list by length of selected word
		this.partitionByLength(this.word.length());

		return this.word;
	}

	@Override
	public String pickWord() {
		Random random = new Random();
		int index = random.nextInt(this.wordList.size());
		String word = this.wordList.get(index);
		return this.pickWord(word);
	}

	/**
	 * This will get the group of words
	 * @param letter that user input
	 * @return the famly group
	 */
	private String partitionByLetter(String letter) {

		HashMap<String, ArrayList<String>> wordGroups = new HashMap<String, ArrayList<String>>();

		// to generate key for word groups
		StringBuilder keySb;

		// iterate over list of words in list
		for (String w : this.wordList) {

			// create key based on currently selected letters
			// as __z_x_y_ blah blah blah
			keySb = this.getKeySb(this.correctLetters);

			// compare guessed letter to each letter in word
			for (int i = 0; i <= w.length() - 1; i++) {
				if (letter.equals(w.charAt(i) + "")) {
					keySb.setCharAt(i, w.charAt(i));
				}
			}

			// add word (and list ) to a group
			if (wordGroups.containsKey(keySb.toString())) {
				ArrayList<String> wList = wordGroups.get(keySb.toString());
				wList.add(w);
				wordGroups.replace(keySb.toString(), wList);
			} else {
				ArrayList<String> wList = new ArrayList<String>();
				wList.add(w);
				wordGroups.put(keySb.toString(), wList);
			}

		}

		return this.updateWordList(wordGroups);

	}

	/**
	 * Loop over the different list of options and update the one with the max size
	 * to be the list to guess on
	 * 
	 * @param wordGroups
	 * @return a max size list for user to guess
	 */
	private String updateWordList(HashMap<String, ArrayList<String>> wordGroups) {

//		String maxWordListKey = Helper.findLargestWordGroupKey(wordGroups);
//		ArrayList<String> maxWordList = wordGroups.get(maxWordListKey);

		int maxWordListCount = 0;
		String maxWordListKey = "";

		ArrayList<String> possibleGroups = new ArrayList<String>();

		ArrayList<String> maxWordList = new ArrayList<String>();

		for (String key : wordGroups.keySet()) {
			ArrayList<String> wordGroup = wordGroups.get(key);

			int wordListCount = wordGroup.size();
			if (wordListCount >= maxWordListCount) {

				maxWordListKey = key;

				// if it's the biggest group yet, reset tempWordLists
				if (wordListCount > maxWordListCount) {
					possibleGroups.clear();
					maxWordListCount = wordListCount;
				}
				possibleGroups.add(maxWordListKey);
			}

		}

		Random random = new Random();
		int keyIndex = random.nextInt(possibleGroups.size());

		maxWordListKey = possibleGroups.get(keyIndex);

		maxWordList = wordGroups.get(maxWordListKey);

		maxWordListCount = maxWordList.size();

		this.wordList = new ArrayList<String>(maxWordList);

		this.correctLetters = new ArrayList<String>(Arrays.asList(maxWordListKey.split("")));

		return maxWordListKey;

	}

	/**
	 * replace the whole wordList with all the word that have same length as picked
	 * word (selectedWordLength)
	 * 
	 * @param selectedWordLength
	 */
	private ArrayList<String> partitionByLength(int selectedWordLength) {

		int wordLength;
		ArrayList<String> wordList = new ArrayList<String>();

		// for each word in word list
		for (String word : this.wordList) {

			// get the length
			wordLength = word.length();

			// only map words with same length as selectedWordLength
			if (wordLength == selectedWordLength) {
				wordList.add(word);
			}

		}
		this.wordList = new ArrayList<String>(wordList);

		return this.wordList;
	}

	@Override
	public boolean findAndMarkLetter(String letter) {

		boolean foundLetter = false;

		// if letter is not single character, ignore

		if (letter.length() != 1)
			return foundLetter;

		// convert to lowercase
		letter = letter.toLowerCase();

		// partition word groups and get key of largest group
		String largestWordGroupKey = this.partitionByLetter(letter);

		// test if guessed letter is in key
		for (int i = 0; i <= largestWordGroupKey.length() - 1; i++) {
			if (letter.equals(largestWordGroupKey.charAt(i) + "")) {
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

	/**
	 * just turn ArrayList of String into StringBulder
	 * 
	 * @param arrayList
	 * @return the String
	 */
	private StringBuilder getKeySb(ArrayList<String> arrayList) {
		StringBuilder keySb = new StringBuilder();

		for (String c : arrayList) {
			keySb.append(c);
		}
		return keySb;

	}

	@Override
	public String getVersion() {
		return HangmanEvil.VERSION;
	}

}

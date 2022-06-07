package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class will reads and parses the dictionary file that are use for the
 * game and removes unacceptable words.
 * 
 * @author ZiPing Lin
 * 
 * @author Din Huang
 *
 */

public class Dictionary {

	/**
	 * read each word in the file and add them to the cleanWords arraylist
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public static ArrayList<String> readFileCleanWords(String fileName) throws FileNotFoundException {

		/**
		 * store word
		 */
		ArrayList<String> cw = new ArrayList<>();

		/**
		 * create file
		 */
		File file = new File(fileName);

		/**
		 * create scanner with given file
		 */
		Scanner sc = new Scanner(file);

		/**
		 *  ReGex to check if a string contains upper case, 
		 *  lower case, special character numeric value
		 */
		String regex = "^[a-z]+$";

		/**
		 * Compile the ReGex
		 */
		Pattern p = Pattern.compile(regex);

		// while scanner has another token
		while (sc.hasNext()) {

			String nextWord = sc.next();

			// Find match between given string & regular expression
			Matcher m = p.matcher(nextWord);

			// if true
			if (m.matches()) {
				cw.add(nextWord);
//				System.out.println(nextWord);
			}
		}
		// close scanner object
		sc.close();

		return cw;
	}



}

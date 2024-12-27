
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.util.*;
import java.io.*;

public class HangmanLexicon {

	private ArrayList<String> list;

	public HangmanLexicon() {
		list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String s = br.readLine();
				if (s != null) {
					list.add(s);
				} else {
					br.close();
					break;
				}

			}
		} catch (IOException e) {
			System.out.println("No File found");
		}
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return list.size();
		// return 10;
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return list.get(index);
	};

}
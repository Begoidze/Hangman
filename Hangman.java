
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	// Gives access on the words.
	HangmanLexicon hang = new HangmanLexicon();

	private HangmanCanvas canvas;

	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);

	}

	/*
	 * Strings, ints and booleans below are used for checking when the game
	 * ends. everything's should be clear from their names.
	 */
	String hypens = "";

	String enteredLett;

	int lives = 8;

	boolean guessedLett;

	int hypensCntr;

	String guessedCheckerS = "-";

	// Chooses a random word from the file.
	private int wordChooser() {

		RandomGenerator rgen = RandomGenerator.getInstance();
		int words = hang.getWordCount();
		int i = rgen.nextInt(0, words - 1);
		return (i);
	}

	String theWord = hang.getWord(wordChooser());
	int length = theWord.length();

	// Using to print some information when the game starts.
	private void infGiver1() {
		println("Welcome to Hangman!");
		println("The word now looks like this: " + hypens);
		println("You have " + lives + " guesses left.");
	}

	/*
	 * Prints information as well but without the welcomemessage and also says
	 * what your previous guess was.
	 */
	private void infGiver2() {
		println("The word now looks like this: " + hypens);
		println("You have " + lives + " guesses left.");
		println("Your guess: " + enteredLett);
	}

	// Counts how many hyoens are there.
	private int countHypens() {
		hypensCntr = 0;
		for (int i = 0; i < length; i++) {
			if (hypens.charAt(i) == guessedCheckerS.charAt(0)) {
				hypensCntr++;
			}
		}
		return hypensCntr;
	}

	// Checks if there still are the "-"s in the word, if there are not,
	// finishes the game.
	private boolean endChecker() {

		countHypens();
		if (hypensCntr > 0) {
			return true;
		} else {
			return false;
		}
	}

	// Turns the chosen word in hypens.
	private void wordConverterIntoHypens() {
		for (int i = 0; i < length; i++) {
			hypens = hypens + "-";
		}
	}

	// Assks the user to put a letter.
	private void asker() {
		enteredLett = readLine("Enter Letter: ").toUpperCase();
		while (!(enteredLett.length() == 1 && Character.isLetter(enteredLett.charAt(0)))) {
			println("Please, enter only one letter (no other symboles allowed): ");
			enteredLett = readLine("Enter Letter: ").toUpperCase();
		}
	}

	// Runs the whole game.
	private void gameRunner() {

		while (endChecker() && lives > 0) {
			asker();
			guessedLett = false;
			for (int i = 0; i < length; i++) {

				if (theWord.charAt(i) == enteredLett.charAt(0)) {
					hypens = hypens.substring(0, i) + enteredLett.charAt(0) + hypens.substring(i + 1, length);
					canvas.displayWord(hypens);
					guessedLett = true;
					println("That guess is correct.");
				}
			}

			if (!guessedLett) {
				
				lives--;
				canvas.noteIncorrectGuess(enteredLett.charAt(0));
				println("There are no " + enteredLett + "'s in this word.");
			}
			infGiver2();

		}
	}

	// Decides if the player wins or loses.
	private void winOrLose() {

		countHypens();

		if (hypensCntr > 0) {
			loseMessage();
		} else {
			winMessage();
		}
	}

	// Uses if the user wins.
	private void winMessage() {
		println("You guessed the word: " + theWord);
		println("You win.");
	}

	// Uses if the user loses.
	private void loseMessage() {
		println("You're completely hung.");
		println("The word was: " + theWord);
		println("You lose.");
	}

	public void run() {

		canvas.reset();

		wordChooser();

		wordConverterIntoHypens();

		infGiver1();

		gameRunner();

		winOrLose();

	}

}

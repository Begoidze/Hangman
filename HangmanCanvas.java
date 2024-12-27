
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	private int mistakeCounter;
	String wrongLetts;

	/** Resets the display so that only the scaffold appears */
	
	
	public void reset() {

		removeAll();
		mistakeCounter = 0;

		scaffold();
		beam();
		rope();

	}
	
	

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		GLabel label = new GLabel(word);

		if (getElementAt(4 * X / 5, Y / 4) != null) {

			remove(getElementAt(4 * X / 5, Y / 4));
		}

		add(label, 4 * X / 5, Y / 4);

	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */
	public void noteIncorrectGuess(char letter) {
		
		wrongLetts = wrongLetts + letter;
		
		GLabel label = new GLabel(wrongLetts);

		if (getElementAt(4 * X / 5, Y / 4 + LOWER_ARM_LENGTH) != null) {

			remove(getElementAt(4 * X / 5, Y / 4 + LOWER_ARM_LENGTH));
		}

		add(label, 4 * X / 5, Y / 4 + LOWER_ARM_LENGTH);
		
		mistakeCounter++;
		if (mistakeCounter == 1) {
			head();
		} else if (mistakeCounter == 2) {
			body();
		} else if (mistakeCounter == 3) {
			leftHand();
			leftHandLower();
		} else if (mistakeCounter == 4) {
			rightHand();
			rightHandLower();
		} else if (mistakeCounter == 5) {
			hip();
			leftLeg();
		} else if (mistakeCounter == 6) {
			rightLeg();
		} else if (mistakeCounter == 7) {
			leftFoot();
		} else if (mistakeCounter == 8) {
			rightFoot();
		}
		
		
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

	double X = getWidth();
	double Y = getHeight();

	private void scaffold() {
		GLine gline = new GLine(X / 2 - BEAM_LENGTH, Y - SCAFFOLD_HEIGHT, X / 2 - BEAM_LENGTH, Y - SCAFFOLD_HEIGHT * 2);
		add(gline);
	}

	private void beam() {
		GLine gline = new GLine(X / 2 - BEAM_LENGTH, Y - SCAFFOLD_HEIGHT * 2, X / 2, Y - SCAFFOLD_HEIGHT * 2);
		add(gline);
	}

	private void rope() {
		GLine gline = new GLine(X / 2, Y - SCAFFOLD_HEIGHT * 2, X / 2, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH);
		add(gline);
	}

	private void head() {
		GOval oval = new GOval(2 * HEAD_RADIUS, 2 * HEAD_RADIUS);

		oval.setColor(Color.BLACK);

		add(oval, X / 2 - HEAD_RADIUS, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH);

	}

	private void body() {
		GLine gline = new GLine(X / 2, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH, X / 2,
				Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + BODY_LENGTH);
		add(gline);
	}

	private void leftHand() {
		GLine gline = new GLine(X / 2, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD,
				X / 2 + UPPER_ARM_LENGTH, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD);
		add(gline);
	}

	private void rightHand() {
		GLine gline = new GLine(X / 2, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD,
				X / 2 - UPPER_ARM_LENGTH, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD);
		add(gline);
	}

	private void leftHandLower() {
		GLine gline = new GLine(X / 2 + UPPER_ARM_LENGTH, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD,
				X / 2 + UPPER_ARM_LENGTH,
				Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(gline);
	}

	private void rightHandLower() {
		GLine gline = new GLine(X / 2 - UPPER_ARM_LENGTH, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD,
				X / 2 - UPPER_ARM_LENGTH,
				Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(gline);
	}

	private void hip() {
		GLine gline = new GLine(X / 2 - HIP_WIDTH / 2, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + BODY_LENGTH,
				X / 2 + HIP_WIDTH / 2, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + BODY_LENGTH);
		add(gline);
	}

	private void leftLeg() {
		GLine gline = new GLine(X / 2 + HIP_WIDTH / 2, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + BODY_LENGTH,
				X / 2 + HIP_WIDTH / 2, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + BODY_LENGTH + LEG_LENGTH);
		add(gline);
	}

	private void rightLeg() {
		GLine gline = new GLine(X / 2 - HIP_WIDTH / 2, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + BODY_LENGTH,
				X / 2 - HIP_WIDTH / 2, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + BODY_LENGTH + LEG_LENGTH);
		add(gline);
	}

	private void leftFoot() {
		GLine gline = new GLine(X / 2 + HIP_WIDTH / 2, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + BODY_LENGTH + LEG_LENGTH,
				X / 2 + HIP_WIDTH / 2 + FOOT_LENGTH, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + BODY_LENGTH + LEG_LENGTH);
		add(gline);
	}

	private void rightFoot() {
		GLine gline = new GLine(X / 2 - HIP_WIDTH / 2, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + BODY_LENGTH + LEG_LENGTH,
				X / 2 - HIP_WIDTH / 2 - FOOT_LENGTH, Y - SCAFFOLD_HEIGHT * 2 + ROPE_LENGTH + BODY_LENGTH + LEG_LENGTH);
		add(gline);
	}

}

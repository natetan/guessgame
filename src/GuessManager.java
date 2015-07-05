/*
 * Yulong Tan
 * July 4th, 2015
 * 
 * This class manages a guess game with the user, where they can
 * set the range themselves via input. 
 */

import java.util.Random;
import java.util.Scanner;

public class GuessManager {
	private Random r;
	private int number;
	private int totalGuesses;
	private int games;
	private int min;

	// Constructs a new game and keeps track of total guesses and total games
	public GuessManager() {
		this.r = new Random();
		this.totalGuesses = 0;
		this.games = 1;
		this.min = 999999999;
	}

	// This method takes an int as a parameter that represents the max value
	// as indicated by the user and returns a number randomly from the range of 0
	// to the parameter
	public int getNumber(int max) {
		this.number = this.r.nextInt(max) + 1;
		return this.number;
	}

	// This method takes an int guess from the user and returns a String telling the
	// user if the actual number was higher or lower than their guess
	public String proximity(int guess) {
		if (guess > this.number) {
			return "It's lower";
		} else {
			return "It's higher";
		}
	}

	// Returns a String representation of the user's stats, which includes the total guesses,
	// total games, average guesses per game, and best game (least guesses)
	public String getStats() {
		return "Here are your stats:\n" +
				"\nTotal games: " + this.games + "\nTotal guesses: " + this.totalGuesses +
				"\nAverage guesses per game: " + (double)this.totalGuesses / this.games +
				"\nBest game: " + this.min;
	}

	// This method does the interacting with the user. It takes an int as a parameter and 
	// plays the game based on their guess. At the end, it asks if the user wants to play
	// again. 
	public void play(int max) {
		Scanner console = new Scanner(System.in);
		int guesses = 1;
		int number = this.getNumber(max);
		System.out.println("I'm thinking of a number between 0 (exclusive) and " 
				+ max + " (inclusive)");
		// System.out.println("The number is " + number);
		System.out.print("What's your guess? ");
		int guess = console.nextInt();
		while (guess != number) {
			guesses++;
			System.out.println(this.proximity(guess));
			System.out.print("Guess again: ");
			guess = console.nextInt();
		}
		this.totalGuesses += guesses;
		if (guesses < min) {
			this.min = guesses;
		}
		this.checkGuess(guesses);
		System.out.println();
		System.out.print("Do you want to play again? (y/n)? ");
		String response = console.next().toLowerCase();
		if (response.startsWith("y")) {
			this.games++;
			this.play(max);
		} else {
			System.out.println();
			System.out.println(this.getStats());
		}
	}

	// This is a privtae helper method that really just checks if they got it in one guess or not. 
	// It either prints out the singular or plural form of guesses. 
	private void checkGuess(int guesses) {
		if (guesses == 1) {
			System.out.println("You got it right in 1 guess!");
		} else {
			System.out.println("You got it right in " + guesses + " guesses!");
		}
	}
}

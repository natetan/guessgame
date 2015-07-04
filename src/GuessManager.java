import java.util.Random;
import java.util.Scanner;

public class GuessManager {
	private Random r;
	private int number;
	private int totalGuesses;
	private int games;
	
	public GuessManager() {
		this.r = new Random();
		this.totalGuesses = 0;
		this.games = 1;
	}
	
	public int getNumber(int max) {
		this.number = this.r.nextInt(max) + 1;
		return this.number;
	}
	
	public String proximity(int guess) {
		if (guess > this.number) {
			return "It's lower";
		} else {
			return "It's higher";
		}
	}
	
	public String getStats() {
		return "Total guesses: " + this.totalGuesses + "\nTotal games: " + this.games +
				"\nAverage guesses per game: " + (double)this.totalGuesses / this.games;
	}
	
	private void checkGuess(int guesses) {
		if (guesses == 1) {
			System.out.println("You got it right in 1 guess!");
		} else {
			System.out.println("You got it right in " + guesses + " guesses!");
		}
	}
	
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
}

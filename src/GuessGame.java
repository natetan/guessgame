import java.util.Scanner;

public class GuessGame {

	public static void main(String[] args) {
		GuessManager manager = new GuessManager();
		Scanner console = new Scanner(System.in);

		System.out.println("This program plays a guessing game with the user!");
		System.out.println("What range do you want the numbers from?");
		System.out.println();
		System.out.print("0 to what? ");
		int max = console.nextInt();
		manager.play(max);;
	}
}

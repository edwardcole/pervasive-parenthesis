import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Pervasive Parentheses!");
        System.out.println(
                "Please enter a command (e (3) evaluates (3), g 20 generates an expression with value 20, q quits):");
        for (;;) {
            String text = scanner.nextLine();
            if (text.charAt(0) == 'q') {
                break;
            }
            switch (text.charAt(0)) {
                case 'q':
                    break;
                case 'e': {
                    Pervasive parsedText = Pervasive.parseInput(text);
                }
                case 'g': {

                }
            }

        }
        System.out.println("Thank you for using Pervasive Parentheses!");
    }
}

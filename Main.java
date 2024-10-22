import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    // sigby
    public static void main(String[] args) {
        System.out.println("Welcome to Pervasive Parentheses!");
        System.out.println(
                "Please enter a command (e (3) evaluates (3), g 20 generates an expression with value 20, q quits):");
        while (true) {
            String text = scanner.nextLine();
            if (text.charAt(0) == 'q') {
                break;
            }
            switch (text.charAt(0)) {
                case 'q':
                    break;
                case 'e':
                    System.out.println("This expression evaluates to: " + Pervasive.evaluate(text));
                case 'g':
                    System.out.println("This number can be generated as: " + Pervasive.generate(text));
                case 'v':
                    System.out.println("The validity of this expression is: " + Pervasive.validate(text));
            }

        }
        System.out.println("Thank you for using Pervasive Parentheses!");
    }
}

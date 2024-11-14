import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static int input(String text) {
        if (text.charAt(0) == 'q') {
            return -1;
        }
        if (text.charAt(0) == 'e')
            System.out.println("This expression evaluates to: " + Pervasive.evaluate(text));
        else if (text.charAt(0) == 'g')
            System.out.println("This number can be generated as: " + Pervasive.generate(text));
        else if (text.charAt(0) == 'v') {
            Validation valid = Pervasive.validate(text);
            System.out.println("The validity of this expression is: " + valid.valid);
            if (!valid.valid) {
                System.out.println(valid.invalidationReason);
            }
        } else
            System.out.println("Please enter a valid expression!");
        return 0;
    }

    // sigby
    public static void main(String[] args) {
        System.out.println("Welcome to Pervasive Parentheses!");
        System.out.println(
                "Please enter a command (e (3) evaluates (3), g 20 generates an expression with value 20, v (3) says the validity of the expression, and q quits):");
        while (true) {
            String text = scanner.nextLine();
            int ret = input(text);
            if (ret == -1) {
                // -1 is like an error code or stop code
                break;
            }
        }
        System.out.println("Thank you for using Pervasive Parentheses!");
    }
}

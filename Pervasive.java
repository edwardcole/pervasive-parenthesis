import java.util.ArrayList;

public class Pervasive {
    static ArrayList<Integer> getFactors(int num) {
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 0; i < num / 2; i++) {
            if (num % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }
    // comment 3
    static int evaluateArithmetic(String input) {
        String inputWhitespace = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ') {
                inputWhitespace.concat(String.valueOf(input.charAt(i)));
            }
        }

        String operation = "";
        int param1 = -1;
        int output = 0;

        for (int i = 0; i < inputWhitespace.length(); i++) {
            try {
                int num = Integer.parseInt(String.valueOf(inputWhitespace.charAt(i)));

                if (!operation.equals("") && param1 != -1) {

                    if (operation.equals("+")) {
                        output += param1 + num;
                        operation = "";
                        param1 = -1;
                    }

                } else {
                    param1 = Integer.parseInt(String.valueOf(inputWhitespace.charAt(i)));
                    // if operation & param1 are null then set param1 to it
                }
            } catch (NumberFormatException e) {
                // then it is an operation
                operation = String.valueOf(inputWhitespace.charAt(i));
            }
        }
        return output;
    }

    static ArrayList<Integer> getIndexes(String haystack, String needle) {
        ArrayList<Integer> output = new ArrayList<>();
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                output.add(i);
            }
        }
        return output;
    }

    static int parseParens(String parens) {
        // example "(14(5))"
        int output = 0;
        for (int i = 1; i < parens.length(); i++) { // start for loop at 1 to not immediately end the parseparens at (
            if (parens.charAt(i) == '(') {

            }
        }
        return output;
    }

    static Pervasive parseInput(String text) {
        // example e (14)(2)3(((7)))
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(') {
                String num = text.substring(i, text.indexOf(")" + 1));
                int completednum = 0;
                if (num.length() > 1) {

                    ArrayList<Integer> numlist = new ArrayList<>();
                    for (char c : num.toCharArray()) {
                        numlist.add(Integer.parseInt(String.valueOf(c)));
                    }
                    for (int digit : numlist) {
                        completednum += digit;
                    }
                }
                text = completednum + text.substring(text.indexOf(")") + 1);
            }
        }
    }
    // comment
    static boolean validate(String str) {
        int openParens = 0;
        int closeParens = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                openParens += 1;
            } else if (c == ')') {
                closeParens += 1;
            }
        }
        return openParens == closeParens;
    }

    static String generate() {

    }

    public int evaluate() {

    }

    Pervasive(ArrayList<String> inputs) {

    }
}

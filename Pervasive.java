import java.util.ArrayList;

public class Pervasive {

    public static int index(String s, int iter, String needle) {
        int index = -1;
        for (int i = 0; i < iter && s.indexOf(needle) != -1; i++) {
            if (index == -1)
                index = 0;
            if (s.indexOf(needle) == -1)
                return -1;
            index = index + s.indexOf(needle);
            s = s.substring(s.indexOf(needle));
        }
        return index;
    }

    public static int lastIndex(String s, int iter, String needle) {
        int index = -1;
        for (int i = 0; i < iter && s.lastIndexOf(needle) != -1; i++) {
            if (index == -1)
                index = 0;
            if (s.lastIndexOf(needle) == -1)
                return -1;
            index = index + s.lastIndexOf(needle);
            s = s.substring(s.lastIndexOf(needle));
        }
        return index;
    }

    static Validation validate(String str) {
        str = str.substring(str.indexOf("v") + 2);
        // get rid of the "v " at the beginning of validate functions
        int openParens = 0;
        int closeParens = 0;

        for (char c : str.toCharArray()) {
            // loop through str to measure parentheses and catching any invalid characters
            if (c == '(')
                openParens += 1;
            else if (c == ')')
                closeParens += 1;
            else {
                try {
                    Integer.parseInt(String.valueOf(c));
                } catch (NumberFormatException e) {
                    return new Validation(false, "Invalid character(s)");
                }
            }
        }

        if (openParens != closeParens)
            return new Validation(false, "Extra open/close parenthesis");

        return new Validation(true, "");
        // it passed :)
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static String generateFromInt(int num) {
        String result = "";
        int parens = 0;
        int counter = num;
        if (!isEven(counter)) {
            counter -= 9;
            result += "9" + generateFromInt(counter);
        } else {
            if (num / 10 != 0) {
                for (; counter % 2 == 0 && counter / 10 > 0 && counter + 9 > 9; counter /= 2) {
                    result += "(";
                    parens += 1;
                }
                if (counter <= 18) {
                    result += Integer.toString((counter - 9)) + 9;
                } else {
                    if (!isEven(counter)) {
                        if (counter / 10 <= 1) {
                            result += counter;
                        } else {
                            counter -= 9;
                            result += "9" + generateFromInt(counter);
                        }
                    } else {
                        result += counter;
                    }
                }
                for (int i = 0; i < parens; i++)
                    result += ")";

            } else
                result += num;
        }

        return result;
    }

    public static String generate(String text) {
        try {
            return generateFromInt(Integer.parseInt(text.substring(text.indexOf(" ") + 1)));
        } catch (NumberFormatException e) {
            return "Invalid number";
        }
    }

    public static int closeParens(String s, int start) {
        int status = 1;
        int i = start;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                status -= 1;
            } else if (c == '(') {
                status += 1;
            }
            if (status == 0) {
                return i;
            }
        }
        return -1;
    }

    public static int evalParens(String num) {
        int completednum = 0;
        // int used for storing the number to return
        int parenNum = 0;
        // just incase an expression is formatted like (123)(456)

        num = num.substring(1, num.length() - 1);
        // get rid of the parens
        if (num.length() >= 1) {

            for (int i = 0; i < num.length(); i++) {
                char c = num.charAt(i);
                // loop through the characters in the parens expression

                if (c == '(') {
                    // if its an open parentheses then go deeper into recursion hell
                    System.out.println("num: " + num);
                    System.out.println("call: " + num.substring(
                            num.indexOf("("),
                            num.lastIndexOf(")") + 1));
                    completednum += evalParens(num.substring(
                            num.indexOf("("),
                            num.lastIndexOf(")") + 1));
                    // call eval parens again on the parentheses expression
                    int sParensIndex = closeParens(num, i + 1);
                    if (sParensIndex != -1) {
                        num = num.substring(sParensIndex);
                        i = 0;
                        System.out.println("upd num: " + num);
                    } else {
                        return completednum * 2;
                    }
                    // skip to the end of the parens expression and remove the previous one
                } else {
                    if (c == ')') {
                        System.out.println(num);
                        continue;
                    }

                    completednum += Integer.parseInt(String.valueOf(c));
                }
            }

        }
        System.out.println("c: " + completednum * 2 + " | num: " + num);

        return completednum * 2;
        // apply the doubling operator cause You know thats what parentheses do
    }

    public static int evaluate(String text) {
        text = text.substring(text.indexOf("e") + 2);
        // get rid of "e " before everything

        Validation validation = validate("v " + text);
        if (!validation.valid) {
            System.err.println("Invalid expression | Error message: " + validation.invalidationReason
                    + " expression: " + text);
            return -1;
        }
        // validate text

        int val = 0;
        for (int i = 0; i < text.length(); i++) {
            // loop through every character in the string

            if (text.charAt(i) == '(') {

                int startPos = text.indexOf("(");
                int endPos = text.lastIndexOf(")") + 1;
                // get start and end of the parenthesis for substring

                String num = text.substring(startPos, endPos);
                // System.out.println("full: " + text);
                val += evalParens(num);
                // Start recursion hell (a.k.a evaluate the parentheses...)

                i = text.substring(i).lastIndexOf(")") + 1;
                // skip to the latest index of ) to get out of the parens
            } else if (text.charAt(i) == ')')
                continue;
            else {
                int num = Integer.parseInt(String.valueOf(text.charAt(i)));
                // parse the character as a number
                // System.out.println("Adding number " + num + " from " + text.substring(i));
                val += num; // if not in parens then just add the number
            }
        }
        return val;
    }
}

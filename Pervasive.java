public class Pervasive {

    static Validation validate(String str) {
        str = str.substring(str.indexOf("v") + 2);
        // get rid of the "v " at the beginning of validate functions
        int balance = 0;

        for (char c : str.toCharArray()) {
            // loop through str to measure parentheses and catching any invalid characters
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
                if (balance < 0) {
                    return new Validation(false, "Mismatched Parentheses");
                }
            } else {
                try {
                    Integer.parseInt(String.valueOf(c));
                } catch (NumberFormatException e) {
                    return new Validation(false, "Invalid Characters");
                }
            }

        }
        if (balance != 0) {
            return new Validation(false, "Extra Open/Close Parentheses");
        }

        return new Validation(true, "Valid Expression");
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static String generateFromInt(int num) {
        String result = "";
        int parens = 0;
        int counter = num;
        if (!isEven(counter)) {
            counter -= 1;
            result += "1" + generateFromInt(counter);
            // as said in the lang exploration doc, it'll subtract by 1 and add 1 at the end
        } else {
            if (num / 10 != 0) {
                for (; counter % 2 == 0 && counter / 10 > 0 && counter + 9 > 9; counter /= 2) {
                    result += "(";
                    parens += 1;
                    // loop that just divides counter by two while it's even every iteration and
                    // checks if its within 1-18 for a shortcut
                }
                if (counter <= 18) {
                    if (counter <= 9) {
                        result += counter;
                    } else {
                        result += Integer.toString((counter - 9)) + 9;
                    }
                    // this just divides 18 into 9 plus the number minus nine
                    // so like 17 would be 9 + 6 since 6 is 17 - 9
                } else {
                    if (!isEven(counter)) {
                        counter -= 1;
                        result += "1" + generateFromInt(counter);
                        // same as initial
                    } else {
                        result += generateFromInt(counter);
                    }
                }
                for (int i = 0; i < parens; i++)
                    result += ")";
                // add the parens in

            } else
                result += num;
        }

        return result;
    }

    public static String generate(String text) {
        try {
            return generateFromInt(Integer.parseInt(text.substring(text.indexOf(" ") + 1)));
            // fancy substring to remove the 'g ' from the start
        } catch (NumberFormatException e) {
            return "Invalid number";
        }
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
        while (true) {
            int parensNum = 0;
            int i = text.indexOf(")");
            if (i == -1)
                break;
            i -= 1;
            while (text.charAt(i) != '(') {
                // so it just goes to the first end parens and goes backwards until the first
                // open paren
                char c = text.charAt(i);
                if (c == '`') {
                    // ` just means true number and not pervasive number. so like `4` is 22
                    int innerIndex = i;
                    i -= 1;
                    while (text.charAt(i) != '`') {
                        i -= 1;
                    }
                    parensNum += Integer.parseInt(text.substring(i + 1, innerIndex));
                    i -= 1;
                }
                c = text.charAt(i);
                if (text.charAt(i) == '(')
                    break;
                parensNum += Integer.parseInt(String.valueOf(c));
                i--;
            }
            text = text.substring(0, i) + "`" + parensNum * 2 + "`" + text.substring(text.indexOf(")") + 1);
            // convty parens to a true expression
        }
        for (int i = 0; i < text.length() && i < text.length(); i++) {
            while (i < text.length() && text.charAt(i) == '`') {
                // code to evaluate all of the backticks
                int innerIndex = i;
                i += 1;
                while (text.charAt(i) != '`') {
                    i++;
                }
                val += Integer.parseInt(text.substring(innerIndex + 1, i));
                // + 1 at beginning cause it goes backwards
                i += 1;
            }
            if (i >= text.length())
                return val;

            val += Integer.parseInt(String.valueOf(text.charAt(i)));
        }
        return val;
    }
}

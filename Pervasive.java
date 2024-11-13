public class Pervasive {

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
            counter -= 1;
            result += "1" + generateFromInt(counter);
        } else {
            if (num / 10 != 0) {
                for (; counter % 2 == 0 && counter / 10 > 0 && counter + 9 > 9; counter /= 2) {
                    result += "(";
                    parens += 1;
                }
                if (counter <= 18) {
                    if (counter <= 9) {
                        result += counter;
                    } else {
                        result += Integer.toString((counter - 9)) + 9;
                    }
                } else {
                    if (!isEven(counter)) {
                        counter -= 1;
                        result += "1" + generateFromInt(counter);
                    } else {
                        result += generateFromInt(counter);
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
                char c = text.charAt(i);
                if (c == '`') {
                    int innerIndex = i;
                    i -= 1;
                    while (text.charAt(i) != '`') {
                        i--;
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
        }
        for (int i = 0; i < text.length() && i < text.length(); i++) {
            while (i < text.length() && text.charAt(i) == '`') {
                int innerIndex = i;
                i += 1;
                while (text.charAt(i) != '`') {
                    i++;
                }
                val += Integer.parseInt(text.substring(innerIndex + 1, i));
                i += 1;
            }
            if (i >= text.length())
                return val;

            val += Integer.parseInt(String.valueOf(text.charAt(i)));
        }
        return val;
    }
}

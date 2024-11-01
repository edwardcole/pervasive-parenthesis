import java.util.ArrayList;

public class Pervasive {
    static ArrayList<Integer> getFactors(int num) {
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 0; i < num / 2; i++)
            if (num % i == 0)
                factors.add(i);

        return factors;
    }

    // comment
    static Validation validate(String str) {
        int openParens = 0;
        int closeParens = 0;
        for (char c : str.toCharArray()) {
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
    }

    static ArrayList<Integer> getPrimeFactors(int num) {
        if (num < 0)
            return new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>(num);

        int counter = num;
        if (counter % 2 == 0)
            for (; counter % 2 == 0; counter /= 2)
                result.add(2);

        for (int i = 3; i < Math.sqrt(counter); i++)
            for (; counter % i == 0; counter /= i)
                result.add(counter);

        if (num <= 2) {
            result.add(num);
            result.add(1);
        }
        return result;
    }

    public static ArrayList<Integer> getAdditivesOfPrime(int num) {
        ArrayList<Integer> result = new ArrayList<>();
        for (; num - 9 > 0; num -= 9)
            result.add(9);
        result.add(num);
        return result;
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static String generateFromInt(int num) {
        String result = "";
        int parens = 0;
        int counter = num;
        if (!isEven(counter)) {
            counter--;
            result += "1" + generateFromInt(counter);
        } else {
            if (num / 10 != 0) {
                for (; counter % 2 == 0 && counter / 10 > 0; counter /= 2) {
                    result += "(";
                    parens += 1;
                }
                if (!isEven(counter)) {
                    if (counter / 10 <= 1) {
                        result += counter;
                    } else {
                        counter--;
                        result += "1" + generateFromInt(counter);
                    }
                } else {
                    result += counter;
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

    private static int getParenthesesAtPosition(String str, int startParensIndex) {
        int index = 0;
        int iter = 0;
        for (int i = str.length(); i > 0 && iter < startParensIndex; i--) {
            int lastIndex = str.lastIndexOf(")");
            if (lastIndex != -1) {
                i = lastIndex;
                iter++;
                str = str.substring(0, lastIndex - 1);
            } else {
                return index;
            }
        }
        return index;
    }

    private static int getStartingParensAtPosition(String str, int startParensIndex) {
        int index = 0;
        int iter = 0;
        for (int i = 0; i < str.length() && iter < startParensIndex; i--) {
            int lastIndex = str.lastIndexOf("(");
            if (lastIndex != -1) {
                i = lastIndex;
                iter++;
                str = str.substring(0, lastIndex - 1);
            } else {
                return index;
            }
        }
        return index;
    }

    public static int evalParens(String num, String full, int startParensIndex) {

        int completednum = 0;
        if (num.length() > 1) {

            ArrayList<Integer> numlist = new ArrayList<>();
            int i = 0;
            for (char c : num.substring(0, num.indexOf(")")).toCharArray()) {
                if (c == '(')
                    if (i > 0)
                        completednum += evalParens(num.substring(i, num.substring(i).indexOf(')') + 1), full,
                                startParensIndex + 1);
                    else
                        continue;

                System.out.println(c);
                numlist.add(Integer.parseInt(String.valueOf(c)));
                i++;
            }

            // loops through the
            for (int digit : numlist)
                completednum += digit;

        }
        return completednum;
    }

    public static int evaluate(String text) {
        text = text.substring(text.indexOf("e") + 2);
        if (!validate(text).valid) {
            return -1;
        }
        int val = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(') {
                String num = text.substring(i, text.substring(i).indexOf(")") + 1);
                i = text.substring(i).indexOf(")") + 1;
                val += evalParens(num, text, getParenthesesAtPosition(text, 1));
            } else if (text.charAt(i) == ')')
                continue;
            else
                val += Integer.parseInt(String.valueOf(text.charAt(i))); // if not in parens then just add the number

        }
        return val;
    }
}

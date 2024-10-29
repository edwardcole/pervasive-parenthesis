import java.util.ArrayList;

public class Pervasive {
    static ArrayList<Integer> getFactors(int num) {
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 0; i < num / 2; i++) {
            if (num % i == 0)
                factors.add(i);
        }
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
        if (openParens != closeParens) {
            return new Validation(false, "Extra open/close parenthesis");
        }
        return new Validation(true, "");
    }

    static ArrayList<Integer> getPrimeFactors(int num) {
        if (num < 0)
            return new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>(num);

        int counter = num;
        if (counter % 2 == 0) {
            for (; counter % 2 == 0; counter /= 2)
                result.add(2);
        }

        for (int i = 3; i < Math.sqrt(counter); i++) {
            for (; counter % i == 0; counter /= i)
                result.add(counter);
        }

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
        return num / 2 == (int) (num / 2.0);
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
                for (; counter % 2 == 0 || counter / 10 == 0; counter /= 2) {
                    result += "(";
                    parens += 1;
                }
                for (int i = 0; i < parens; i++) {
                    result += ")";
                }
            } else {
                result += num;
            }
        }

        return result;
    }

    public static String generate(String text) {
        try {
            return generateFromInt(Integer.parseInt(text.substring(text.indexOf(" "))));
        } catch (NumberFormatException e) {
            return "Invalid number";
        }
    }

    public static int evaluate(String text) {
        text = text.substring(text.indexOf("e") + 1);
        int val = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(') {
                String num = text.substring(i, text.indexOf(")" + 1));
                int completednum = 0;
                if (num.length() > 1) {

                    ArrayList<Integer> numlist = new ArrayList<>();
                    for (char c : num.toCharArray()) {
                        numlist.add(Integer.parseInt(String.valueOf(c)));
                    }
                    // loops through the
                    for (int digit : numlist) {
                        completednum += digit;
                    }
                }
                val += completednum * 2; // times two because of parens
            } else {
                val += Integer.parseInt(String.valueOf(text.charAt(i))); // if not in parens then just add the number
            }
        }
        return val;
    }
}

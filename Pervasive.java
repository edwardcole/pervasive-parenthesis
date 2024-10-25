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

    // comment
    static Validation validate(String str) {
        int openParens = 0;
        int closeParens = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                openParens += 1;
            } else if (c == ')') {
                closeParens += 1;
            } else {
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
        if (num < 0) {
            return new ArrayList<>();
        }
        ArrayList<Integer> result = new ArrayList<>(num);
        int counter = num;
        if (counter % 2 == 0) {
            while (counter % 2 == 0) {
                counter /= 2;
                result.add(num);
            }
        }
        for (int i = 3; i < Math.sqrt(counter); i++) {
            while (counter % i > 0) {
                counter /= i;
                result.add(counter);
            }
        }
        if (num <= 2) {
            result.add(num);
            result.add(1);
        }
        return result;
    }

    public static ArrayList<Integer> getAdditivesOfPrime(int num) {

    }

    public static String generateFromInt(int num) {
        String result = "";
        ArrayList<Integer> factors = getPrimeFactors(num);

    }

    public static String generate(String text) {
        try {
            return generateFromInt(Integer.parseInt(text));
        } catch (NumberFormatException e) {
            return "Invalid number";
        }
    }

    public static int evaluate(String text) {
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

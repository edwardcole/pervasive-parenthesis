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
        str = str.substring(str.indexOf("v") + 2);
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
                if (counter + 9 > 9) {
                    result += (counter - 9) + 9;
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
                }
            } else {
                result += num;
            }
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
        int index = -1;
        int iter = 0;
        for (int i = str.length(); i > 0 && iter < startParensIndex; i--) {
            int lastIndex = str.lastIndexOf(")");
            if (lastIndex != -1) {
                i = lastIndex;
                index = i;
                iter++;
                str = str.substring(0, lastIndex - 1);
            } else {
                return index;
            }
        }
        return index;
    }

    private static int getStartingParensAtPosition(String str, int startParensIndex) {
        int index = -1;
        int iter = 0;
        for (int i = 0; i < str.length() && iter < startParensIndex; i--) {
            int lastIndex = str.indexOf("(");
            if (lastIndex != -1) {
                i = lastIndex;
                index = i;
                iter++;
                str = str.substring(lastIndex + 1);
            } else {
                return index;
            }
            // System.out.println(iter + " iter");
        }
        return index;
    }

    public static int evalParens(String num) {
        int completednum = 0;
        num = num.substring(1, num.length() - 1);
        if (num.length() >= 1) {
            ArrayList<Integer> numlist = new ArrayList<>();
            for (int i = 0; i < num.length(); i++) {
                char c = num.charAt(i);
                if (c == '(') {
                    int valueOfParens = evalParens(
                            num.substring(
                                    getStartingParensAtPosition(num, 1),
                                    getParenthesesAtPosition(num, 1) + 1));
                    completednum += valueOfParens;
                    i = getParenthesesAtPosition(num, 1);
                } else {
                    numlist.add(Integer.parseInt(String.valueOf(c)));
                }
            }

            // loops through the
            for (int digit : numlist)
                completednum += digit;

        }
        System.out.println("c: " + completednum + " | num: " + num);
        return completednum * 2;
    }

    public static int evaluate(String text) {
        text = text.substring(text.indexOf("e") + 2);
        if (!validate("v " + text).valid) {
            return -99;
        }
        int val = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(') {
                int startPos = text.indexOf("(");
                int endPos = text.lastIndexOf(")") + 1;
                String num = text.substring(startPos, endPos);
                i = text.substring(i).indexOf(")") + 1;
                System.out.println("full: " + text);
                val += evalParens(num);
            } else if (text.charAt(i) == ')')
                continue;
            else
                val += Integer.parseInt(String.valueOf(text.charAt(i))); // if not in parens then just add the number
            }
        }
        return val;
    }
}

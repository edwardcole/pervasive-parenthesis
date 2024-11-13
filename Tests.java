public class Tests {
    static int numCorrect;

    static void testEvaluate(String expression, int expected) {
        int actual;
        try {
            actual = Pervasive.evaluate(expression);
        } catch (Exception e) {
            System.out.print("⚠️ Exception raised: " + e);
            actual = -1;
            System.out.println("⚠️ Exception raised: " + e);
        }
        if (actual == expected) {
            System.out.println("✅ Test passed");
        } else {
            System.out.println("⛔ Test failed. Expected: " + expected + ", got: " + actual);
        }
    }

    static void testValidate(String expression, boolean expected) {
        Validation actual = new Validation(false,"");
        try {
            actual = Pervasive.validate(expression);
        } catch (Exception e) {
            System.out.print("⚠️ Exception raised: " + e);
            actual.valid = false;
            System.out.println("⚠️ Exception raised: " + e);
        }
        if (actual.valid == expected) {
            System.out.println("✅ Test passed");
        } else {
            System.out.println("⛔ Test failed. Expected: " + expected + ", got: " + actual);
        }
    }

    public static void main(String[] args) {
        testValidate("e 1(", false);
        testValidate("(/)", false);
        testValidate("", true);
        testValidate("(1)3", true);
        testValidate(")1(", false);
        testValidate("(()1)", false);
        testValidate("(((((1)))))", false);
        
    }
} 
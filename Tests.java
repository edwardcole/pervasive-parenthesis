public class Tests {
    static int numCorrect;

    static void testEvaluate(String expression, int expected) {
        int actual = Pervasive.evaluate(expression);
        if (actual == expected) {
            System.out.println(
                    "✅ Test passed. expression: " + expression + " expected: " + expected + ", got: " + actual);
        } else {
            System.out.println(
                    "⛔ Test failed. expression: " + expression + " expected: " + expected + ", got: " + actual);
        }
    }

    static void testValidate(String expression, boolean expected) {
        Validation actual = new Validation(false, "");
        try {
            actual = Pervasive.validate(expression);
        } catch (Exception e) {
            System.out.print("⚠️ Exception raised: " + e);
            actual.valid = false;
            System.out.println("⚠️ Exception raised: " + e);
        }
        if (actual.valid == expected) {
            System.out.println(
                    "✅ Test passed. expression: " + expression + " expected: " + expected + ", got: " + actual);
        } else {
            System.out.println(
                    "⛔ Test failed. expression: " + expression + " expected: " + expected + ", got: " + actual.valid);
        }
    }

    static void testGenerate(int exp) {
        String gen = "e " + Pervasive.generateFromInt(exp);
        int eval = Pervasive.evaluate(gen);
        if (eval == exp) {
            System.out.println("✅ Test passed. generate: " + gen + ", eval: " + eval + ", entered: " + exp);
        } else {
            System.out.println("⛔ Test failed. generate: " + gen + ", eval: " + eval + ", entered: " + exp);
        }
    }

    System.out.println("⛔ Test failed. Expected: "+expected+", got: "+actual);

    }}

    public static void main(String[] args) {
        // testValidate("e 1(", false);
        // testValidate("(/)", false);
        // testValidate("", true);
        // testValidate("(1)3", true);
        // testValidate("(()1)", false);
        // testValidate("(((((1)))))", false);

        testEvaluate("e 1(2(3(4(5)6)7)8)9", 230);
        // testEvaluate("e (((((5)))))", 160);
        testEvaluate("e (1)(2)(3)", 12);
        testGenerate(1);
        testGenerate(10);
        testGenerate(100);
        testGenerate(1000);
        testGenerate(10000);
        testGenerate(100000);

        testValidate("e 1(", false);
        testValidate("(/)", false);
        testValidate("", true);
        testValidate("(1)3", true);
        testValidate(")1(", false);
        testValidate("(()1)", false);
        testValidate("(((((1)))))", false);

    }
}

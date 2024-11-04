public class Tests {
    static int numCorrect;

    static void testEvaluate(String expression, int expected) {
        try {
            Pervasive.evaluate(expression);
        } catch (Exception e) {
            System.out.println("⚠️ Exception raised: " + e);
        }
        int actual = Pervasive.evaluate(expression);

        if (actual == expected) {
            System.out.println("✅ Test passed");
        } else {
            System.out.println("⛔ Test failed. Expected: " + expected + ", got: " + actual);
        }
    }

    static void testInput(String input) {

    }

    public static void main(String[] args) {
        testEvaluate("e (1)3", 5);
        testEvaluate("e ", 0);
        testEvaluate("e (1(2(3(4)5)6)7)8", 184);
        testEvaluate("e (((1(2))4))6", 62);
    }
}

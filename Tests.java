public class Tests {
    static int numCorrect;

    static void testEvaluate(String expression, int expected) {
        try {
            Pervasive.evaluate(expression);
        } catch (Exception e) {
            System.out.print("⚠️ Exception raised: " + e);
        }
        int actual = Pervasive.evaluate(expression);
        if (actual == expected) {
            System.out.print("✅ Test passed");
        } else {
            System.out.print("⛔ Test failed. Expected: " + expected + "Got: " + actual);
        }
    }

    public static void main(String[] args) {
        testEvaluate("(1)3", 5);
        testEvaluate("", 0);
        testEvaluate("(1(2(3(4)5)6)7)8", 184);
        testEvaluate("(((1(2))4))6", 62);
    }
}

static void testInput(String input) {

}

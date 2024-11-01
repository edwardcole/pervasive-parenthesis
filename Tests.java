public class Tests {
    static int numCorrect;

    static void testEvaluate(String expression, int expected) {
        try {
            Pervasive.evaluateArithmetic(expression);
        } catch (Exception e) {
            System.out.print("⚠️ Exception raised: " + e);
        }
        int actual = Pervasive.evaluateArithmetic(expression);
        if (actual == expected) {
            System.out.print("✅ Test passed");
        } else {
            System.out.print("⛔ Test failed. Expected: " + expected + "Got: " + actual);
        }
    }

    public static void main(String[] args) {
        testEvaluate("(1)3", 5);
        testEvaluate("", 0);
        String f = "";
        for(int i = 0; i < 1000; i++){
            f += 1;
        }
        testEvaluate(f, 1000);

    }
}

static void testInput(String input) {

}

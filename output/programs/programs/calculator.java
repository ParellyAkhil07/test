
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Stack;

public class Calculator {

    public static double evaluate(String expression) {
        expression = expression.replaceAll(" ", "");
        Stack<Integer> parensIndices = new Stack<>();
        StringBuilder storage = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char charAt = expression.charAt(i);
            if (charAt == '(') {
                parensIndices.push(i);
            } else if (charAt == ')') {
                int start = parensIndices.pop() + 1;
                double total = calculate(expression.substring(start, i));
                storage.setLength(start - 1);
                storage.append(total);
            } else {
                storage.append(charAt);
            }
        }
        return calculate(storage.toString());
    }

    private static double calculate(String innerExpression) {
        // Handle multiplication and division first
        innerExpression = processOperators(innerExpression, '*', '/');
        // Handle addition and subtraction next
        innerExpression = processOperators(innerExpression, '+', '-');
        return Double.parseDouble(innerExpression);
    }

    private static String processOperators(String expression, char op1, char op2) {
        StringBuilder newExpr = new StringBuilder();
        double total = 0;
        int i = 0;
        while (i < expression.length()) {
            char charAt = expression.charAt(i);
            if (charAt == op1 || charAt == op2) {
                double prev = Double.parseDouble(newExpr.toString());
                newExpr.setLength(0);
                i++;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    newExpr.append(expression.charAt(i));
                    i++;
                }
                double next = Double.parseDouble(newExpr.toString());
                newExpr.setLength(0);
                total = (charAt == op1) ? prev * next : prev / next;
                newExpr.append(total);
            } else {
                newExpr.append(charAt);
                i++;
            }
        }
        return newExpr.toString();
    }

    public static class Testing {

        @Test
        public void testNoParens() {
            assertEquals(7.0, evaluate("1 + 3 * 2"), 0.001);
        }

        @Test
        public void testSingleParens() {
            assertEquals(16.0, evaluate("(1 + 7) * 2"), 0.001);
        }

        @Test
        public void testOperationsOrder() {
            assertEquals(29.0, evaluate("4 * (1 + 7) - 3"), 0.001);
        }

        @Test
        public void testLongParens() {
            assertEquals(-6.0, evaluate("(4 - 7 - 1) * 3"), 0.001);
        }

        @Test
        public void testDecimals() {
            assertEquals(6.25, evaluate("(1.2 + 1.3) * 2.5"), 0.001);
        }
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("Calculator$Testing");
    }
}

import static org.junit.Assert.assertEquals;
import java.util.HashMap;

public class ClimbStairs {

    // Recursive method to calculate the number of ways to climb stairs
    public static int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2) + climbStairs(n - 3);
    }

    // Dynamic programming with memoization
    public static int climbStairsDP(int n) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        cache.put(1, 1);
        cache.put(2, 2);

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        cache.put(n, climbStairs(n - 1) + climbStairs(n - 2) + climbStairs(n - 3));
        return cache.get(n);
    }

    // Dynamic programming with tabulation
    public static int climbStairsTab(int n, int[] steps) {
        int[] result = new int[n + 1];
        result[0] = 1;

        for (int i = 0; i < steps.length; i++) {
            for (int j = steps[i]; j <= n; j++) {
                int sum = 0;
                for (int k = 0; k <= i; k++) {
                    sum += result[j - steps[k]];
                }
                result[j] = sum;
            }
        }

        return result[n];
    }

    public static class Testing {

        @Test
        public void testClimbStairs() {
            assertEquals(24, climbStairs(6));
            assertEquals(81, climbStairs(8));
        }

        @Test
        public void testClimbStairsDP() {
            assertEquals(24, climbStairsDP(6));
            assertEquals(81, climbStairsDP(8));
        }

        @Test
        public void testClimbStairsTab() {
            assertEquals(24, climbStairsTab(6, new int[]{1, 2, 3}));
            assertEquals(14, climbStairsTab(10, new int[]{2, 3, 5}));
        }
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("ClimbStairs$Testing");
    }
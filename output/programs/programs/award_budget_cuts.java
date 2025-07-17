import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AwardBudgetCuts {

    public static double findGrantCap(double[] grantsArray, double newBudget) {
        // Sort the grants array in descending order
        Arrays.sort(grantsArray);
        for (int j = 0; j < grantsArray.length / 2; j++) {
            double temp = grantsArray[j];
            grantsArray[j] = grantsArray[grantsArray.length - 1 - j];
            grantsArray[grantsArray.length - 1 - j] = temp;
        }

        int i = grantsArray.length - 1;
        boolean flag = false;
        double cap = newBudget / grantsArray.length;

        while (!flag) {
            // Reduce the budget by the grants that are less than the current cap
            while (cap > grantsArray[i]) {
                newBudget -= grantsArray[i];
                i--;
            }

            // Calculate the new cap
            double newCap = newBudget / (i + 1);

            // Check if the cap has stabilized
            if (cap == newCap) {
                flag = true;
            }

            cap = newCap;
        }

        return cap;
    }

    public static class Testing {
        @Test
        public void testFindGrantCap() {
            assertEquals(47, findGrantCap(new double[]{2, 100, 50, 120, 1000}, 190), 0.01);
            assertEquals(1.5, findGrantCap(new double[]{2, 4}, 3), 0.01);
            assertEquals(1, findGrantCap(new double[]{2, 4, 6}, 3), 0.01);
            assertEquals(47, findGrantCap(new double[]{2, 100, 50, 120, 1000}, 190), 0.01);
            assertEquals(128, findGrantCap(new double[]{2, 100, 50, 120, 167}, 400), 0.01);
            assertEquals(23.8, findGrantCap(new double[]{21, 100, 50, 120, 130, 110}, 140), 0.01);
            assertEquals(211, findGrantCap(new double[]{210, 200, 150, 193, 130, 110, 209, 342, 117}, 1530), 0.01);
        }
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("AwardBudgetCuts$Testing");
    }
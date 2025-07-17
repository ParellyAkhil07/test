import java.util.Set;

public class AddToZero {

    /**
     * Given list of ints, return true if any two nums sum to 0.
     *
     * @param nums List of integers
     * @return true if any two numbers sum to zero, otherwise false
     */
    public static boolean addToZero(int[] nums) {
        // If the array has less than 2 elements, return false
        if (nums.length < 2) {
            return false;
        }

        // Use a HashSet to store the numbers for quick lookup
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        // Check if the negative of any number exists in the set
        for (int num : nums) {
            if (numSet.contains(-num)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(addToZero(new int[]{})); // false
        System.out.println(addToZero(new int[]{1})); // false
        System.out.println(addToZero(new int[]{1, 2, 3})); // false
        System.out.println(addToZero(new int[]{1, 2, 3, -2})); // true

        // Indicate all tests passed
        System.out.println("ALL TESTS PASSED!");
    }

    // Using binary search, find val in range 1-100. Return number of guesses.
    public static int binarySearch(int val) {
        // Ensure the value is between 1 and 100
        if (val <= 0 || val >= 101) {
            throw new IllegalArgumentException("Val must be between 1-100");
        }

        int numGuesses = 0;
        int guess = -1;
        int low = 0;
        int high = 101;

        // Continue searching until the guess matches the value
        while (guess != val) {
            numGuesses++;
            guess = (high - low) / 2 + low;

            if (guess > val) {
                high = guess;
            } else if (guess < val) {
                low = guess;
            }
        }

        return numGuesses;
    }

    public static void main(String[] args) {
        // Test cases to verify the binary search function
        assert binarySearch(50) == 1 : "Test case 1 failed";
        assert binarySearch(25) == 2 : "Test case 2 failed";
        assert binarySearch(75) == 2 : "Test case 3 failed";
        assert binarySearch(31) <= 7 : "Test case 4 failed";

        int maxGuesses = 0;
        for (int i = 1; i <= 100; i++) {
            maxGuesses = Math.max(maxGuesses, binarySearch(i));
        }
        assert maxGuesses == 7 : "Test case 5 failed";

        System.out.println("ALL TESTS PASSED");
    }
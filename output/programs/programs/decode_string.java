
public class DecodeString {

    // Decodes a string. A valid code is a sequence of numbers and letters,
    // always starting with a number and ending with letter(s).
    // Each number tells you how many characters to skip before finding a good letter.
    // After each good letter should come the next next number.
    public static String decode(String s) {
        // Runtime: O(n)
        // Spacetime: O(n)

        StringBuilder decoded = new StringBuilder();

        for (int l = 0; l < s.length(); l++) {
            try {
                // Try to parse the character as an integer
                int skip = Integer.parseInt(String.valueOf(s.charAt(l)));
                // Append the character at the calculated position to the decoded string
                decoded.append(s.charAt(skip + l + 1));
            } catch (NumberFormatException e) {
                // Continue if the character is not a number
                continue;
            }
        }

        return decoded.toString();
    }

    // Decodes a string using a recursive approach.
    public static String decode2(String s) {
        // Runtime: O(n)
        // Spacetime: O(n)

        return _decode2(s, new StringBuilder(), 0);
    }

    private static String _decode2(String s, StringBuilder decoded, int i) {
        if (i >= s.length()) {
            return decoded.toString();
        }

        char current = s.charAt(i);

        if (Character.isDigit(current)) {
            int skip = Character.getNumericValue(current);
            decoded.append(s.charAt(skip + i + 1));
        }

        return _decode2(s, decoded, i + 1);
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(decode("0h")); // Output: h
        System.out.println(decode("2abh")); // Output: h
        System.out.println(decode("0h1ae2bcy")); // Output: hey

        System.out.println(decode2("0h")); // Output: h
        System.out.println(decode2("2abh")); // Output: h
        System.out.println(decode2("0h1ae2bcy")); // Output: hey
    }
public class Fibonacci {
    int[] sequence;

    public void generate(int n) {
        sequence = new int[n];

        if (n > 0) sequence[0] = 0;
        if (n > 1) sequence[1] = 1;

        for (int i = 2; i < n; i++) {
            sequence[i] = sequence[i - 1] + sequence[i - 2];
        }
    }

    public void printSequence() {
        if (sequence == null) {
            System.out.println("No sequence generated yet.");
            return;
        }

        for (int num : sequence) {
            System.out.println(num);
        }
    }
}
// Save as MethodsPractice.java
public class MethodsPractice {

    /** Returns true if a is > 0. */
    public static boolean isPositive(int a) {
        return a > 0;
    }

    /** Returns the smallest of three ints. */
    public static int findLeast(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /** Returns the area of an equilateral triangle with side length a. */
    public static double triangle(int a) {
        if (a < 0) throw new IllegalArgumentException("side must be >= 0");
        return (Math.sqrt(3) / 4.0) * a * a;
    }

    /** Returns true if a and b share the same last digit (ignoring sign). */
    public static boolean shareLastDigit(int a, int b) {
        return Math.abs(a) % 10 == Math.abs(b) % 10;
    }

    /** Returns the sum of the decimal digits of n (works for any length, ignores sign). */
    public static int sumDigits(int n) {
        n = Math.abs(n);
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    /**
     * Interprets the digits of the given int as a binary number and returns its base-10 value.
     * e.g., toDecimal(1011) -> 11
     */
    public static int toDecimal(int binary) {
        // Optional: validate digits are only 0 or 1
        int check = Math.abs(binary);
        if (check == 0) return 0; // handle 0 quickly
        while (check > 0) {
            int d = check % 10;
            if (d != 0 && d != 1) {
                throw new IllegalArgumentException("Input must contain only 0s and 1s");
            }
            check /= 10;
        }
        // Use Java's base conversion
        return Integer.parseInt(String.valueOf(Math.abs(binary)), 2);
    }

    public static void main(String[] args) {
        // quick tests
        System.out.println(isPositive(5));        // true
        System.out.println(isPositive(-1));       // false

        System.out.println(findLeast(7, 2, 9));   // 2

        System.out.println(triangle(2));          // 1.732050807... (√3)

        System.out.println(shareLastDigit(27, 47));   // true
        System.out.println(shareLastDigit(-15, 25));  // true

        System.out.println(sumDigits(0));         // 0
        System.out.println(sumDigits(12345));     // 15
        System.out.println(sumDigits(-909));      // 18

        System.out.println(toDecimal(101));       // 5
        System.out.println(toDecimal(1011));      // 11
        System.out.println(toDecimal(1111));      // 15
    }
}
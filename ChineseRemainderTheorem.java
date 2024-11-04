package Code;
import java.util.Scanner;

public class ChineseRemainderTheorem {

    // Class to store the result of the Extended Euclidean Algorithm
    static class Result {
        int gcd;
        int x;
        int y;

        Result(int gcd, int x, int y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }
    }

    // Function to implement the Extended Euclidean Algorithm
    public static Result extendedEuclidean(int a, int b) {
        if (b == 0) {
            return new Result(a, 1, 0);
        }

        Result result = extendedEuclidean(b, a % b);
        int x = result.y;
        int y = result.x - (a / b) * result.y;

        return new Result(result.gcd, x, y);
    }

    // Function to compute the modular inverse of a mod m using the Extended Euclidean Algorithm
    public static int modInverse(int a, int m) {
        Result result = extendedEuclidean(a, m);
        if (result.gcd != 1) {
            throw new IllegalArgumentException("Inverse doesn't exist");
        }
        return (result.x % m + m) % m; // Ensure the inverse is positive
    }

    // Function to solve the system of congruences using the Chinese Remainder Theorem
    public static int chineseRemainderTheorem(int[] a, int[] m) {
        int product = 1; // Product of all moduli
        int n = a.length;

        // Compute the product of all moduli
        for (int i = 0; i < n; i++) {
            product *= m[i];
        }

        int result = 0;

        // Apply the Chinese Remainder Theorem
        for (int i = 0; i < n; i++) {
            int Mi = product / m[i];          // M_i = product / m_i
            int inverseMi = modInverse(Mi, m[i]);  // Inverse of M_i mod m_i
            result += a[i] * Mi * inverseMi;   // Add the contribution to the result
        }

        return result % product; // Return the result modulo the product
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of equations
        System.out.print("Enter the number of congruences: ");
        int n = scanner.nextInt();

        // Input the coefficients (a_i) and moduli (m_i)
        int[] a = new int[n];
        int[] m = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter a_" + (i+1) + ": ");
            a[i] = scanner.nextInt();

            System.out.print("Enter m_" + (i+1) + ": ");
            m[i] = scanner.nextInt();
        }

        // Solve using the Chinese Remainder Theorem
        int solution = chineseRemainderTheorem(a, m);

        // Display the solution
        System.out.println("The solution is: " + solution);
    }
}
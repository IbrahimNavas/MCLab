package Code;
import java.util.Scanner;

public class ExtendedEuclideanAlgorithm {

    // Class to store the result of the Extended Euclidean Algorithm
    static class Result {
        int gcd;  // Greatest common divisor
        int x;    // Coefficient for 'a'
        int y;    // Coefficient for 'b'
        
        Result(int gcd, int x, int y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }
    }

    // Function to implement the Extended Euclidean Algorithm
    public static Result extendedEuclidean(int a, int b) {
        if (b == 0) {
            // Base case: gcd(a, 0) = a, and the coefficients are (1, 0)
            return new Result(a, 1, 0);
        }

        // Recursive call
        Result result = extendedEuclidean(b, a % b);

        // Update the coefficients using the results of the recursion
        int x = result.y;
        int y = result.x - (a / b) * result.y;

        return new Result(result.gcd, x, y);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input two integers
        System.out.print("Enter the first number (a): ");
        int a = scanner.nextInt();

        System.out.print("Enter the second number (b): ");
        int b = scanner.nextInt();

        // Call the extended Euclidean algorithm
        Result result = extendedEuclidean(a, b);

        // Display the results
        System.out.println("GCD of " + a + " and " + b + " is: " + result.gcd);
        System.out.println("Coefficients x and y are: " + result.x + " and " + result.y);
        System.out.println("Verification: " + a + " * " + result.x + " + " + b + " * " + result.y + " = " + result.gcd);

        if (result.gcd == 1) {
            // The multiplicative inverse of a modulo b is x
            // Note: result.x might be negative, so we take it mod b to ensure it's positive
            int multiplicativeInverse = (result.x % b + b) % b;
            System.out.println("The multiplicative inverse of " + a + " modulo " + b + " is: " + multiplicativeInverse);
        } else {
            System.out.println(a + " has no multiplicative inverse modulo " + b);
        }
    }
}
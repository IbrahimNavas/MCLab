package Code;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PrimitiveRootVerification {

    // Function to compute (base^exp) % mod efficiently using modular exponentiation
    public static int modExp(int base, int exp, int mod) {
        int result = 1;
        base = base % mod; // Update base if it is more than mod
        while (exp > 0) {
            if ((exp % 2) == 1) { // If exp is odd, multiply base with result
                result = (result * base) % mod;
            }
            exp = exp >> 1; // Right shift exp (divide by 2)
            base = (base * base) % mod; // Square the base
        }
        return result;
    }

    // Function to check if g is a primitive root modulo n
    public static boolean isPrimitiveRoot(int g, int n) {
        // We need to check if g^k (mod n) gives all unique values from 1 to n-1
        Set<Integer> set = new HashSet<>();
        
        for (int k = 1; k < n; k++) {
            int value = modExp(g, k, n);
            // If the value is already present in the set, it's not a primitive root
            if (set.contains(value)) {
                return false;
            }
            set.add(value);
        }

        // Check if set contains all numbers from 1 to n-1
        return set.size() == (n - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a number (g): ");
        int g = scanner.nextInt();
        
        System.out.print("Enter modulo (n): ");
        int n = scanner.nextInt();
        
        if (isPrimitiveRoot(g, n)) {
            System.out.println(g + " is a primitive root modulo " + n);
        } else {
            System.out.println(g + " is not a primitive root modulo " + n);
        }
        
        scanner.close();
    }
}
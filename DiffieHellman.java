package Lab6_Theorem_Advanced;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.Random;

public class DiffieHellman {

    // Function to calculate (base^exponent) mod modulus using modular exponentiation
    public static BigInteger modExp(BigInteger base, BigInteger exponent, BigInteger modulus) {
        return base.modPow(exponent, modulus);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Public parameters (prime number p and base g)
        BigInteger p = new BigInteger("23");  // Prime number
        BigInteger g = new BigInteger("5");   // Base (generator)

        System.out.println("Public Parameters:");
        System.out.println("Prime p: " + p);
        System.out.println("Base g: " + g);

        // User A's private key and public key calculation
        System.out.print("Enter User A's private key (a): ");
        BigInteger a = scanner.nextBigInteger();
        BigInteger A = modExp(g, a, p);
        System.out.println("User A's public key (A): " + A);

        // User B's private key and public key calculation
        System.out.print("Enter User B's private key (b): ");
        BigInteger b = scanner.nextBigInteger();
        BigInteger B = modExp(g, b, p);
        System.out.println("User B's public key (B): " + B);

        // Both parties exchange public keys and compute the shared secret
        BigInteger sharedKeyA = modExp(B, a, p);
        BigInteger sharedKeyB = modExp(A, b, p);

        // The shared secret key should be the same for both parties
        System.out.println("User A's shared secret: " + sharedKeyA);
        System.out.println("User B's shared secret: " + sharedKeyB);

        // Verifying that both shared secrets are equal
        if (sharedKeyA.equals(sharedKeyB)) {
            System.out.println("Key exchange successful. Shared secret: " + sharedKeyA);
        } else {
            System.out.println("Key exchange failed. Shared secrets do not match.");
        }
    }
}
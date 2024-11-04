package Lab6_Theorem_Advanced;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class ElGamal {

    // Function to calculate (base^exponent) mod modulus using modular exponentiation
    public static BigInteger modExp(BigInteger base, BigInteger exponent, BigInteger modulus) {
        return base.modPow(exponent, modulus);
    }

    // Function to calculate the greatest common divisor (GCD)
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    // Function to find the modular inverse
    public static BigInteger modInverse(BigInteger a, BigInteger m) {
        BigInteger m0 = m;
        BigInteger y = BigInteger.ZERO, x = BigInteger.ONE;

        while (a.compareTo(BigInteger.ONE) > 0) {
            BigInteger q = a.divide(m);

            BigInteger t = m;
            m = a.mod(m);
            a = t;
            t = y;

            y = x.subtract(q.multiply(y));
            x = t;
        }

        if (x.compareTo(BigInteger.ZERO) < 0) {
            x = x.add(m0);
        }

        return x;
    }

    // ElGamal key generation
    public static BigInteger[] generateKeys(int bitLength) {
        Random random = new Random();

        // Step 1: Choose a large prime number p
        BigInteger p = BigInteger.probablePrime(bitLength, random);

        // Step 2: Choose a generator g
        BigInteger g = new BigInteger("2");

        // Step 3: Choose a private key x
        BigInteger x = new BigInteger(bitLength - 1, random);

        // Step 4: Calculate public key y = g^x mod p
        BigInteger y = modExp(g, x, p);

        return new BigInteger[] { p, g, y, x };  // Return (p, g, y) as public key, x as private key
    }

    // ElGamal encryption
    public static BigInteger[] encrypt(BigInteger m, BigInteger p, BigInteger g, BigInteger y) {
        Random random = new Random();

        // Step 1: Choose a random number k
        BigInteger k = new BigInteger(p.bitLength() - 1, random);

        // Step 2: Calculate c1 = g^k mod p
        BigInteger c1 = modExp(g, k, p);

        // Step 3: Calculate c2 = m * y^k mod p
        BigInteger c2 = (m.multiply(modExp(y, k, p))).mod(p);

        return new BigInteger[] { c1, c2 };
    }

    // ElGamal decryption
    public static BigInteger decrypt(BigInteger[] ciphertext, BigInteger p, BigInteger x) {
        // Step 1: Extract c1 and c2
        BigInteger c1 = ciphertext[0];
        BigInteger c2 = ciphertext[1];

        // Step 2: Calculate s = c1^x mod p
        BigInteger s = modExp(c1, x, p);

        // Step 3: Calculate the modular inverse of s
        BigInteger sInv = modInverse(s, p);

        // Step 4: Calculate m = c2 * sInv mod p
        BigInteger m = (c2.multiply(sInv)).mod(p);

        return m;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Key generation
        System.out.print("Enter the bit length for ElGamal key (e.g., 512, 1024): ");
        int bitLength = scanner.nextInt();
        BigInteger[] keys = generateKeys(bitLength);

        BigInteger p = keys[0];  // Prime number
        BigInteger g = keys[1];  // Generator
        BigInteger y = keys[2];  // Public key
        BigInteger x = keys[3];  // Private key

        System.out.println("Public Key: (p = " + p + ", g = " + g + ", y = " + y + ")");
        System.out.println("Private Key: x = " + x);

        // Encryption
        System.out.print("Enter the plaintext (numeric value) to encrypt: ");
        BigInteger m = scanner.nextBigInteger();
        BigInteger[] ciphertext = encrypt(m, p, g, y);
        System.out.println("Ciphertext (c1, c2): (" + ciphertext[0] + ", " + ciphertext[1] + ")");

        // Decryption
        BigInteger decryptedMessage = decrypt(ciphertext, p, x);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
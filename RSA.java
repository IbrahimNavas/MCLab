package Lab6_Theorem_Advanced;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {

    // Function to generate a random prime number
    public static BigInteger generatePrime(int bitLength) {
        Random random = new Random();
        return BigInteger.probablePrime(bitLength, random);
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

    // Function to find modular inverse
    public static BigInteger modInverse(BigInteger a, BigInteger m) {
        BigInteger m0 = m;
        BigInteger y = BigInteger.ZERO, x = BigInteger.ONE;

        while (a.compareTo(BigInteger.ONE) > 0) {
            // q is quotient
            BigInteger q = a.divide(m);

            BigInteger t = m;
            // m is remainder now, process same as Euclid's algorithm
            m = a.mod(m);
            a = t;
            t = y;

            // Update x and y
            y = x.subtract(q.multiply(y));
            x = t;
        }

        // Make x positive
        if (x.compareTo(BigInteger.ZERO) < 0) {
            x = x.add(m0);
        }

        return x;
    }

    // RSA key generation
    public static BigInteger[] generateKeys(int bitLength) {
        BigInteger p = generatePrime(bitLength / 2);  // Generate first prime
        BigInteger q = generatePrime(bitLength / 2);  // Generate second prime
        BigInteger n = p.multiply(q);  // n = p * q
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));  // Euler's Totient Function
        BigInteger e = new BigInteger("65537");  // Common choice for e (public key exponent)
        
        // Ensure that e and phi(n) are coprime (gcd(e, phi) = 1)
        while (gcd(e, phi).compareTo(BigInteger.ONE) != 0) {
            e = e.add(BigInteger.TWO);
        }
        
        BigInteger d = modInverse(e, phi);  // d is the modular inverse of e mod phi(n)
        
        return new BigInteger[] { e, d, n };  // Return public key (e, n) and private key (d, n)
    }

    // RSA encryption
    public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
        return message.modPow(e, n);
    }

    // RSA decryption
    public static BigInteger decrypt(BigInteger ciphertext, BigInteger d, BigInteger n) {
        return ciphertext.modPow(d, n);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Key generation
        System.out.print("Enter the bit length for RSA key (e.g., 512, 1024, 2048): ");
        int bitLength = scanner.nextInt();
        BigInteger[] keys = generateKeys(bitLength);

        BigInteger e = keys[0];  // Public exponent
        BigInteger d = keys[1];  // Private exponent
        BigInteger n = keys[2];  // Modulus

        System.out.println("Public Key: (e = " + e + ", n = " + n + ")");
        System.out.println("Private Key: (d = " + d + ", n = " + n + ")");

        // Encryption
        System.out.print("Enter a message (numeric value) to encrypt: ");
        BigInteger message = scanner.nextBigInteger();
        BigInteger ciphertext = encrypt(message, e, n);
        System.out.println("Ciphertext: " + ciphertext);

        // Decryption
        BigInteger decryptedMessage = decrypt(ciphertext, d, n);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
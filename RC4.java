package Lab5_RC4;

//Lab5

import java.util.Scanner;

public class RC4 {

    // Function to initialize the RC4 state vector (S-box)
    public static void initializeRC4Key(byte[] key, byte[] S) {
        int keyLength = key.length;
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
        }

        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + key[i % keyLength]) & 0xFF;
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }
    }

    // Function to generate the keystream using the RC4 algorithm
    public static void generateKeystream(byte[] S, byte[] keystream, int length) {
        int i = 0, j = 0, k;
        for (int n = 0; n < length; n++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            k = (S[i] + S[j]) & 0xFF;
            keystream[n] = S[k];
        }
    }

    // Function to XOR the keystream with the plaintext or ciphertext
    public static byte[] rc4(byte[] data, byte[] key) {
        byte[] S = new byte[256];
        byte[] keystream = new byte[data.length];

        // Initialize the S-box with the key
        initializeRC4Key(key, S);

        // Generate the keystream
        generateKeystream(S, keystream, data.length);

        // XOR the keystream with the data (encryption or decryption)
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = (byte) (data[i] ^ keystream[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the key for RC4
        System.out.print("Enter the key: ");
        String keyInput = scanner.nextLine();
        byte[] key = keyInput.getBytes();

        // Input the plaintext to encrypt
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();
        byte[] plaintextBytes = plaintext.getBytes();

        // Encrypt the plaintext using RC4
        byte[] ciphertext = rc4(plaintextBytes, key);

        // Display the ciphertext in hexadecimal form
        System.out.print("Ciphertext (in hex): ");
        for (byte b : ciphertext) {
            System.out.printf("%02X ", b);
        }
        System.out.println();

        // Decrypt the ciphertext (since RC4 is symmetric, encryption and decryption are the same)
        byte[] decrypted = rc4(ciphertext, key);

        // Display the decrypted text
        System.out.print("Decrypted plaintext: ");
        System.out.println(new String(decrypted));
    }
}

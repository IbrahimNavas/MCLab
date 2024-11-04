# AES (Advanced Encryption Standard) Overview

AES is a widely used symmetric key encryption algorithm that secures data by transforming it into an unreadable format. It operates on fixed block sizes of 128 bits and supports key lengths of 128, 192, and 256 bits. The encryption process involves several rounds of transformations to ensure strong security against various attacks.

## Steps in AES Encryption

1. **Key Expansion**
    - The original cipher key is expanded into multiple round keys.

2. **Initial Round**
    - **AddRoundKey**: The initial round key is combined with the plaintext block using the XOR operation.

3. **Main Rounds** (Number of rounds varies based on key size)
    - **SubBytes**: Each byte in the state is substituted with a corresponding byte from a precomputed substitution table known as the S-Box.
    - **ShiftRows**: The rows of the state are cyclically shifted to the left, varying by row.
    - **MixColumns**: The columns of the state are mixed to provide diffusion, ensuring that changes in the plaintext affect many bits of the output.
    - **AddRoundKey**: The current state is XORed with the round key for that specific round.

4. **Final Round** (Similar to regular rounds, but without the MixColumns step)
    - **SubBytes**
    - **ShiftRows**
    - **AddRoundKey**

## Decryption in AES

The decryption process reverses the steps of encryption using the inverse operations:

1. **AddRoundKey**
2. **Inverse ShiftRows**
3. **Inverse SubBytes**
4. **AddRoundKey**
5. **Inverse MixColumns** (in rounds where applicable)

The decryption steps continue until all rounds are completed, ultimately restoring the original plaintext from the ciphertext.

By following these processes, AES provides robust security for sensitive data, making it a standard choice for encryption across various applications.


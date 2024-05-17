# El-Gamal-encryption
implementation of EL Gamaal encrypton java code
ElGamal Encryption/Decryption in Java
This Java program implements the ElGamal encryption and decryption algorithm. It generates a prime number, creates the necessary keys, and performs encryption and decryption of a user-provided message. The implementation includes functions for prime number generation, modular exponentiation using the square-and-multiply method, and the extended Euclidean algorithm for modular inverses.

Features
Generate a random prime number within a specified range.
Create a generator for the prime number.
Generate public and private keys.
Encrypt a message using the ElGamal encryption algorithm.
Decrypt an encrypted message using the ElGamal decryption algorithm.
Usage
User Input: The user is prompted to enter a number to be encrypted.
Prime Generation: The program generates a random prime number.
Key Generation: Public and private keys are generated using the prime number.
Encryption: The user’s message is encrypted using the generated keys.
Decryption: The encrypted message is decrypted back to the original message.
Prerequisites
Java Development Kit (JDK) installed.
Basic understanding of Java programming.
Notes
The program checks for valid input and ensures that the message entered is a positive integer.
The prime number generated is between 1000 and 9000 for simplicity, but this range can be adjusted as needed.
The encryption and decryption processes use modular arithmetic and the extended Euclidean algorithm to ensure the correctness of operations.
The implementation demonstrates the core principles of the ElGamal encryption algorithm, making it a useful educational tool for understanding public-key cryptography.




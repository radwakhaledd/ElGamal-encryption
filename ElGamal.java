import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class ElGamal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int message;
        do {
            System.out.print("Enter a number: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
                System.out.print("Enter a number: ");
            }
            message = scanner.nextInt();
        } while (message <= 0);

        System.out.println("Message: " + message);
        int prime = generatePrime();
        System.out.println("Generated Prime: " + prime);
        // Generate keys
        long[] keys = generateKeys(prime);
        System.out.println("Private key (d): " + keys[3]);
        System.out.println("K pub (p,alpha,beta): ( " +keys[0]+" , "+keys[1]+" , " +keys[2]+" )");
        // Encrypt message
        long[] encryptedMessage = encryption(message, keys,prime);
        System.out.println("i : " + encryptedMessage[2]);
        System.out.println("Encrypted message (Y) :  " +encryptedMessage[1]);
        System.out.println("Encrypted message (Ke,Y) : ( " +encryptedMessage[0]+" , "+encryptedMessage[1]+" )");
        // Decrypt message
        long decryptedMessage = decryption(encryptedMessage, keys);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    // Function to check if a number is prime
    public static boolean isPrime(int n)
    {
        for (int i = 2; i * i <= n; i++)
        {
            if (n % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    // Function to generate a prime number within the specified range
    public static int generatePrime() {
        Random random = new Random();
        int primeRange;
        do
        {
            primeRange = ThreadLocalRandom.current().nextInt(1000, 9000) ;
        } while (!isPrime(primeRange) );
        return primeRange;
    }
    public static long squareAndMultiply(long base, long exponent, long modulo) {
        long result = 1;
        while (exponent > 0) {
            //checks the least significant bit
            if ((exponent & 1) == 1) {
                result = (result * base) % modulo;
            }
            base = (base * base) % modulo;
            //Right shift the exponent
            exponent >>= 1;
        }
        return result;
    }


    // Function to create the generator
    public static int createGenerator(int prime)
    {
        int generator;
        do {
            generator = ThreadLocalRandom.current().nextInt(2, prime-1);
        } while (squareAndMultiply(generator,prime-1,prime)!=1);

        return generator;
    }
    // Function to generate public and private keys
    public static long[] generateKeys(int prime)
    {
        int generator = createGenerator(prime);
        int privateKey = ThreadLocalRandom.current().nextInt(2, prime-2);
        long publicKey = squareAndMultiply(generator,privateKey,prime);

        return new long[] { prime, generator, publicKey, privateKey };
    }
    // Function for encryption
    public static long[] encryption(int message, long[] keys,int prime) {
        long p = keys[0];
        long g = keys[1];
        long B = keys[2];
        long d = keys[3];
        int i = ThreadLocalRandom.current().nextInt(2, prime-2);
        long Ke = squareAndMultiply(g,i,p);
        long Km = squareAndMultiply(B,i,p);
        long Y = (message*Km)%p;
        return new long[]{Ke,Y,i};
    }
    //Function for extendedEuclidean
    public static long[] extendedEuclidean(long a, long b) {
        long t = 0, s = 1, last_t = 1, last_s = 0;
        while (b != 0) {
            long quotient = a / b;
            long temp = b;
            b = a % b;
            a = temp;

            long temp_t = t;
            t = last_t - quotient * t;
            last_t = temp_t;

            long temp_s = s;
            s = last_s - quotient * s;
            last_s = temp_s;
        }
        return new long[]{a, last_t, last_s};
    }
    // Function for decryption
    public static long decryption(long[] encryptedMessage, long[] keys) {
        long p = keys[0];
        long d = keys[3];

        long Ke = encryptedMessage[0];
        long Y = encryptedMessage[1];
        long Km = squareAndMultiply(Ke,d,p);
        long[] extendedEuclideanResult = extendedEuclidean(Km, p);
        long inverse = extendedEuclideanResult[1];
        if (inverse < 0) {
            inverse += p; // Ensure that the inverse is positive
        }
        long decryptedMessage = (Y * inverse) % p;
        return decryptedMessage;
    }

}

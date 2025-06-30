import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+<>?";
    private static final SecureRandom random = new SecureRandom();
    private static SecretKey secretKey;

    public static void main(String[] args) throws Exception {
        secretKey = generateKey();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPassword Generator Application");
            System.out.println("1. Generate Password");
            System.out.println("2. Encrypt Password");
            System.out.println("3. Decrypt Password");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> generatePassword(scanner);
                case 2 -> encryptPassword(scanner);
                case 3 -> decryptPassword(scanner);
                case 4 -> {
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return keyGen.generateKey();
    }

    private static void generatePassword(Scanner scanner) {
        System.out.print("Enter desired password length: ");
        int length = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (length <= 0) {
            System.out.println("Invalid length. Please enter a positive number.");
            return;
        }

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        System.out.println("Generated Password: " + password);
    }

    private static void encryptPassword(Scanner scanner) throws Exception {
        System.out.print("Enter password to encrypt: ");
        String password = scanner.nextLine();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        String encryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);

        System.out.println("Encrypted Password: " + encryptedPassword);
    }

    private static void decryptPassword(Scanner scanner) throws Exception {
        System.out.print("Enter encrypted password: ");
        String encryptedPassword = scanner.nextLine();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        String decryptedPassword = new String(decryptedBytes);

        System.out.println("Decrypted Password: " + decryptedPassword);
    }
}

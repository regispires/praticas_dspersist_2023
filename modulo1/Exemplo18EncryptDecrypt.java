import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

public class Exemplo18EncryptDecrypt {

    public static void main(String[] args) {
        String inputFile = args[0];
        String encryptedFile = args[1];
        String decryptedFile = args[2];

        String secretKey = "YourSecretKey123";

        try {
            encryptFile(inputFile, encryptedFile, secretKey);
            System.out.println("File encrypted successfully.");

            decryptFile(encryptedFile, decryptedFile, secretKey);
            System.out.println("File decrypted successfully.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void encryptFile(String inputFile, String encryptedFile, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        try (InputStream inputStream = new FileInputStream(inputFile);
             OutputStream outputStream = new FileOutputStream(encryptedFile);
             CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                cipherOutputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void decryptFile(String encryptedFile, String decryptedFile, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        try (InputStream inputStream = new FileInputStream(encryptedFile);
             CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
             OutputStream outputStream = new FileOutputStream(decryptedFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}

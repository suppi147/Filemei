package FileSecurity;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FilemeiHasher implements SecurityController {
    private static final String SECRET_KEY = "emoidungkhoc";

    public static String base64ToHex(String base64String) {
        byte[] bytes = Base64.getDecoder().decode(base64String);
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static String hexToBase64(String hexString) {
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = (byte) Integer.parseInt(hexString.substring(i, i + 2), 16);
        }
        return Base64.getEncoder().encodeToString(bytes);
    }
    public static String encrypt(String plaintext) {
        try {
            SecretKeySpec secretKey = generateKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            return base64ToHex(Base64.getEncoder().encodeToString(encryptedBytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String encryptedText) {
        try {
            encryptedText=hexToBase64(encryptedText);
            SecretKeySpec secretKey = generateKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static SecretKeySpec generateKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        byte[] validKeyBytes = new byte[16]; // AES-128 key length
        System.arraycopy(keyBytes, 0, validKeyBytes, 0, Math.min(keyBytes.length, validKeyBytes.length));
        return new SecretKeySpec(validKeyBytes, "AES");
    }
   
}

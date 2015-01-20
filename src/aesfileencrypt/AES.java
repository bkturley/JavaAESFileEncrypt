package aesfileencrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static byte[] encrypt(String plainText, String encryptionKey, String seed) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(seed.getBytes("UTF-8")));
        
        String paddedPlaintext =  padStringToMultiple(plainText, 16);
        
        return cipher.doFinal(paddedPlaintext.getBytes("UTF-8"));
    }

    private static String padStringToMultiple(String plainText, int multiple) {
        char nullCharacter = '\0';
        while(!stringLengthIsMultiple(plainText, multiple)){
            plainText += nullCharacter;
        }
        return plainText;
    }
    
    private static boolean stringLengthIsMultiple(String string, int multiple){
        int stringLength = string.length();
        int remainder = stringLength%multiple;
        boolean returnMe = remainder == 0;
        return returnMe;
    }

    public String decrypt(byte[] cipherText, String encryptionKey, String seed) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(seed.getBytes("UTF-8")));
        return new String(cipher.doFinal(cipherText), "UTF-8");
    }
}

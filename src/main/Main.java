package main;

import aesfileencrypt.AES;

public class Main {

    public static void main(String[] args) {
        //16 byte long blocks
        String plainText = "test text 123\0\0\0";
        String encryptionKey = "0123456789abcdef";
        String seed = "AAAAAAAAAAAAAAAA";
        String salt = "ShakaShaka";

        try {

            System.out.println("==Java AES Encryption==");
            System.out.println("Plain Text:   " + plainText);

            AES aes = new AES();
            byte[] cipherText = aes.encrypt(plainText, encryptionKey, seed);

            System.out.print("cipherText:  ");
            for (int i = 0; i < cipherText.length; i++) {
                System.out.print(new Integer(cipherText[i]) + " ");
            }
            System.out.println("");

            String decrypted = aes.decrypt(cipherText, encryptionKey, seed);

            System.out.println("decrypt: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

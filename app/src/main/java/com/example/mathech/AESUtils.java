package com.example.mathech;

import android.content.Context;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    // Pick a secret key (must be same for encrypt + decrypt)
    private static final String SECRET = "my_super_secret_key";

    private static SecretKeySpec getKey() throws Exception {
        byte[] key = SECRET.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // use only first 128 bit
        return new SecretKeySpec(key, "AES");
    }

    // Decrypt from assets folder
    public static String decryptFromAssets(Context context, String assetFile) throws Exception {
        InputStream is = context.getAssets().open(assetFile);
        byte[] encData = new byte[is.available()];
        is.read(encData);
        is.close();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, getKey());
        byte[] decData = cipher.doFinal(encData);
        return new String(decData, "UTF-8");
    }

    // Helper for encryption (used in a separate tool, not in app)
    public static byte[] encrypt(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, getKey());
        return cipher.doFinal(data);
    }
}

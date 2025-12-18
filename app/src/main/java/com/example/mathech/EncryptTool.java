package com.example.mathech;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class EncryptTool {
    public static void main(String[] args) {
        try {
            // Change paths to where your html files are located
            String[] files = {"SnakeandIntegers.html", "BouncingIntegers.html", "BubbleIntegers.html"};

            for (String fileName : files) {
                File inputFile = new File("C:/path/to/your/html/" + fileName);
                byte[] data = readAllBytesCompat(inputFile);

                byte[] encData = AESUtils.encrypt(data);

                File outFile = new File("C:/path/to/output/" + fileName.replace(".html", ".enc"));
                FileOutputStream fos = new FileOutputStream(outFile);
                try {
                    fos.write(encData);
                } finally {
                    fos.close();
                }
                System.out.println(fileName + " encrypted!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] readAllBytesCompat(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int r;
            while ((r = fis.read(buf)) != -1) {
                bos.write(buf, 0, r);
            }
            return bos.toByteArray();
        } finally {
            fis.close();
        }
    }
}

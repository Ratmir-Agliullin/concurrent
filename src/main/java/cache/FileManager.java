package cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileManager {

    private static String currentCheckSum="123545";// " как бы предыдущее" значение кэша
    private static String filePath="C:\\Users\\agliullin\\Desktop\\x5\\concurrent\\src\\main\\resources\\map";

    public static Boolean changedFileHash()  {
        File file = new File(filePath);

        MessageDigest md5Digest = null;
        try {
            md5Digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String checksum = null;
        try {
            checksum = getFileChecksum(md5Digest, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (checksum.equals(currentCheckSum)) {
            return true;
        }
        currentCheckSum = checksum;
        System.out.println("hashcode of file has been changed");
        return false;
    }
    private static String getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        FileInputStream fis = new FileInputStream(file);

        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        fis.close();

        byte[] bytes = digest.digest();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static void addStringInFile() {
        String added = "\nАвстралия-Канберра";
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.append(added);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Deprecated
public class ReadFile {

    static Map<String, Object> cache = new HashMap<String, Object>();

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("C:\\Users\\agliullin\\Desktop\\x5\\concurrent\\src\\main\\resources\\map"));
            while (in.hasNextLine()) {
                String curLine = in.nextLine();
                cache.put(curLine.split("-")[0], curLine.split("-")[1]);
                System.out.println(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't read file");
            e.printStackTrace();
        }

    }
}

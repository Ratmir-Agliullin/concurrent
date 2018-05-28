import cache.CacheManager;
import cache.CacheThread;
import cache.FileManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CachTest {


    private CacheManager cacheManager;

    @Before
    public void beforeTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        cacheManager = (CacheManager) context.getBean("cacheManager");
        readFile();
        System.out.println("cach size is "+ cacheManager.getMap().size());
    }

    private void readFile() {
        Map<String, Object> cache = new HashMap<String, Object>();
        try {
            Scanner in = new Scanner(new File("C:\\Users\\agliullin\\Desktop\\x5\\concurrent\\src\\main\\resources\\map"));
            while (in.hasNextLine()) {
                String curLine = in.nextLine();
                cache.put(curLine.split("-")[0], curLine.split("-")[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't read file");
            e.printStackTrace();
        }
        cacheManager.setMap(cache);
    }

    @Test
    public void MainTest() {
        CacheThread cacheThread1 = new CacheThread(cacheManager, "№1");
        CacheThread cacheThread2 = new CacheThread(cacheManager, "№2");
        CacheThread cacheThread3 = new CacheThread(cacheManager, "№3");
        //выводим первоначальный кэш в три потока
        cacheThread1.start();
        cacheThread2.start();
        cacheThread3.start();
        System.out.println("current size of cache is "+cacheManager.getMap().size());
        FileManager.addStringInFile();
        if (!FileManager.changedFileHash()) {
            cacheManager.clearCache();
        }
        readFile();
        System.out.println("new size of cache is "+cacheManager.getMap().size());
        assert (cacheManager.getMap().size() != 0);
    }
}

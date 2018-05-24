import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import threads.PrototypeBean;
import threads.SingletonBean;
import threads.SomeThread;

import static org.junit.Assert.assertTrue;

public class ScopeBeanTest {

    private final static Logger log = LoggerFactory.getLogger(ScopeBeanTest.class);

    SomeThread singletonThread1;
    SomeThread prototypeThread1;

    SomeThread singletonThread2;
    SomeThread prototypeThread2;

    SingletonBean singletonBean1;
    PrototypeBean prototypeBean1;

    SingletonBean singletonBean2;
    PrototypeBean prototypeBean2;

    @Before
    public void Init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        singletonBean1 = (SingletonBean) context.getBean("singletonBean");
        singletonBean2 = (SingletonBean) context.getBean("singletonBean");
        prototypeBean1 = (PrototypeBean) context.getBean("prototypeBean");
        prototypeBean2 = (PrototypeBean) context.getBean("prototypeBean");
    }

    @Test
    public void ScopeTest() {
        singletonThread1 = new SomeThread(singletonBean1, "singletonBean");
        prototypeThread1 = new SomeThread(prototypeBean1, "prototypeBean");

        singletonThread2 = new SomeThread(singletonBean2, "singletonBean");
        prototypeThread2 = new SomeThread(prototypeBean2, "prototypeBean");

        Thread sThread1 = new Thread(singletonThread1);
        Thread sThread2 = new Thread(singletonThread2);
        sThread1.start();
        sThread2.start();

        Thread pThread1 = new Thread(prototypeThread1);
        Thread pThread2 = new Thread(prototypeThread2);
        pThread1.start();
        pThread2.start();

        assertTrue(prototypeBean1.getCount()<=5);
        //log.info("prototypeBean1 = "+prototypeBean1.getCount());
        System.out.println("prototypeBean1 = "+prototypeBean1.getCount());
        assertTrue(prototypeBean2.getCount()<=5);
        log.info("prototypeBean2 = "+prototypeBean1.getCount());
        System.out.println("prototypeBean2 = "+prototypeBean1.getCount());
        assertTrue(singletonBean1.getCount()>5);
        log.info("singletonBean1 = "+singletonBean1.getCount());
        System.out.println("singletonBean1 = "+singletonBean1.getCount());
        assertTrue(singletonBean2.getCount()>5);
        log.info("singletonBean2 = "+singletonBean2.getCount());
        System.out.println("singletonBean2 = "+singletonBean2.getCount());
    }
}

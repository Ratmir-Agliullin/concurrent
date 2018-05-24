package threads;

import beans.FirstBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SomeThread implements Runnable{

    private BeansInterface bean;
    private String beanName;

    public SomeThread(BeansInterface bean, String beanName) {
        this.bean = bean;
        this.beanName = beanName;
    }

    public void run() {
        for(int i=0;i<5;i++) {
            getIncrement();
        }
    }

    public int getIncrement() {
        return bean.inc();
    }
}

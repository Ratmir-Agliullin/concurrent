import beans.FirstBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        FirstBean bean = (FirstBean) context.getBean("firstBean");
        System.out.println(bean.getName());
    }
}

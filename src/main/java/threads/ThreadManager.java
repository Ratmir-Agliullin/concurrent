package threads;

public class ThreadManager {

    public static void main(String[] args) {
        SingletonBean singletonBean = null;
        SomeThread thread1 = new SomeThread(singletonBean, "prototypeBean");
        Thread mt1 = new Thread(thread1);
        mt1.start();
    }
}

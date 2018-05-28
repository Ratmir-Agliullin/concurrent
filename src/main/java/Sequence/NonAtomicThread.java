package Sequence;

public class NonAtomicThread extends Thread {

    private NonAtomicSequence count;
    private Integer flag;

    public NonAtomicThread(NonAtomicSequence count, Integer flag) {
        this.count = count;
        this.flag = flag;
    }

    @Override
    public void run() {
        synchronized (this) {
        for (int i = 0; i < 100; i++) {
            count.next();
        }
         notify();}
        flag  =flag+1;
      //  System.out.println("non: "+count.curval().toString());
    }
}

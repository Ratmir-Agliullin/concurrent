package Sequence;

public class AtomicThread extends Thread {

    private AtomicSequence count;
    private Integer flag;

    public AtomicThread(AtomicSequence count, Integer flag) {
        this.count = count;
        this.flag = flag;
    }

    @Override
    public void run() {
        synchronized (this) {
        for(int i=0;i<100;i++) {
count.next();
        } notifyAll();}
        flag=flag+1;
     //   System.out.println("atomic "+ count.curval().toString());
    }
}

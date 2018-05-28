
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertTrue;

public class SequenceTest {

    public static volatile Integer flag = 0;

    class ASeq {
        AtomicReference<BigInteger> value;

        ASeq(AtomicReference<BigInteger> value) {
            this.value = value;
        }

        public synchronized void next() {
            BigInteger current = value.get();
            BigInteger next = current.add(BigInteger.ONE);
            value.set(next);
            notify();
        }
    }

    class NSeq {
        BigInteger value;

        NSeq(BigInteger value) {
            this.value = value;
        }

        public synchronized void next() {
    value = new BigInteger(String.valueOf(value.intValue()+1));
            notify();
        }
    }

    class AThread implements Runnable {
        ASeq aSeq;

        AThread(ASeq aSeq) {
            this.aSeq = aSeq;
        }

        public void run() {
            for (int i = 0; i < 100; i++) {
                aSeq.next();
            }
            flag = flag + 1;
        }
    }

    class NThread implements Runnable {
        NSeq nSeq;

        NThread(NSeq nSeq) {
            this.nSeq = nSeq;
        }

        public void run() {
            for (int i = 0; i < 100; i++) {
                nSeq.next();
            }
            flag = flag + 1;
        }
    }


    @Test
    public void test() {
        AtomicReference<BigInteger> value = new AtomicReference<BigInteger>(BigInteger.ZERO);
        BigInteger value1 = new BigInteger("0");
        ASeq aSeq = new ASeq(value);
        NSeq nSeq = new NSeq(value1);
        AThread aThread = new AThread(aSeq);
        NThread nThread = new NThread(nSeq);
        new Thread(aThread).start();
        new Thread(aThread).start();
        new Thread(nThread).start();
        new Thread(nThread).start();
        while (flag < 4) {
        }
//        value1 = new BigInteger(String.valueOf(value1.intValue()+1));
        System.out.println("Atomic: " + value.get().toString());
        System.out.println("NonAtomic: " + value1.toString());
        assertTrue(value.get().toString().equals("200"));
        assertTrue(!value1.toString().equals("200"));
    }
}

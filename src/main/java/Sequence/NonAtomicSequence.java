package Sequence;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class NonAtomicSequence implements Sequence {

    BigInteger value;

    public NonAtomicSequence(BigInteger value) {
        this.value = value;
    }

    public synchronized BigInteger next() {
            value = new BigInteger(String.valueOf(value.intValue() + 1));
            notify();
            return value;
    }

    public BigInteger curval() {
        return value;
    }
}

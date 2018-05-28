package Sequence;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicSequence {

    AtomicReference<BigInteger> value;

    public AtomicSequence(AtomicReference<BigInteger> value) {
        this.value = value;
    }

    public synchronized AtomicReference<BigInteger> next() {

            BigInteger current = value.get();
            BigInteger next = current.add(BigInteger.ONE);
            value.set(next);
            notify();

        return value;
    }

    public AtomicReference<BigInteger> curval() {
        return value;
    }
}

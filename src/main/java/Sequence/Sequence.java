package Sequence;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public interface Sequence {
BigInteger next();
    BigInteger curval();
}

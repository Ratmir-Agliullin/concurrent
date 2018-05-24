import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

public class SequenceImpl implements Sequence {

    AtomicReference<BigInteger>[] array;
    int index;


    public BigInteger next() {
        if (index>=array.length) {
            throw new NoSuchElementException();
        }
        return array[index+1].get();
    }

    public BigInteger curval() {
        return array[index].get();
    }

//
//    AtomicReference<Integer> cache = new AtomicReference<Integer>();
//
//
//    public BigInteger next() {
//        return null;
//    }
//
//    public BigInteger curval() {
//        return null;
//    }
}

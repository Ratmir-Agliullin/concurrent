package threads;

public class PrototypeBean implements BeansInterface {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public PrototypeBean(int count) {
        this.count = count;
    }

    public int inc() {
        return ++count;
    }
}

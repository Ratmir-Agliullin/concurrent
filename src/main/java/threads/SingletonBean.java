package threads;

public class SingletonBean implements BeansInterface {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SingletonBean(int count) {
        this.count = count;
    }

    public int inc() {
        return ++count;
    }
}

package demo.singleton;

public class StarvingSingleton {
    public static final StarvingSingleton instance = new StarvingSingleton();
    private StarvingSingleton () { }
    public static StarvingSingleton getInstance() {
        return instance;
    }
}

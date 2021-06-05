package demo.singleton;

public class LazyDoubleCheckSingleton {
    public static volatile LazyDoubleCheckSingleton instance;
    private LazyDoubleCheckSingleton () {};

    public LazyDoubleCheckSingleton getInstance () {
        if (instance == null) {
            synchronized (LazyDoubleCheckSingleton.class){
                if (instance == null) {
                    //memory = allocate() //1.分配对象内存空间
                    //instance(memory) //2.初始化对象
                    //instance memory //3.设置instance 指向刚分配的内存地址，此时instance != null
                    return new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

}

package demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorCollector {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
         Class clazz = Class.forName("demo.reflect.ReflectTarget");
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        constructor.setAccessible(true);
        ReflectTarget target = (ReflectTarget) constructor.newInstance("zp", 123);
        target.sayHi();
    }
}

package demo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FiledCollector {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class clazz = Class.forName("demo.reflect.ReflectTarget");
        ReflectTarget target = (ReflectTarget) clazz.getConstructor().newInstance();

        Field[] fields= clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println("f::"+ f);
        }

        Field field = clazz.getField("name");
        field.set(target, "zp");
        field.setAccessible(true);
        System.out.println(target.name);
    }
}

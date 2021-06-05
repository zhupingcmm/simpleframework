package demo.reflect;

public class ReflectTarget {

    public String name;
    public int index;

    ReflectTarget (String name) {
        System.out.println("default constructor");
    }
    public ReflectTarget(){
        System.out.println("wu can constructor");
    }

    public ReflectTarget (String name, int index) {
        System.out.println("dan cai constructor"+ name +":"+ index);
    }

    @Override
    public String toString(){
        return "[ReflectTarget filed ] name:" + name + ", index:" + index;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //第一种方式
        ReflectTarget target = new ReflectTarget();
        Class reflectTarget1 = target.getClass();
        System.out.println(reflectTarget1.getName());
        //第二种方式
        Class reflectTarget2 = ReflectTarget.class;
        System.out.println(reflectTarget2.getName());

        //第三种方式
        Class reflectTarget3 = Class.forName("demo.reflect.ReflectTarget");
        System.out.println(reflectTarget3.getName());

    }

    public void sayHi() {
        System.out.println("hi");
    }
}

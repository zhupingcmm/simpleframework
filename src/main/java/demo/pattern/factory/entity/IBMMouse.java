package demo.pattern.factory.entity;

public class IBMMouse implements Mouse{
    @Override
    public void sayHi() {
        System.out.println("I am IBM mouse!");
    }
}

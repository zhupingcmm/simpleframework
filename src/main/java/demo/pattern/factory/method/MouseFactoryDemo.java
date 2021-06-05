package demo.pattern.factory.method;

import demo.pattern.factory.entity.Mouse;

public class MouseFactoryDemo {
    public static void main(String[] args) {
        MouseFactory factory  = new HPMouseFactory();
        Mouse mouse = factory.createMouse();
        mouse.sayHi();
    }
}

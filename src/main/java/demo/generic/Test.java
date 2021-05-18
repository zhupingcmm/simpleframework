package demo.generic;

public class Test {
    public static void main(String[] args) {
        RobotFactory factory = new RobotFactory();
        System.out.println(factory.nextNumber());
        System.out.println(factory.nextObject());

        Character [] characters = new Character[]{'A', 'B'};
        RobotFactory.printValue(characters);
    }
}

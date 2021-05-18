package demo.generic;

import java.util.Random;

public class RobotFactory implements GenericIFactory<String,Integer>{
    private String[] stringRobot = new String[]{"hello", "jhh"};
    private Integer[] integerRobot = new Integer[]{1,2};
    public String nextObject() {
        Random random = new Random();
        return stringRobot[random.nextInt(2)];
    }

    @Override
    public Integer nextNumber() {
        Random random = new Random();
        return integerRobot[random.nextInt(2)];
    }

    public static <E> void printValue(E [] inputArray) {
        for (E ele : inputArray) {
            System.out.printf("%s", ele);
            System.out.printf(" ");
        }
        System.out.println();
    }
}

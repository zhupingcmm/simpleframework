package demo.generic;

import lombok.Data;

@Data
public class Demo <T>{
    private T member;

    public Demo(T member) {
        this.member = member;
    }
}

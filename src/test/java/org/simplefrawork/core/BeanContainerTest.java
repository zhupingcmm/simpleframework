package org.simplefrawork.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BeanContainerTest {
    private static BeanContainer instance;

    @BeforeAll
    static void init(){
        instance = BeanContainer.getInstance();
    }

    @Test
    public void loadBeansTest () {
        instance.isLoaded();
        Assertions.assertEquals(false, instance.isLoaded());
        instance.loadBeans("com.imooc");
        Assertions.assertEquals(6, instance.size());
        Assertions.assertEquals(true, instance.isLoaded());
    }
}

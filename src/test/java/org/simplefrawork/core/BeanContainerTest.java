package org.simplefrawork.core;

import com.imooc.controller.frontend.MainPageController;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {
    private static BeanContainer instance;

    @BeforeAll
    static void init() {
        instance = BeanContainer.getInstance();
    }

    @Test
    @Order(1)
    public void loadBeansTest() {
        instance.isLoaded();
        Assertions.assertEquals(false, instance.isLoaded());
        instance.loadBeans("com.imooc");
        Assertions.assertEquals(6, instance.size());
        Assertions.assertEquals(true, instance.isLoaded());
    }

    @Test
    @Order(2)
    public void getBean () {
        MainPageController controller = (MainPageController) instance.getBean(MainPageController.class);
        Assertions.assertEquals(true, controller instanceof MainPageController);
    }
}

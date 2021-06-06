package org.simplefrawork.inject;

import com.imooc.controller.frontend.MainPageController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simplefrawork.core.BeanContainer;

public class DependencyInjectorTest {
    @DisplayName("doIoc test")
    @Test
    public void doIocTest () {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.imooc");
        MainPageController controller = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, controller instanceof MainPageController);
        Assertions.assertEquals(null, controller.getService());
        DependencyInjector injector = new DependencyInjector();
        injector.doIoc();
        Assertions.assertNotEquals(null,controller.getService());
    }
}

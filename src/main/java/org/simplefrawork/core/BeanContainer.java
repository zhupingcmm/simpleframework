package org.simplefrawork.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simplefrawork.core.annotation.Component;
import org.simplefrawork.core.annotation.Controller;
import org.simplefrawork.core.annotation.Repository;
import org.simplefrawork.core.annotation.Service;
import org.simplefrawork.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {
    public static volatile BeanContainer instance;
    private boolean loaded = false;


    private final Map<Class<?>, Object> beanMap= new ConcurrentHashMap();

    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class, Controller.class, Repository.class, Service.class);

    public static BeanContainer getInstance () {
        if (instance == null) {
            synchronized (BeanContainer.class) {
                if (instance == null) {
                    return new BeanContainer();
                }
            }
        }
        return instance;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public int size () {
        return beanMap.size();
    }

    public synchronized void loadBeans (String packageName) {
        if (isLoaded()) {
            log.warn("BeanContainer have been initial!!!");
            return;
        }
        Set<Class<?>> clazzSet =  ClassUtil.extractPackageClass(packageName);
        if (clazzSet == null) {
            log.warn("no beans was been loaded!!!");
            return;
        }

        for (Class<?> clazz : clazzSet) {
            for (Class<? extends Annotation> annotation: BEAN_ANNOTATION) {
                if (clazz.isAnnotationPresent(annotation)) {
                    beanMap.put(clazz,ClassUtil.newInstance(clazz));
                }
            }
        }
        loaded = true;
    }

}

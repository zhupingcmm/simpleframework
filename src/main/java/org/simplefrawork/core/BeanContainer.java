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
import java.util.*;
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
                    instance =  new BeanContainer();
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

    public Object addBean (Class<?> clazz, Object instance) {
        return beanMap.put(clazz, instance);
    }

    public Object deleteBean (Class<?> clazz) {
        return beanMap.remove(clazz);
    }

    public Set<Class<?>> getClasses () {
        return beanMap.keySet();
    }

    public Set<Object> getBeans () {
        return new HashSet<>(beanMap.values());
    }

    public Object getBean (Class<?> clazz) {
        return beanMap.get(clazz);
    }

    public Set<Class<?>> getClassesByAnnotation (Class<? extends Annotation> annotation) {
        Set<Class<?>> classSet = getClasses();

        Set<Class<?>> classes = new HashSet<>();

        for (Class<?> clazz : classSet) {
            if (clazz.isAnnotationPresent(annotation)) {
                classes.add(clazz);
            }
        }
        return classes;

    }

    public Set<Class<?>> getClassesBySuper (Class<?> interfaceOrClass) {
        Set<Class<?>> keySet = getClasses();

        Set<Class<?>> classSet = new HashSet<>();

        for (Class<?> clazz : keySet) {
            if (interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)) {
                classSet.add(clazz);
            }
        }

        return classSet;
    }

}

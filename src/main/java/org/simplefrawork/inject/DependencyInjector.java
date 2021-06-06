package org.simplefrawork.inject;

import org.simplefrawork.core.BeanContainer;
import org.simplefrawork.inject.annotation.Autowired;
import org.simplefrawork.util.ClassUtil;
import org.simplefrawork.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

public class DependencyInjector {
    private BeanContainer beanContainer;

    public DependencyInjector () {
        beanContainer = BeanContainer.getInstance();
    }

    public void doIoc () {

        for (Class clazz: beanContainer.getClasses()) {
            Field [] fields = clazz.getDeclaredFields();
            for (Field field: fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.name();
                    Class<?> fieldClass= field.getType();
                    Object filedValue = getFieldInstance(fieldClass, autowiredValue);
                    if(filedValue == null){
                        throw new RuntimeException("unable to inject relevant type，target fieldClass is:" + fieldClass.getName() + " autowiredValue is : " + autowiredValue);
                    } else {
                        //6.通过反射将对应的成员变量实例注入到成员变量所在类的实例里
                        Object targetBean =  beanContainer.getBean(clazz);
                        ClassUtil.setField(field, targetBean, filedValue, true);
                    }
                }
            }

        }

    }

    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
       Object fieldValue =  beanContainer.getBean(fieldClass);

       if (fieldValue != null) {
           return fieldValue;
       } else {
           Class<?> implementedClass = getImplementedClass(fieldClass, autowiredValue);

           if (implementedClass != null) {
               return beanContainer.getBean(implementedClass);
           }
       }
       return null;

    }

    private Class<?> getImplementedClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (classSet != null) {
            if (classSet.size() != 0) {
                if (classSet.size() == 1) {
                    return classSet.iterator().next();
                } else {
                    if (ValidationUtil.isEmpty(autowiredValue)) {
                        throw new RuntimeException("multiple implement class for  " + fieldClass.getSimpleName() + "please config the right one ");
                    } else {
                        for (Class<?> clazz: classSet) {
                            if (autowiredValue.equalsIgnoreCase(clazz.getSimpleName())) {
                                return clazz;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}

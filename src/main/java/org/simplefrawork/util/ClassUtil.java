package org.simplefrawork.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ClassUtil {

    private static final String FILE_PROTOCOL = "file";

    /**
     *
     * @param packageName
     * @return
     */
    public static Set<Class<?>> extractPackageClass(String packageName) {
        ClassLoader classLoader = getClassLoader();
        URL url = classLoader.getResource(packageName.replace(".","/"));
        if (url == null) {
            return null;
        }
        Set<Class<?>> classSet = null;
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
            classSet = new HashSet<>();
            File packageDir = new File(url.getPath());
            extractClassFile(classSet, packageDir, packageName);
        }
        return classSet;
    }

    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        if (!fileSource.isDirectory()) return;
        File [] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    String absolutePath = file.getAbsolutePath();
                    if (absolutePath.endsWith(".class")) {
                        addToClassSet(absolutePath, packageName, emptyClassSet);
                    }
                }
                return false;
            }
        });

        if (files != null) {
            for (File f: files) {
                extractClassFile(emptyClassSet, f, packageName);
            }
        }
    }

    public static <T> T newInstance(Class<?> clazz) {
        try {
            return (T)clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void addToClassSet(String absolutePath, String packageName, Set<Class<?>> emptyClassSet) {
        absolutePath = absolutePath.replace(File.separator, ".");
        String clazzName = absolutePath.substring(absolutePath.indexOf(packageName));
        clazzName = clazzName.substring(0, clazzName.lastIndexOf("."));
        Class targetClazz = loadClass(clazzName);
        emptyClassSet.add(targetClazz);

    }

    public static void setField (Field field, Object target, Object value, boolean accessible) {
        field.setAccessible(accessible);
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            log.error("setFiled error:", e);
            throw new RuntimeException(e);
        }

    }

    public static Class<?> loadClass (String clazzName) {
        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    /**
     *
     * @return 当前的classLoader
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static void main(String[] args) {
        extractPackageClass("com.imooc.entity");
    }
}

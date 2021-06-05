package demo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationParser {
    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("demo.annotation.ImoockCurse");
        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            CurseInfoAnnotation curseInfoAnnotation = (CurseInfoAnnotation) annotation;
            System.out.println(curseInfoAnnotation.curseName() +"\n"+ curseInfoAnnotation.curseProfile());
        }
    }


    public static void parseMethodAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("demo.annotation.ImoockCurse");
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            boolean hasAnnotation = method.isAnnotationPresent(CurseInfoAnnotation.class);
            if (hasAnnotation) {
//                Annotation[] annotations = method.getAnnotationsByType(CurseInfoAnnotation.class);
//                for (Annotation annotation : annotations) {
//                   CurseInfoAnnotation curseInfoAnnotation = (CurseInfoAnnotation) annotation;
//                    System.out.println(curseInfoAnnotation.curseName() + curseInfoAnnotation.curseProfile());
////                   curseInfoAnnotation.curseIndex();
//                }
            }

        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        parseTypeAnnotation();
        parseMethodAnnotation();
    }
}

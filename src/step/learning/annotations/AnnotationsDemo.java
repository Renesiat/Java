package step.learning.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationsDemo {


    AnnotatedClass an = new AnnotatedClass();
    Class<?> annotatedClass = an.getClass();

    NonAnnotatedClass obj = new NonAnnotatedClass();
    Class<?> nonAnnotatedClass = obj.getClass();

    MixedAnnotatedClass mix = new MixedAnnotatedClass();
    Class<?> mixedClass = mix.getClass();
    public void Run() {
        try {
            mixedClass = Class.forName(
                    "step.learning.annotations.MixedAnnotatedClass"
            );
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
            return;
        }

        TypeAnnotation annotation = annotatedClass.getAnnotation(TypeAnnotation.class);
        if (annotation == null) {
            System.out.printf("Class '%s' has no TypeAnnotation %n",
                    annotatedClass.getName());
        } else {
            System.out.printf("Class '%s' does TypeAnnotation %n",
                    annotatedClass.getName());
        }

        TypeAnnotation nonAnnotation = nonAnnotatedClass.getAnnotation(TypeAnnotation.class);
        if (nonAnnotation == null) {
            System.out.printf("Class '%s' has no TypeAnnotation %n",
                    nonAnnotatedClass.getName());
        } else {
            System.out.printf("Class '%s' does TypeAnnotation %n",
                    nonAnnotatedClass.getName());
        }

        TypeAnnotation mixedAnnotation = mixedClass.getAnnotation(TypeAnnotation.class);
        if (mixedAnnotation == null) {
            System.out.printf("Class '%s' has no TypeAnnotation %n",
                    mixedClass.getName());
        } else {
            System.out.printf("Class '%s' does TypeAnnotation %n",
                    mixedClass.getName());
        }

        System.out.println("------------------------------------------------------");

        AnnotatedMethods();

        System.out.println("------------------------------------------------------");

        NonAnnotatedMethods();

        System.out.println("------------------------------------------------------");

        MixedMethods();

        System.out.println("------------------------------------------------------");

        FieldAnnotation();

        System.out.println("Done");
    }

    public void AnnotatedMethods() {
        Method[] methods = annotatedClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MethodAnnotation.class)) {
                MethodAnnotation methodAnnotation =
                        method.getAnnotation(MethodAnnotation.class);
                System.out.printf("Method '%s' of class '%s' has '%s' annotation%n",
                        method.getName(), annotatedClass.getName(),
                        methodAnnotation.value());
            } else {
                System.out.printf("Method '%s' of class '%s' has  NO annotation%n",
                        method.getName(), annotatedClass.getName());
            }
            try {
                method.invoke(an);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                System.out.println("Method call error" + ex.getMessage());
            }
        }
    }

    public void NonAnnotatedMethods() {

        Method[] methods = nonAnnotatedClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MethodAnnotation.class)) {
                MethodAnnotation methodAnnotation =
                        method.getAnnotation(MethodAnnotation.class);
                System.out.printf("Method '%s' of class '%s' has '%s' annotation%n",
                        method.getName(), nonAnnotatedClass.getName(),
                        methodAnnotation.value());
            } else {
                System.out.printf("Method '%s' of class '%s' has  NO annotation%n",
                        method.getName(), nonAnnotatedClass.getName());
            }
            try {
                method.invoke(obj);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                System.out.println("Method call error" + ex.getMessage());
            }
        }
    }

    public void MixedMethods() {

        Method[] methods = mixedClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MethodAnnotation.class)) {
                MethodAnnotation methodAnnotation =
                        method.getAnnotation(MethodAnnotation.class);
                System.out.printf("Method '%s' of class '%s' has '%s' annotation%n",
                        method.getName(), mixedClass.getName(),
                        methodAnnotation.value());
            } else {
                System.out.printf("Method '%s' of class '%s' has  NO annotation%n",
                        method.getName(), mixedClass.getName());
            }
            try {
                method.invoke(mix);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                System.out.println("Method call error" + ex.getMessage());
            }
        }
    }

    public void FieldAnnotation(){
        Field[] fields = annotatedClass.getDeclaredFields() ;
        for( Field field : fields ) {
            FieldAnnotation fieldAnnotation =
                    field.getAnnotation( FieldAnnotation.class ) ;
            if( fieldAnnotation != null ) {
                System.out.printf(
                        "Field '%s' of class '%s' annotated for ver='%s' and pri='%d'\n",
                        field.getName(), annotatedClass.getName(),
                        fieldAnnotation.version(), fieldAnnotation.priority() ) ;
            }
            else {
                System.out.printf( "Field '%s' of class '%s' NOT annotated \n",
                        field.getName(), annotatedClass.getName() ) ;
            }
        }
    }


}

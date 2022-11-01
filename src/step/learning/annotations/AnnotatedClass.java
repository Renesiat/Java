package step.learning.annotations;

import java.lang.reflect.Method;

@TypeAnnotation
public class AnnotatedClass {
    @FieldAnnotation( version = "1.0", priority = 1 )
    public int field1;

    @FieldAnnotation( version = "1.1", priority = 2 )
    public String field2;

    @FieldAnnotation( version = "1.2", priority = 0 )
    public double field3;
@MethodAnnotation("Recommended")
    public void method1(){
    System.out.println("method1 works");
}

    @MethodAnnotation("Default")
    protected void method2(){
        System.out.println("method2 works");
    }

    @MethodAnnotation("Deprecate")
    private void method3(){
        System.out.println("method3 works");
    }



}

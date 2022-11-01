package step.learning.annotations;

public class MixedAnnotatedClass {

    public void method1(){
        System.out.println("method1 works");
    }

    @MethodAnnotation(value = "EntryPoint")
    protected void method2(){
        System.out.println("method2 works");
    }

    @MethodAnnotation("Finalizer")
    private void method3(){
        System.out.println("method3 works");
    }
}

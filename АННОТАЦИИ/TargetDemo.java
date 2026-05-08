package annotationexamples.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@interface MethodOnly { }

@Target({ElementType.TYPE, ElementType.FIELD})
@interface ClassAndField { }

@ClassAndField
class Example {
    @MethodOnly
    public void method1() { }
    
    @ClassAndField
    private String field;
}

public class TargetDemo {
    public static void demo() {
        System.out.println("@Target");
        System.out.println("MethodOnly: только методы");
        System.out.println("ClassAndField: классы и поля");
        System.out.println();
    }
}
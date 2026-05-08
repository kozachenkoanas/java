package annotationexamples.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.SOURCE)
@interface SourceAnnotation { }

@Retention(RetentionPolicy.CLASS)
@interface ClassAnnotation { }

@Retention(RetentionPolicy.RUNTIME)
@interface RuntimeAnnotation {
    String value();
}

class RetentionExample {
    @SourceAnnotation
    public void sourceMethod() { }
    
    @ClassAnnotation
    public void classMethod() { }
    
    @RuntimeAnnotation("runtime data")
    public void runtimeMethod() { }
}

public class RetentionDemo {
    public static void demo() throws Exception {
        System.out.println("@Retention");
        
        Method m = RetentionExample.class.getMethod("runtimeMethod");
        if (m.isAnnotationPresent(RuntimeAnnotation.class)) {
            RuntimeAnnotation ra = m.getAnnotation(RuntimeAnnotation.class);
            System.out.println("Runtime annotation: " + ra.value());
        }
        
        System.out.println("SOURCE: только в .java");
        System.out.println("CLASS: в .class файле");
        System.out.println("RUNTIME: доступно через рефлексию\n");
    }
}
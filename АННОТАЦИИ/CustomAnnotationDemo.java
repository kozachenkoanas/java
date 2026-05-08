package annotationexamples.custom;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Important { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Priority {
    int value() default 1;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ApiInfo {
    String endpoint();
    String method() default "GET";
}

@Important
class ApiService {
    
    @Priority(5)
    public void criticalMethod() { }
    
    @ApiInfo(endpoint = "/users", method = "POST")
    public void createUser() { }
}

public class CustomAnnotationDemo {
    public static void demo() {
        System.out.println("ПОЛЬЗОВАТЕЛЬСКИЕ АННОТАЦИИ");
        
        Class<ApiService> clazz = ApiService.class;
        System.out.println("Class @Important: " + clazz.isAnnotationPresent(Important.class));
        
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Priority.class)) {
                Priority p = m.getAnnotation(Priority.class);
                System.out.println(m.getName() + " priority: " + p.value());
            }
            if (m.isAnnotationPresent(ApiInfo.class)) {
                ApiInfo ai = m.getAnnotation(ApiInfo.class);
                System.out.println(m.getName() + ": " + ai.method() + " " + ai.endpoint());
            }
        }
        System.out.println();
    }
}
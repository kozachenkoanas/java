package annotationexamples;

import annotationexamples.builtin.*;
import annotationexamples.meta.*;
import annotationexamples.custom.*;
import annotationexamples.spring.*;
import annotationexamples.jpa.*;

public class AnnotationExamples {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        try {
            java.lang.reflect.Field charset = System.out.getClass().getDeclaredField("charset");
            charset.setAccessible(true);
            charset.set(System.out, java.nio.charset.Charset.forName("UTF-8"));
        } catch (Exception e) {
            try {
                System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
            } catch (Exception ex) {}
        }
        
        System.out.println("ПРИМЕРЫ АННОТАЦИЙ\n");
        
        // Built-in
        OverrideDemo.demo();
        DeprecatedDemo.demo();
        SuppressWarningsDemo.demo();
        FunctionalInterfaceDemo.demo();
        
        // Meta
        TargetDemo.demo();
        try {
            RetentionDemo.demo();
        } catch (Exception e) {}
        
        // Custom
        CustomAnnotationDemo.demo();
        ValidationDemo.demo();
        
        // Spring
        SpringAnnotationsDemo.demo();
        SpringConfigDemo.demo();
        
        // JPA
        JpaAnnotationsDemo.demo();
        HibernateAnnotationsDemo.demo();
        
        System.out.println("\n Собрано успешно");
    }
}
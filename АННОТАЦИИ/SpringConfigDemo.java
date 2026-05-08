package annotationexamples.spring;

import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Configuration { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Bean { 
    String name() default "";
    String initMethod() default "";
    String destroyMethod() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Scope { 
    String value() default "singleton";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Lazy { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Primary { }

@Configuration
class AppConfig {
    
    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "cleanup")
    @Scope("prototype")
    @Lazy
    @Primary
    public DataSource dataSource() {
        return new DataSource();
    }
    
    @Bean
    public EmailService emailService() {
        return new EmailService();
    }
}

class DataSource {
    public void init() {
        System.out.println("DataSource init");
    }
    
    public void cleanup() {
        System.out.println("DataSource cleanup");
    }
}

class EmailService {
    public void sendEmail() {
        System.out.println("Sending email...");
    }
}

public class SpringConfigDemo {
    public static void demo() {
        System.out.println("SPRING CONFIGURATION АННОТАЦИИ");
        
        Class<AppConfig> configClass = AppConfig.class;
        
        if (configClass.isAnnotationPresent(Configuration.class)) {
            System.out.println("@Configuration - класс конфигурации");
        }
        
        for (Method method : configClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Bean.class)) {
                Bean bean = method.getAnnotation(Bean.class);
                System.out.print("\n@Bean");
                if (!bean.name().isEmpty()) System.out.print(" name=\"" + bean.name() + "\"");
                if (!bean.initMethod().isEmpty()) System.out.print(" initMethod=\"" + bean.initMethod() + "\"");
                if (!bean.destroyMethod().isEmpty()) System.out.print(" destroyMethod=\"" + bean.destroyMethod() + "\"");
                
                if (method.isAnnotationPresent(Scope.class)) {
                    Scope scope = method.getAnnotation(Scope.class);
                    System.out.print(" @Scope(\"" + scope.value() + "\")");
                }
                if (method.isAnnotationPresent(Lazy.class)) {
                    System.out.print(" @Lazy");
                }
                if (method.isAnnotationPresent(Primary.class)) {
                    System.out.print(" @Primary");
                }
                System.out.println(" - метод: " + method.getName());
            }
        }
        System.out.println("\n");
    }
}
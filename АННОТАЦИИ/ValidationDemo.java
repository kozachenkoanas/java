package annotationexamples.custom;

import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotEmpty {
    String message() default "Поле не может быть пустым";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Min {
    int value();
    String message() default "Значение слишком маленькое";
}

class User {
    @NotEmpty(message = "Имя обязательно")
    private String name;
    
    @Min(value = 18, message = "Возраст должен быть 18+")
    private int age;
    
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Validator {
    public static void validate(Object obj) throws Exception {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            
            if (f.isAnnotationPresent(NotEmpty.class)) {
                String value = (String) f.get(obj);
                if (value == null || value.trim().isEmpty()) {
                    throw new Exception(f.getAnnotation(NotEmpty.class).message());
                }
            }
            
            if (f.isAnnotationPresent(Min.class)) {
                int value = (int) f.get(obj);
                Min min = f.getAnnotation(Min.class);
                if (value < min.value()) {
                    throw new Exception(min.message());
                }
            }
        }
    }
}

public class ValidationDemo {
    public static void demo() {
        System.out.println("ВАЛИДАЦИЯ");
        
        try {
            User u1 = new User("John", 25);
            Validator.validate(u1);
            System.out.println("Валидация успешна");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        
        try {
            User u2 = new User("", 15);
            Validator.validate(u2);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println();
    }
}
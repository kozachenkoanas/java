package annotationexamples.spring;

import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Component { 
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Service { 
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Repository { 
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Controller { 
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface RestController { 
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD})
@interface Autowired { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Value { 
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface GetMapping { 
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface PostMapping { 
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface PathVariable { 
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface RequestBody { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Transactional { }

// Классы

@Component
class EmailService {
    public void sendEmail() {
        System.out.println("Sending email...");
    }
}

@Service
class UserService {
    @Autowired
    private EmailService emailService;
    
    @Value("${app.name:DefaultApp}")
    private String appName;
    
    @Transactional
    public void createUser() {
        System.out.println("Creating user...");
        emailService.sendEmail();
        System.out.println("App name: " + appName);
    }
}

@RestController
class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public String getUsers() {
        return "Getting all users";
    }
    
    @PostMapping("/users")
    public String createUser(@RequestBody String user) {
        userService.createUser();
        return "User created: " + user;
    }
    
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable("id") Long id) {
        return "Getting user with id: " + id;
    }
}

@Repository
interface UserRepository {
    void save();
}

public class SpringAnnotationsDemo {
    public static void demo() {
        System.out.println("SPRING ANNOTATIONS");
        
        // Демонстрация стереотипных аннотаций
        Class<?>[] classes = {
            EmailService.class,
            UserService.class,
            UserController.class,
            UserRepository.class
        };
        
        for (Class<?> clazz : classes) {
            System.out.print(clazz.getSimpleName() + " - аннотации: ");
            if (clazz.isAnnotationPresent(Component.class)) {
                Component c = clazz.getAnnotation(Component.class);
                System.out.print("@Component" + (c.value().isEmpty() ? "" : "(\"" + c.value() + "\")") + " ");
            }
            if (clazz.isAnnotationPresent(Service.class)) {
                Service s = clazz.getAnnotation(Service.class);
                System.out.print("@Service" + (s.value().isEmpty() ? "" : "(\"" + s.value() + "\")") + " ");
            }
            if (clazz.isAnnotationPresent(Repository.class)) {
                Repository r = clazz.getAnnotation(Repository.class);
                System.out.print("@Repository" + (r.value().isEmpty() ? "" : "(\"" + r.value() + "\")") + " ");
            }
            if (clazz.isAnnotationPresent(Controller.class)) {
                Controller c = clazz.getAnnotation(Controller.class);
                System.out.print("@Controller" + (c.value().isEmpty() ? "" : "(\"" + c.value() + "\")") + " ");
            }
            if (clazz.isAnnotationPresent(RestController.class)) {
                RestController rc = clazz.getAnnotation(RestController.class);
                System.out.print("@RestController" + (rc.value().isEmpty() ? "" : "(\"" + rc.value() + "\")") + " ");
            }
            System.out.println();
        }
        
        // Демонстрация полей с аннотациями
        System.out.println("\nПоля и методы с аннотациями:");
        for (Field field : UserService.class.getDeclaredFields()) {
            System.out.print("Поле " + field.getName() + ": ");
            if (field.isAnnotationPresent(Autowired.class)) {
                System.out.print("@Autowired ");
            }
            if (field.isAnnotationPresent(Value.class)) {
                Value v = field.getAnnotation(Value.class);
                System.out.print("@Value(\"" + v.value() + "\") ");
            }
            System.out.println();
        }
        
        // Демонстрация методов с аннотациями
        for (Method method : UserController.class.getDeclaredMethods()) {
            System.out.print("Метод " + method.getName() + ": ");
            if (method.isAnnotationPresent(GetMapping.class)) {
                GetMapping gm = method.getAnnotation(GetMapping.class);
                System.out.print("@GetMapping(\"" + gm.value() + "\") ");
            }
            if (method.isAnnotationPresent(PostMapping.class)) {
                PostMapping pm = method.getAnnotation(PostMapping.class);
                System.out.print("@PostMapping(\"" + pm.value() + "\") ");
            }
            if (method.isAnnotationPresent(Transactional.class)) {
                System.out.print("@Transactional ");
            }
            System.out.println();
            
            // Демонстрация параметров
            for (Parameter param : method.getParameters()) {
                System.out.print("  Параметр " + param.getName() + ": ");
                if (param.isAnnotationPresent(PathVariable.class)) {
                    PathVariable pv = param.getAnnotation(PathVariable.class);
                    System.out.print("@PathVariable(\"" + pv.value() + "\") ");
                }
                if (param.isAnnotationPresent(RequestBody.class)) {
                    System.out.print("@RequestBody ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}
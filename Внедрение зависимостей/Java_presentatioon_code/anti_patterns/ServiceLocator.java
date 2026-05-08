package anti_patterns;
import java.util.Map;
import java.util.HashMap;


public class ServiceLocator {
    private static Map<Class<?>, Object> services = new HashMap<>();
    
    public static <T> void register(Class<T> type, T instance){
        services.put(type, instance);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> type) {
        return (T)services.get(type);
    }

    interface Logger {
        void log(String msg);
    }
    
    static class ConsoleLogger implements Logger {
        @Override
        public void log(String msg) { System.out.println("[LOG] " + msg); }
    }
    //Здесь мы испоьзуем ServiceLocator
    static class UserService {
        public void process() {
            Logger logger = ServiceLocator.get(Logger.class);
            logger.log("Processing user");
        }
    }

    public static void demo(){
        System.out.println("\n----ServiceLocator antipattern----");
        ServiceLocator.register(Logger.class, new ConsoleLogger());
        UserService service = new UserService();
        service.process();
    }

}

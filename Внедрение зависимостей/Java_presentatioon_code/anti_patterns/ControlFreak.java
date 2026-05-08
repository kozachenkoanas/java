package anti_patterns;

public class ControlFreak {
    static class EmainSender {
        public void send(String to, String message){
            System.out.println("Sending email to: " + to + ": " + message);
        }
    }

    static class Logger {
        public void log(String message){
            System.out.println("[INFO] " + message);
        }
    }

    //Сам создаёт внутри себя зависимости
    static class UserService{
        private EmainSender emailSender;
        private Logger logger;
        public UserService(){
            //В конструкторе он сам себе делает зависимости
            emailSender = new EmainSender();
            logger = new Logger();
        }

        public void registerUser(String email, String name){
            logger.log("Registering user: " + name);
            emailSender.send(email, "Welcome " + name);            
        }
    }
    public static void demo() {
        System.out.println("\n----Control freak antipattern----");
        UserService service = new UserService();
        service.registerUser("me@mail.com", "Vitya");
        /* Основаня проблема - неудобно менять класс при изменении реализации 
        + неудобно тестировать */
    }
}

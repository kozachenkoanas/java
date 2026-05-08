package injection_types;

public class InjectionDemo {
    interface MessageService {
        void send(String message);
    }
    
    static class EmailService implements MessageService {
        public void send(String message) { 
            System.out.println("Email: " + message); 
        }
    }
    
    static class SmsService implements MessageService {
        public void send(String message) { 
            System.out.println("SMS: " + message); 
        }
    }
    
    //через конструктор
    static class ConstructorExample {
        private final MessageService service;
        public ConstructorExample(MessageService service) {
            this.service = service;
        }
        public void notify(String msg) {service.send(msg);}
    }
    
    //через сеттер (зависимость не обязательна или может меняться)
    static class SetterExample {
        private MessageService service;
        public SetterExample() { }
        
        public void setMessageService(MessageService service) {
            this.service = service;
        }
        
        public void notify(String msg) {
            if (service != null) {
                service.send(msg);
            }
            else {
                System.out.println("No service configured!");
            } 
        }
    }
    
    //через interface injection (используется редко, т.к. нарушают прринцип разделения ответственности (класс начинает зависеть от того, как именно ему будут доставляться зависимости (если через фреймворк)))
    interface Injectable<T> {
        void inject(T dependency);
    }
    
    static class InterfaceExample implements Injectable<MessageService> {
        private MessageService service;
        
        @Override
        public void inject(MessageService service) {
            this.service = service;
        }
        
        public void notify(String msg) {service.send(msg);}
    }

     public static void demo() {
        System.out.println("\nConstructot injection");
        ConstructorExample c1 = new ConstructorExample(new EmailService());
        c1.notify("Email via constructor");
        ConstructorExample c2 = new ConstructorExample(new SmsService());
        c2.notify("SMS via constructor");
        
        System.out.println("\nSetter injection");
        SetterExample s = new SetterExample();
        s.notify("Before setter"); 
        s.setMessageService(new EmailService());
        s.notify("After setter");
        s.setMessageService(new SmsService());
        s.notify("Changed to SMS");
        
        System.out.println("\nInterface injection");
        InterfaceExample i = new InterfaceExample();
        i.inject(new EmailService());
        i.notify("Hello via interface");
        
    }
}

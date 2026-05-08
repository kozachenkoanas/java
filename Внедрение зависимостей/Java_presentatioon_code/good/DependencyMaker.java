package good;

public class DependencyMaker {
    //Пишем две функции для использования в demo()
    public static UserService createEmailUserService() {
        MessageSender emailSender = new EmailSender();
        UserRepository repository = new MemorizedUserRepository();
        return new UserService(repository, emailSender);
    }
    public static UserService createSmsUserService() {
        MessageSender smsSender = new SmsSender();
        UserRepository repository = new MemorizedUserRepository();
        return new UserService(repository, smsSender);
    }

    public static void demo() {
        UserService service = createEmailUserService();
        service.registerUser("bib@mail.com", "Bib");
        UserService smsService = createSmsUserService();
        smsService.registerUser("bob@example.com", "Bob");
        
    }
}

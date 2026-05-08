package good;

//Что хорошо - зависит только от интерфейсов, а не от низкоуровневых реализаций (классов)
public class UserService {
    private final UserRepository repository;
    private final MessageSender messageSender;

    //внедрение зависимостей через конструктор
    public UserService(UserRepository repository, MessageSender messageSender) {
        this.repository = repository;
        this.messageSender = messageSender;
    }

    public void registerUser(String email, String name) {
        if (repository.findByEmail(email) != null) {
            System.out.println("User already exists: " + email);
            return;
        }
        repository.save(email, name);
        System.out.println("User registered: " + email);
        messageSender.send(email, "Hello " + name + "!");
    }
}

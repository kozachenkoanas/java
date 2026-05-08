package good;

public interface UserRepository {
    void save(String email, String name);
    String findByEmail(String email);    
}

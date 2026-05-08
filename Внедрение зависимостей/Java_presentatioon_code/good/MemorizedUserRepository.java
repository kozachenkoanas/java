package good;
import java.util.HashMap;
import java.util.Map;

public class MemorizedUserRepository implements UserRepository{
    private Map<String, String> store = new HashMap<>();    
    @Override
    public void save(String email, String name) {
        store.put(email, name);
        System.out.println("Saved user - {" + email + ": " + name + "}");
    }
    
    @Override
    public String findByEmail(String email) {
        return store.get(email);
    }    
}

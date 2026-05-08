package anti_patterns;

public class ConcreteDependency { //зависимость от конкретной реализации
    //создаём конкретный класс без интерфейса
    static class SqlDatabase {
        public void save(String data) {
            System.out.println("Saving to Sql: " + data);
        }
    }
    //класс userservice жёстко зависим от класса sqldatabase
    static class UserService{
        private SqlDatabase db;
        public UserService(SqlDatabase db){
            this.db = db;
        }
        public void saveUser(String name) {
            db.save("User: " + name);
        }
    }
    public static void demo(){
        System.out.println("\n----ConcreteDependancy antipattern----");
        SqlDatabase db = new SqlDatabase();
        UserService service = new UserService(db);
        service.saveUser("Ivan");
    }
}

package com.example;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JUnitFullTest {

    OrderService service;
    Calculator calculator = new Calculator();
    UserRepository repository = new UserRepository();
    Product product = new Product();

    @BeforeEach
    void init() {
        service = new OrderService();
        System.out.println("Тест начат: объект Service создан");
    }

    @AfterEach
    void cleanUp() {
        repository.deleteAll();
        System.out.println("Тест окончен: база очищена");
    }

    @Test
    @DisplayName("1. Базовая проверка: список заказов пуст")
    void testEmpty() {
        assertTrue(service.getAllOrders().isEmpty(), "Изначально список должен быть пуст");
    }

    @Test
    @DisplayName("2. Проверка чисел: сложение double")
    void shouldCalculateTotal() {
        double result = calculator.sum(10.5, 20.0);
        assertEquals(30.5, result, 0.001); 
    }

    @Test
    @DisplayName("3. Проверка на Null: создание пользователя")
    void shouldCreateUser() {
        User user = repository.save(new User("Alice", "Admin", true));
        assertNotNull(user, "Объект не должен быть null");
        assertNotNull(user.getId(), "ID должен быть сгенерирован");
    }

    @Test
    @DisplayName("4. Проверка исключений: отрицательная цена")
    void shouldFailOnNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            product.setPrice(-100);
        }, "Должно быть выброшено исключение при цене < 0");
    }

    @Test
    @DisplayName("5. Групповая проверка (assertAll): профиль пользователя")
    void shouldUpdateProfile() {
        User user = new User("Oleg", "Dev", true);

        assertAll("Проверка всех полей объекта сразу",
            () -> assertEquals("Oleg", user.getName()),
            () -> assertEquals("Dev", user.getJob()),
            () -> assertTrue(user.isActive())
        );
    }

    @Test
    @Disabled("Функционал оплаты не разработан")
    @DisplayName("6. Отключенный тест")
    void testCryptoPayment() {
        fail("Этот тест не должен был запуститься!");
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "qwerty", "admin"})
    @DisplayName("7. Проверка слишком коротких паролей")
    void testShortPasswords(String password) {
        assertFalse(PasswordValidator.isValid(password), "Пароль '" + password + "' не короткий");
    }

    @ParameterizedTest
    @CsvSource({
        "10, 20, 30",
        "0,  5,  5",
        "-1, 1,  0"
    })
    @DisplayName("8. Параметризованный тест: сложение")
    void testAddition(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }

    

    static class OrderService {
        List<String> getAllOrders() { return Collections.emptyList(); }
    }

    static class Calculator {
        double sum(double a, double b) { return a + b; }
    }

    static class User {
        private String id;
        private String name, job;
        private boolean active;

        User(String name, String job, boolean active) {
            this.name = name; this.job = job; this.active = active;
        }
        String getId() { return id; }
        String getName() { return name; }
        String getJob() { return job; }
        boolean isActive() { return active; }
        void setId(String id) { this.id = id; }
    }

    static class UserRepository {
        User save(User u) { u.setId(UUID.randomUUID().toString()); return u; }
        void deleteAll() {  }
    }

    static class Product {
        void setPrice(int price) {
            if (price < 0) throw new IllegalArgumentException();
        }
    }

    static class PasswordValidator {
        static boolean isValid(String password) {
            return password != null && password.length() > 6;
        }
    }

    static class Cart {
        private List<String> items = new ArrayList<>();
        void add(String item) { items.add(item); }
        int size() { return items.size(); }
    }
}

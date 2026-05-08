import java.util.*;

/*
 *  Set
 * 
 * HashSet      - порядок не гарантируется (самый быстрый)
 * LinkedHashSet- сохраняет порядок вставки (чуть медленнее)
 * TreeSet      - всегда отсортирован (самый медленный, но с доп. методами)
 * 
 * Все имеют одинаковые базовые методы (add, remove, contains...)
 * Разница ТОЛЬКО во внутреннем устройстве и поведении при переборе
 * 
 * ===== СЛОЖНОСТЬ (BIG O) =====
 * 
 * HashSet / LinkedHashSet:
 * - add()    : O(1) в среднем, O(n) в худшем (коллизии)
 * - remove() : O(1) в среднем, O(n) в худшем
 * - contains(): O(1) в среднем, O(n) в худшем
 * - size()   : O(1)
 * - iterator(): O(h/n) где h - емкость таблицы
 * 
 * TreeSet:
 * - add()    : O(log n) - спуск по дереву
 * - remove() : O(log n) - поиск + удаление с балансировкой
 * - contains(): O(log n) - бинарный поиск по дереву
 * - first()/last(): O(1) или O(log n)
 * - ceiling()/floor(): O(log n)
 * - size()   : O(1)
 * 
 * ПАМЯТЬ:
 * - HashSet: меньше всех (только массив корзин)
 * - LinkedHashSet: среднее (массив корзин + связи для порядка)
 * - TreeSet: больше всех (красно-черное дерево с тремя ссылками на узел)
 */
public class SetDemo {
    public static void main(String[] args) {
        
        // ======== 1. БАЗОВЫЕ МЕТОДЫ (ОДИНАКОВЫ ДЛЯ ВСЕХ) ========
        System.out.println("=== 1. БАЗОВЫЕ МЕТОДЫ (одинаково для всех) ===\n");
        
        // Создание 
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();
        
        // Добавление - add()
        // HashSet/LinkedHashSet: O(1) в среднем
        // TreeSet: O(log n)
        hashSet.add("Яблоко");
        hashSet.add("Банан");
        hashSet.add("Апельсин");
 
        
        System.out.println("hashSet: " + hashSet);
        
        // contains() - проверка наличия
        // HashSet/LinkedHashSet: O(1) в среднем
        // TreeSet: O(log n)
        System.out.println("contains('Банан'): " + hashSet.contains("Банан")); 
        System.out.println("contains('Груша'): " + hashSet.contains("Груша")); 
        
        // remove() - удаление
        // HashSet/LinkedHashSet: O(1) в среднем
        // TreeSet: O(log n)
        hashSet.remove("Банан");
        System.out.println("После remove('Банан'): " + hashSet);
        
        // size() и isEmpty() - все O(1)
        System.out.println("size(): " + hashSet.size());
        System.out.println("isEmpty(): " + hashSet.isEmpty());
        
        // Перебор (for-each) - зависит от реализации
        System.out.print("Перебор: ");
        for (String s : hashSet) {
            System.out.print(s + " ");
        }
        System.out.println("\n");
        
        // ======== 2. ГЛАВНОЕ ОТЛИЧИЕ - ПОРЯДОК ЭЛЕМЕНТОВ ========
        System.out.println("=== 2. ГЛАВНОЕ ОТЛИЧИЕ - ПОРЯДОК ЭЛЕМЕНТОВ ===\n");
        
        // Демонстрация с числами
        Set<Integer> nums1 = new HashSet<>(Set.of(5, 2, 8, 1, 9, 3, 7, 4, 6));
        Set<Integer> nums2 = new LinkedHashSet<>(Set.of(5, 2, 8, 1, 9, 3, 7, 4, 6));
        Set<Integer> nums3 = new TreeSet<>(Set.of(5, 2, 8, 1, 9, 3, 7, 4, 6));
        
        System.out.println("\nЧисла:");
        System.out.println("HashSet      : " + nums1 + " (непредсказуемо)");
        System.out.println("LinkedHashSet: " + nums2 + " (порядок вставки)");
        System.out.println("TreeSet      : " + nums3 + " (отсортировано)");
        System.out.println();
        
        // ======== 3. УНИКАЛЬНЫЕ МЕТОДЫ TREESET ========
        System.out.println("=== 3. УНИКАЛЬНЫЕ МЕТОДЫ TREESET  ===\n");
        
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.addAll(Set.of(1, 3, 5, 7, 9, 2, 4, 6, 8, 10));
        System.out.println("TreeSet чисел: " + numbers);
        
        // first() / last() - первый и последний (O(1)
        System.out.println("first(): " + numbers.first()); 
        System.out.println("last(): " + numbers.last());  
        
        // lower() / higher() - строго меньше/больше (O(log n)) (самый большой элемент меньше/самый большой элемент больше)
        System.out.println("lower(5): " + numbers.lower(5));  
        System.out.println("higher(5): " + numbers.higher(5)); 
        
        // floor() / ceiling() - меньше/равно или больше/равно (O(log n))
        System.out.println("floor(5): " + numbers.floor(5));     // 5 (<=5)
        System.out.println("ceiling(5): " + numbers.ceiling(5)); // 5 (>=5)
        System.out.println("floor(11): " + numbers.floor(11));   // 10 (<=11)
        System.out.println("ceiling(11): " + numbers.ceiling(11)); // null
        
        // subSet() - подмножество (O(log n))
        System.out.println("subSet(3, 7): " + numbers.subSet(3, 7)); // [3,4,5,6] 
        System.out.println("subSet(3, false, 7, true): " + numbers.subSet(3, false, 7, true)); // [4,5,6,7]
        
        // headSet() / tailSet() - все до/после
        System.out.println("headSet(5): " + numbers.headSet(5)); // [1,2,3,4] (меньше 5)
        System.out.println("tailSet(5): " + numbers.tailSet(5)); // [5,6,7,8,9,10] (больше или равно 5)
        
        // descendingSet() - обратный порядок
        System.out.println("descendingSet(): " + numbers.descendingSet()); // [10,9,8,7,6,5,4,3,2,1]
        System.out.println();
        
        // ======== 4. ОПЕРАЦИИ НАД МНОЖЕСТВАМИ ========
        System.out.println("=== 4. ОПЕРАЦИИ НАД МНОЖЕСТВАМИ ===");
        
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        Set<Integer> setB = new HashSet<>(Set.of(4, 5, 6, 7, 8));
        
        System.out.println("Множество A: " + setA);
        System.out.println("Множество B: " + setB);
        
        // Объединение (union)
        Set<Integer> union = new HashSet<>(setA);
        union.addAll(setB);
        System.out.println("A или B (объединение): " + union);
        
        // Пересечение (intersection)
        Set<Integer> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);
        System.out.println("A и B (пересечение): " + intersection);
        
        // Разность (difference)
        Set<Integer> difference = new HashSet<>(setA);
        difference.removeAll(setB);
        System.out.println("A \\ B (разность): " + difference);
        
        // Проверка подмножества
        Set<Integer> subset = new HashSet<>(Set.of(1, 2));
        System.out.println("A содержит {1,2}? " + setA.containsAll(subset));
        System.out.println();
        
        // ======== 5. КАСТОМНЫЕ ОБЪЕКТЫ ========
        System.out.println("=== 5. КАСТОМНЫЕ ОБЪЕКТЫ ===");
        
        // HashSet с кастомными объектами
        Set<Person> people = new HashSet<>();
        people.add(new Person("Иван", 25));
        people.add(new Person("Мария", 30));
        people.add(new Person("Иван", 25));
        
        System.out.println("HashSet: " + people);

        
        // TreeSet требует Comparable или Comparator
        Set<Person> sortedPeople = new TreeSet<>(); // Person должен реализовать Comparable
        sortedPeople.add(new Person("Иван", 25));
        sortedPeople.add(new Person("Петр", 30));
        sortedPeople.add(new Person("Анна", 20));
        sortedPeople.add(new Person("Анна", 24));
        
        System.out.println("TreeSet с Person (отсортировано): " + sortedPeople);
        System.out.println();
        
        // ======== 6. NULL В SET ========
        System.out.println("=== 6. NULL В SET ===");
        
        Set<String> hashSetWithNull = new HashSet<>();
        hashSetWithNull.add("A");
        hashSetWithNull.add(null);
        hashSetWithNull.add("B");
        hashSetWithNull.add(null); // второй не добавится
        System.out.println("HashSet с null: " + hashSetWithNull); // null разрешен 
        
        Set<String> linkedSetWithNull = new LinkedHashSet<>();
        linkedSetWithNull.add("A");
        linkedSetWithNull.add(null);
        linkedSetWithNull.add("B");
        linkedSetWithNull.add(null);
        System.out.println("LinkedHashSet с null: " + linkedSetWithNull); // null разрешен
        
        try {
            Set<String> treeSetWithNull = new TreeSet<>();
            treeSetWithNull.add("A");
            treeSetWithNull.add(null); // TreeSet НЕ РАЗРЕШАЕТ null!
        } catch (NullPointerException e) {
            System.out.println("TreeSet.add(null) -> NullPointerException!");
        }
        System.out.println();
    }
}

/**
 * Класс Person для демонстрации работы с HashSet и TreeSet
 */
class Person implements Comparable<Person> {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    // Для HashSet/LinkedHashSet (определяют уникальность)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && name.equals(person.name);
    }
    
     @Override
    public int hashCode() {
        return 31 * name.hashCode() + age;
    }
    
    // Для TreeSet (определяет сортировку)
    @Override
    public int compareTo(Person other) {
        int nameCompare = this.name.compareTo(other.name);
        if (nameCompare != 0) {
            return nameCompare;
        }
        return Integer.compare(this.age, other.age);
    }
    
    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}
import java.util.*;

/**
 *  MAP 
 * 
 * ===== СЛОЖНОСТЬ (BIG O) =====
 * 
 * HashMap/LinkedHashMap:           
 * - put: O(1)                     
 * - get: O(1)                      
 * - remove: O(1)                   
 * - containsKey: O(1)              
 * - containsValue: O(n)   
 *         
 * TreeMap:
 * - put: O(log n)
 * - get: O(log n)
 * - remove: O(log n)
 * - containsKey: O(log n)
 * - containsValue: O(n)
 */
public class MapDemo {
    public static void main(String[] args) {
        
        // ======== 1. СОЗДАНИЕ ========
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();
        
        // ======== 2. ОСНОВНЫЕ МЕТОДЫ (ОДИНАКОВЫ ДЛЯ ВСЕХ) ========
        System.out.println("=== ОСНОВНЫЕ МЕТОДЫ ===\n");
        
        // put() - добавить (O(1) для HashMap, O(log n) для TreeMap)
        hashMap.put("Иван", 25);
        hashMap.put("Катя", 30);
        hashMap.put("Петр", 28);
        hashMap.put("Иван", 26); // замена значения
        
        // get() - получить (O(1) для HashMap, O(log n) для TreeMap)
        System.out.println("get('Иван'): " + hashMap.get("Иван")); // 26
        System.out.println("get('Анна'): " + hashMap.get("Анна")); // null
        
        // getOrDefault() - с значением по умолчанию
        System.out.println("getOrDefault: " + hashMap.getOrDefault("Анна", 0));
        
        // containsKey() - есть ли ключ (O(1) / O(log n))
        System.out.println("containsKey('Мария'): " + hashMap.containsKey("Катя"));
        
        // containsValue() - есть ли значение (O(n) для всех!)
        System.out.println("containsValue(30): " + hashMap.containsValue(30));
        
        // remove() - удалить
        hashMap.remove("Петр");
        System.out.println("После remove: " + hashMap);
        
        // size() и isEmpty()
        System.out.println("size: " + hashMap.size());
        System.out.println("isEmpty: " + hashMap.isEmpty());
        System.out.println();
        
        // ======== 3. ГЛАВНОЕ ОТЛИЧИЕ - ПОРЯДОК ========
        System.out.println("=== ПОРЯДОК ЭЛЕМЕНТОВ ===\n");
        
        Map<String, Integer> hash = new HashMap<>();
        Map<String, Integer> linked = new LinkedHashMap<>();
        Map<String, Integer> tree = new TreeMap<>();
        
        List<String> names = List.of("Иван", "Петр", "Анна", "Катя", "Борис");
        
        for (String name : names) {
            hash.put(name, name.length());
            linked.put(name, name.length());
            tree.put(name, name.length());
        }
        
        System.out.println("HashMap      : " + hash);  // порядок случаен
        System.out.println("LinkedHashMap: " + linked); // порядок вставки
        System.out.println("TreeMap      : " + tree);   // отсортирован
        System.out.println();
        
        // ======== 4. ПЕРЕБОР (3 СПОСОБА) ========
        System.out.println("=== ПЕРЕБОР ===\n");
        
        Map<String, Integer> map = new HashMap<>(Map.of("A", 1, "B", 2, "C", 3));
        
        // Способ 1: entrySet() - пары ключ-значение
        System.out.println("entrySet:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        
        // Способ 2: keySet() - только ключи
        System.out.println("\nkeySet:");
        for (String key : map.keySet()) {
            System.out.println("  " + key + " -> " + map.get(key));
        }
        
        // Способ 3: values() - только значения
        System.out.println("\nvalues:");
        for (Integer value : map.values()) {
            System.out.println("  " + value);
        }
        System.out.println();
        
        // ======== 5. УНИКАЛЬНЫЕ МЕТОДЫ TREEMAP ========
        System.out.println("=== МЕТОДЫ ТОЛЬКО TREEMAP ===\n");
        
        TreeMap<Integer, String> treeMap2 = new TreeMap<>();
        treeMap2.put(10, "A");
        treeMap2.put(20, "B");
        treeMap2.put(30, "C");
        treeMap2.put(40, "D");
        treeMap2.put(50, "E");
        
        System.out.println("TreeMap: " + treeMap2);
        
        // first/last - O(1)
        System.out.println("firstKey(): " + treeMap2.firstKey()); // 10
        System.out.println("lastKey(): " + treeMap2.lastKey());   // 50
        
        // lower/higher - строго меньше/больше (O(log n))
        System.out.println("lowerKey(30): " + treeMap2.lowerKey(30));   // 20
        System.out.println("higherKey(30): " + treeMap2.higherKey(30)); // 40
        
        // floor/ceiling - меньше/равно или больше/равно (O(log n))
        System.out.println("floorKey(25): " + treeMap2.floorKey(25));   // 20
        System.out.println("ceilingKey(25): " + treeMap2.ceilingKey(25)); // 30
        
        // subMap - подмножество (O(log n))
        System.out.println("subMap(20,40): " + treeMap2.subMap(20, 40)); // 20,30
        System.out.println();
        
        // ======== 6. NULL ========
        System.out.println("=== NULL ===\n");
        
        Map<String, Integer> hashNull = new HashMap<>();
        hashNull.put(null, 100);     // null-ключ можно
        hashNull.put("key", null);    // null-значение можно
        System.out.println("HashMap с null: " + hashNull);
        
        try {
            TreeMap<String, Integer> treeNull = new TreeMap<>();
            treeNull.put(null, 100); // TreeMap нельзя
        } catch (NullPointerException e) {
            System.out.println("TreeMap.put(null) -> NullPointerException");
        }
        System.out.println();
        
     
}
}
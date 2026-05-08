import java.util.*;

/**
 * List
 * 
 * ===== ARRAYLIST =====
 * - Внутри: обычный массив (Object[])
 * - Начальная емкость: 10
 * - При заполнении: новый массив в 1.5 раза больше
 * 
 * ===== СЛОЖНОСТЬ (BIG O) =====
 * - add(E) в конец: O(1) амортизированно
 * - add(index, E): O(n) - сдвиг
 * - get(index): O(1)
 * - set(index, E): O(1)
 * - remove(index): O(n) - сдвиг
 * - remove(Object): O(n) - поиск + сдвиг
 * - contains/indexOf: O(n)
 * 
 * ===== LINKEDLIST =====
 * - Внутри: двусвязный список (Node)
 * - Хранит ссылки на первый и последний узлы
 * ОСОБЕННОСТЬ: реализует 2 интерфейса: List И Deque
 * Поэтому у него методов больше, чем у ArrayList
 * 
 * ===== СЛОЖНОСТЬ (BIG O) =====
 * - add(E) в конец: O(1)
 * - add(index, E): O(n) - дойти до позиции
 * - addFirst/addLast: O(1)
 * - get(index): O(n) - пройти по ссылкам
 * - getFirst/getLast: O(1)
 * - remove(index): O(n)
 * - removeFirst/removeLast: O(1)
 * - contains/indexOf: O(n)
 * 
 *  ===== ОПЕРАЦИИ ОЧЕРЕДИ =====
 * - offer/poll/peek: O(1)
 * - push/pop: O(1)
 */
public class ListDemo {
    public static void main(String[] args) {
        
        // ========== ARRAYLIST ==========
        System.out.println("========== ARRAYLIST ==========\n");
        
        // Создание
        List<String> arrayList = new ArrayList<>();
        
        // Добавление O(1)
        arrayList.add("Яблоко");             
        arrayList.add("Банан");                      
        arrayList.add(1, "Апельсин");     
        // O(n)         
        arrayList.addAll(List.of("Груша", "Киви")); 
        System.out.println("После добавлений: " + arrayList);
        
        // Получение и изменение // O(1)
        String first = arrayList.get(0);             
        String old = arrayList.set(1, "Манго");    
        System.out.println("get(0): " + first);
        System.out.println("set(1): заменили '" + old + "' на Манго");
        System.out.println("После замены: " + arrayList);
        
        // Удаление O(n)
        String removed = arrayList.remove(2);        
        boolean isRemoved = arrayList.remove("Яблоко"); 
        System.out.println("remove(2): удалили " + removed);
        System.out.println("remove('Яблоко'): " + isRemoved);
        System.out.println("После удалений: " + arrayList);
        
        // Поиск O(n)
        arrayList.add("Киви");
        arrayList.add("Киви");
        System.out.println("contains('Киви'): " + arrayList.contains("Киви")); 
        System.out.println("indexOf('Киви'): " + arrayList.indexOf("Киви"));   
        System.out.println("lastIndexOf('Киви'): " + arrayList.lastIndexOf("Киви")); 
        
        // Размер
        System.out.println("size(): " + arrayList.size());
        System.out.println("isEmpty(): " + arrayList.isEmpty());
        
        // Работа с емкостью (только для ArrayList)
        ArrayList<String> arrayListWithCapacity = new ArrayList<>(3);
        arrayListWithCapacity.add("A");
        arrayListWithCapacity.add("B");
        arrayListWithCapacity.add("C");
        arrayListWithCapacity.add("D"); // расширение O(n)
        arrayListWithCapacity.trimToSize(); // уменьшить емкость
        arrayListWithCapacity.ensureCapacity(100); // зарезервировать
        
        // Очистка O(n)
        arrayList.clear(); 
        System.out.println("После clear(): " + arrayList);
        System.out.println();
        
        // ========== LINKEDLIST ==========
        System.out.println("========== LINKEDLIST ==========\n");
        
        // Создание
        LinkedList<String> linkedList = new LinkedList<>();
        
        // Методы List (как у ArrayList)
        // O(1)
        linkedList.add("Первый");           
        linkedList.add("Второй");
        // O(n)            
        linkedList.add(1, "Между");          
        System.out.println("После add: " + linkedList);
        // O(n)
        String element = linkedList.get(2);  
        String oldVal = linkedList.set(1, "Новый");
        System.out.println("get(2): " + element);
        System.out.println("get(index 1): " + oldVal);
        System.out.println("После set: " + linkedList);
        // O(n)
        linkedList.poll();
        String removedNode = linkedList.remove(); 
        System.out.println("После remove: " + linkedList);
        
        // Уникальные методы (работа с началом/концом)
        LinkedList<String> deque = new LinkedList<>();
        // O(1)
        deque.add("Средний");
        deque.addFirst("Начало");   
        deque.addLast("Конец");      
        System.out.println("\naddFirst/addLast: " + deque);
        
        System.out.println("getFirst: " + deque.getFirst()); 
        System.out.println("getLast: " + deque.getLast());   
        
        System.out.println("removeFirst: " + deque.removeFirst()); 
        System.out.println("removeLast: " + deque.removeLast());   
        System.out.println("После удалений: " + deque);
        
        // Методы очереди (Queue) - FIFO
        // O(1)
        Queue<String> queue = new LinkedList<>();
        queue.offer("Задача 1");     
        queue.offer("Задача 2");     
        queue.offer("Задача 3");     
        System.out.println("\nОчередь (FIFO): " + queue);
        System.out.println("peek (первый): " + queue.peek());  
        System.out.println("poll (взяли): " + queue.poll());   
        System.out.println("После poll: " + queue);
        
        // Методы стека (Deque) - LIFO
        // O(1)
        Deque<String> stack = new LinkedList<>();
        stack.push("Первый");   
        stack.push("Второй");  
        stack.push("Третий");   
        System.out.println("\nСтек (LIFO): " + stack);
        System.out.println("peek (верх): " + stack.peek()); 
        System.out.println("pop (сняли): " + stack.pop()); 
        System.out.println("После pop: " + stack);
        
        // Поиск  O(n)
        LinkedList<String> searchList = new LinkedList<>(List.of("A", "B", "C", "B", "D"));
        System.out.println("\ncontains('B'): " + searchList.contains("B"));
        System.out.println("indexOf('B'): " + searchList.indexOf("B"));
        System.out.println("lastIndexOf('B'): " + searchList.lastIndexOf("B"));
        
        // ========== КОПИРОВАНИЕ МНОГОМЕРНЫХ СПИСКОВ ==========
        System.out.println("\n========== КОПИРОВАНИЕ МНОГОМЕРНЫХ СПИСКОВ ==========\n");
        
        List<List<Integer>> originalMatrix = new ArrayList<>();
        originalMatrix.add(new ArrayList<>(List.of(1, 2, 3)));
        originalMatrix.add(new ArrayList<>(List.of(4, 5, 6)));
        
        // Поверхностное копирование
        List<List<Integer>> shallowCopy = new ArrayList<>(originalMatrix);
        
        // Глубокое копирование
        List<List<Integer>> deepCopy = new ArrayList<>();
        for (List<Integer> row : originalMatrix) {
            deepCopy.add(new ArrayList<>(row));
        }
        
        System.out.println("Оригинал:     " + originalMatrix);
        System.out.println("shallowCopy:  " + shallowCopy);
        System.out.println("deepCopy:     " + deepCopy);
        
        // Изменяем оригинал
        originalMatrix.get(0).set(0, 999);
        
        System.out.println("\nПосле изменения оригинала[0][0] = 999:");
        System.out.println("Оригинал:     " + originalMatrix);
        System.out.println("shallowCopy:  " + shallowCopy); // изменилось
        System.out.println("deepCopy:     " + deepCopy);    // не изменилось
        
        // ========== ПЕРЕБОР ==========
        System.out.println("\n========== ПЕРЕБОР ==========\n");
        
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        
        System.out.println("for-each:");
        for (Integer num : numbers) System.out.print(num + " ");
        
        System.out.println("\n\nfor по индексу:");
        for (int i = 0; i < numbers.size(); i++) System.out.print(numbers.get(i) + " ");
        
        System.out.println("\n\nлямбда:");
        numbers.forEach(num -> System.out.print(num + " "));
        System.out.println();
    }
}

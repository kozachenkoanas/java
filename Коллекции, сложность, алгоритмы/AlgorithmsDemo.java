import java.util.*;

/**
 * Класс Collections содержит статические методы для работы с коллекциями

 * ===== СЛОЖНОСТЬ АЛГОРИТМОВ =====
 * - sort(): O(n log n) - быстрая сортировка / TimSort
 * - binarySearch(): O(log n) - только для отсортированных списков
 * - reverse(): O(n)
 * - shuffle(): O(n)
 * - min()/max(): O(n)
 * - frequency(): O(n)
 * - copy(): O(n)
 * - fill(): O(n)
 * - rotate(): O(n)
 * - replaceAll(): O(n)
 * - swap(): O(1)
 */
public class AlgorithmsDemo {
    public static void main(String[] args) {
        
        // ======== 1. СОРТИРОВКА ========
        System.out.println("=== 1. СОРТИРОВКА (sort) ===\n");
        
        List<Integer> numbers = new ArrayList<>(List.of(5, 2, 8, 1, 9, 3, 7, 4, 6));
        System.out.println("Исходный: " + numbers);
        
        // Сортировка - O(n log n)
        Collections.sort(numbers);
        System.out.println("После sort(): " + numbers);
        
        // Сортировка в обратном порядке
        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("reverseOrder(): " + numbers);
        
        // Сортировка строк
        List<String> names = new ArrayList<>(List.of("Иван", "Аннастасия", "Петр", "Мария"));
        Collections.sort(names);
        System.out.println("Строки по алфавиту: " + names);
        
        // Сортировка по длине строки
        Collections.sort(names, Comparator.comparing(String::length));
        System.out.println("По длине: " + names);
        System.out.println();
        
        // ======== 2. БИНАРНЫЙ ПОИСК ========
        System.out.println("=== 2. БИНАРНЫЙ ПОИСК (binarySearch) ===\n");
        
        List<Integer> sortedList = new ArrayList<>(List.of(10, 20, 30, 40, 50, 60, 70));
        System.out.println("Отсортированный список: " + sortedList);
        
        // Поиск существующего элемента - O(log n)
        int index = Collections.binarySearch(sortedList, 40);
        System.out.println("binarySearch(40): индекс " + index); // 3
        
        // Поиск несуществующего элемента
        int notFound = Collections.binarySearch(sortedList, 45);
        System.out.println("binarySearch(45): " + notFound); // отрицательное (-5)
        
        // Список должен быть отсортирован
        List<Integer> unsorted = new ArrayList<>(List.of(5, 1, 3, 2, 4));
        int badSearch = Collections.binarySearch(unsorted, 5);
        System.out.println("На несортированном: " + badSearch); 
        System.out.println();
        
        // ======== 3. РЕВЕРС ========
        System.out.println("=== 3. РЕВЕРС (reverse) ===\n");
        
        List<Integer> reverseList = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        System.out.println("Исходный: " + reverseList);
        // Реверс O(n)
        Collections.reverse(reverseList); 
        System.out.println("После reverse(): " + reverseList);
        System.out.println();
        
        // ======== 4. ПЕРЕМЕШИВАНИЕ ========
        System.out.println("=== 4. ПЕРЕМЕШИВАНИЕ (shuffle) ===\n");
        
        List<Integer> shuffleList1 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> shuffleList2 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        System.out.println("Исходный: " + shuffleList1);
        // Перемешивание O(n) - случайный порядок
        Collections.shuffle(shuffleList1); 
        System.out.println("После shuffle(): " + shuffleList1);
        
        // С определенным Random'ом
        Collections.shuffle(shuffleList2, new Random(16)); // воспроизводимое перемешивание
        System.out.println("С Random(16): " + shuffleList2);
        System.out.println();
 
        // ======== 5. MIN / MAX ========
        System.out.println("=== 5. MIN / MAX ===\n");
        
        List<Integer> minMaxList = new ArrayList<>(List.of(42, 17, 89, 3, 56, 91, 28));
        System.out.println("Список: " + minMaxList);
        // Min O(n)
        Integer min = Collections.min(minMaxList); 
        // Max O(n)
        Integer max = Collections.max(minMaxList); 
        System.out.println("min: " + min); 
        System.out.println("max: " + max); 
        
        // С компаратором (по последней цифре)
        Integer minByLastDigit = Collections.min(minMaxList, Comparator.comparing(i -> i % 10));
        System.out.println("min по последней цифре: " + minByLastDigit); // 91 
        System.out.println();
        
        // ======== 6. ЧАСТОТА ========
        System.out.println("=== 6. ЧАСТОТА (frequency) ===\n");
        
        List<String> freqList = new ArrayList<>(List.of("A", "B", "A", "C", "A", "B"));
        System.out.println("Список: " + freqList);
        // Частота O(n)
        int freqA = Collections.frequency(freqList, "A"); 
        int freqB = Collections.frequency(freqList, "B");
        int freqC = Collections.frequency(freqList, "C");
        int freqD = Collections.frequency(freqList, "D");
        
        System.out.println("Частота 'A': " + freqA); 
        System.out.println("Частота 'B': " + freqB); 
        System.out.println("Частота 'C': " + freqC); 
        System.out.println("Частота 'D': " + freqD); 
        System.out.println();
        
        // ======== 7. ЗАПОЛНЕНИЕ ========
        System.out.println("=== 7. ЗАПОЛНЕНИЕ (fill) ===\n");
        
        List<String> fillList = new ArrayList<>(List.of("A", "B", "C", "D", "E"));
        System.out.println("Исходный: " + fillList);
        // Заполнение O(n) - заменяет все элементы
        Collections.fill(fillList, "X"); 
        System.out.println("После fill('X'): " + fillList);
        System.out.println();
        
        // ======== 8. КОПИРОВАНИЕ ========
        System.out.println("=== 8. КОПИРОВАНИЕ (copy) ===\n");
        
        List<String> source = new ArrayList<>(List.of("A", "B", "C", "D", "E"));
        List<String> dest = new ArrayList<>(List.of("1", "2", "3", "4", "5"));
        
        System.out.println("source: " + source);
        System.out.println("dest до: " + dest);
        // Копирование O(n) - копирует source в dest
        Collections.copy(dest, source); //


        System.out.println("dest после copy: " + dest);
        System.out.println("source: " + source);
        
        // ВАЖНО: dest должен быть такого же размера или больше!
        List<String> smallDest = new ArrayList<>(List.of("1", "2"));
        try {
            Collections.copy(smallDest, source);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка: целевой список слишком мал");
        }
        System.out.println();
        
        // ======== 9. ЗАМЕНА ВСЕХ ========
        System.out.println("=== 9. ЗАМЕНА ВСЕХ (replaceAll) ===\n");
        
        List<String> replaceList = new ArrayList<>(List.of("A", "B", "A", "C", "A", "D"));
        System.out.println("Исходный: " + replaceList);
        // Замена  O(n)
        Collections.replaceAll(replaceList, "A", "Z");
        System.out.println("replaceAll('A'->'Z'): " + replaceList);
        System.out.println();
        
        // ======== 10. ВРАЩЕНИЕ ========
        System.out.println("=== 10. ВРАЩЕНИЕ (rotate) ===\n");
        
        List<Integer> rotateList = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        System.out.println("Исходный: " + rotateList);
        // Сдвиг  O(n)
        Collections.rotate(rotateList, 2); 
        System.out.println("rotate(2): " + rotateList);
        
        Collections.rotate(rotateList, -3); 
        System.out.println("rotate(-3): " + rotateList);
        System.out.println();
        
        // ======== 11. ОБМЕН ЭЛЕМЕНТОВ ========
        System.out.println("=== 11. ОБМЕН ЭЛЕМЕНТОВ (swap) ===\n");
        
        List<String> swapList = new ArrayList<>(List.of("A", "B", "C", "D"));
        System.out.println("Исходный: " + swapList);
        // O(1) - обмен элементов
        Collections.swap(swapList, 1, 3); 
        System.out.println("swap(1,3): " + swapList); 
        System.out.println();
        
        // ======== 12. НЕИЗМЕНЯЕМЫЕ КОЛЛЕКЦИИ ========
        System.out.println("=== 12. НЕИЗМЕНЯЕМЫЕ КОЛЛЕКЦИИ ===\n");
        
        List<String> modifiable = new ArrayList<>(List.of("A", "B", "C"));
        List<String> unmodifiable = Collections.unmodifiableList(modifiable);
        
        System.out.println("modifiable: " + modifiable);
        System.out.println("unmodifiable: " + unmodifiable);
        
        modifiable.add("D"); // можно менять исходную
        System.out.println("После изменения исходной unmodifiable: " + unmodifiable); // изменилось
        
        try {
            unmodifiable.add("E"); // ОШИБКА!
        } catch (UnsupportedOperationException e) {
            System.out.println("Нельзя менять unmodifiable список");
        }
        
        // Создание неизменяемой копии 
        List<String> immutable = List.of("X", "Y", "Z"); // полностью неизменяемая
        System.out.println("immutable: " + immutable);
        // immutable.add("W"); // ОШИБКА!
        System.out.println();
        

        // ======== 13. ПРОВЕРКА НА НАЛИЧИЕ ОБЩИХ ЭЛЕМЕНТОВ ========
        System.out.println("=== 14. ОПЕРАЦИИ НАД МНОЖЕСТВАМИ ===\n");
        
        List<Integer> listA = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        List<Integer> listB = new ArrayList<>(List.of(4, 5, 6, 7, 8));
        
        System.out.println("A: " + listA);
        System.out.println("B: " + listB);
        
        // Пересечение (есть ли общие элементы) O(n*m)
        
        boolean hasIntersection = !Collections.disjoint(listA, listB); 
        System.out.println("Есть общие элементы? " + hasIntersection); // true
        
        // Добавление всех элементов из другой коллекции O(m)
        List<Integer> union = new ArrayList<>(listA);
        union.addAll(listB);
        System.out.println("addAll (объединение): " + union);
        
        // Удаление всех из другой коллекции O(n*m)
        List<Integer> difference = new ArrayList<>(listA);
        difference.removeAll(listB);
        System.out.println("removeAll (разность): " + difference);
        
        // Оставить только общие 	O(n*m)
        List<Integer> intersection = new ArrayList<>(listA);
        intersection.retainAll(listB);
        System.out.println("retainAll (пересечение): " + intersection);
    }
}
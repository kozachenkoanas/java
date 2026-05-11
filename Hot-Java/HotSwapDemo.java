//Демонстрация Hot Swap (горячей замены кода) в JVM.

public class HotSwapDemo {

    private static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Демонстрация Hot Swap (горячая замена кода) ===\n");
        System.out.println("JVM запущена. PID: " + ProcessHandle.current().pid());
        System.out.println();
        System.out.println("  1. Этот метод Worker.getMessage() сейчас печатает 'VERSION 1'.");
        System.out.println("  2. Не останавливая программу, изменим строку в методе getMessage()");
        System.out.println("Программа вызывает Worker каждые 2 секунды...\n");

        Worker worker = new Worker();

        // Поток, который постоянно дёргает Worker и показывает результат
        Thread workerThread = new Thread(() -> {
            int callCount = 0;
            while (running) {
                callCount++;
                String result = worker.getMessage();
                System.out.printf("[Вызов #%d] Worker говорит: %s%n", callCount, result);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }, "worker-thread");
        workerThread.setDaemon(true);
        workerThread.start();

        // Ждём ручной остановки
        System.out.println("Нажмите Enter для выхода...");
        try {
            System.in.read();
        } catch (java.io.IOException e) {
            System.err.println("Ошибка при чтении ввода: " + e.getMessage());
        }
        running = false;
        System.out.println("Завершение.");
    }
}

//Класс-мишень для Hot Swap.

class Worker {

    /**
     * Метод, который мы будем менять на лету.
     * Меняется ТОЛЬКО тело метода — возвращаемая строка.
     */
    public String getMessage() {

        return "HOT SWAP";

    }
}

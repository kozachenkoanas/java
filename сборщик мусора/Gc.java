/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gc;


public class Gc {
    static class TestObject {
        private int id;
        
        public TestObject(int id) {
            this.id = id;
            System.out.println("Создан объект " + id);
        }
        
        
        protected void finalize() {
            System.out.println("Уничтожен объект " + id);
        }
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("Объекты с finalize\n");
        
        for (int experiment = 1; experiment <= 3; experiment++) {
            System.out.println("Эксперимент " + experiment + ":");
            
            // Создаем объекты без сохранения ссылок
            for (int i = 1; i <= 3; i++) {
                new TestObject(i);
                Thread.sleep(200);
            }
            
            System.out.println("Вызываем GC");
            System.gc();
            
            Thread.sleep(1000);
            System.out.println();
        }
    }
}

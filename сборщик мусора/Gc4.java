/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;

/**
 *
 * @author Виолетта
 */


   public class GC4 {
    public static void main(String[] args) {
       
        
        
       
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        
        // СОЗДАЕМ ЦИКЛ: A→B→C→A
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeA;
        
        System.out.println("   Цикл создан: A → B → C → A");
       
        
        // доступ
        
        System.out.println("   nodeA.name = " + nodeA.name);
        System.out.println("   nodeA.next.name = " + nodeA.next.name);
        System.out.println("   nodeA.next.next.name = " + nodeA.next.next.name);
        System.out.println("   nodeA.next.next.next.name = " + nodeA.next.next.next.name + " \n");
        
        // УДАЛЯЕМ ВСЕ ССЫЛКИ ИЗ ПРОГРАММЫ
       
        nodeA = null;
        nodeB = null;
        nodeC = null;
        System.out.println("   nodeA = null, nodeB = null, nodeC = null");
        System.out.println("   Объекты стали недоступны для программы\n");
        
        // запускаем GC
   
        System.gc();
        
        try { Thread.sleep(2000); } catch (Exception e) {}
        

    }
}

class Node {
    String name;
    Node next;
    private static int counter = 0;
    private int id;
    private byte[] data = new byte[1024 * 100]; 
    
    public Node(String name) {
        this.name = name;
        this.id = ++counter;
    }
    
   
    protected void finalize() {
        System.out.println("GC УДАЛИЛ объект " + name + " (id=" + id + ") - циклическая ссылка не помешала!");
    }
}

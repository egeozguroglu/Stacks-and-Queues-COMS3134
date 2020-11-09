/** TwoStackQueueTester.java
 * Data Structures in Java (COMS3134) - Fall 2020 - HW2
 * @Author: Ege Ozguroglu (uni: eo2464)
 * @Date: 10/16/2020
 */
public class TwoStackQueueTester{
    
    public static void main(String[] args){
        
        TwoStackQueue q = new TwoStackQueue();
        System.out.println(q.size());
        q.enqueue(1);
        System.out.println(q.size());
        q.enqueue(2);
        System.out.println(q.size());
        q.enqueue(3);
        System.out.println(q.size());
        q.enqueue(4);
        System.out.println(q.size());
        
        System.out.println(q.dequeue());
        System.out.println(q.size());
        System.out.println(q.dequeue());
        System.out.println(q.size());
        System.out.println(q.dequeue());
        System.out.println(q.size());
        System.out.println(q.dequeue());
        System.out.println(q.size());
        System.out.println(q.dequeue());
        System.out.println(q.size());
    }
    
}
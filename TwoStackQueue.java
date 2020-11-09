/** TwoStackQueue.java
 * Data Structures in Java (COMS3134) - Fall 2020 - HW2
 * @Author: Ege Ozguroglu (uni: eo2464)
 * @Date: 10/16/2020
 */
public class TwoStackQueue<T> implements TwoStackQueueInterface<T> {

    // instance variables:
    // s1: input stack - enqueue operations push data here
    private MyStack<T> s1;
    // S2: output stack - dequeue operations pop data from S2
    private MyStack<T> s2;
    // queueSize - number of items in the queue
    private int queueSize;
    
    // Constructor:
    public TwoStackQueue(){
        s1 = new MyStack<T>();
        s2 = new MyStack<T>();
        queueSize = 0;
    }
    
	public void enqueue(T x){
        s1.push(x);
        queueSize++;
    }
    
    // time cost: ocassionally O(N).
	public T dequeue(){
        
        // Checks if the queue is empty.
        if(this.isEmpty()){
            System.err.println("Dequeue can't be performed. Queue is empty.");
            return null;
        }
        else if(s2.isEmpty()){
            // only if s2 (output stack) is empty, pop all items in s1 and push to s2.
            // this reverses the order in s1 and brings the first input on top of s2.
            // FIFO order is therefore preserved. 
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }  
        }
        // perform the dequeue operation by removing the oldest item (on the top of s2).
        T itemRemoved = (T) s2.pop(); 
        // decrement queueSize since an item has been removed from the queue.
        queueSize--;
        return itemRemoved;
    }
    
	public int size(){
        return queueSize;
    }
    
	public boolean isEmpty(){
        if (queueSize == 0){
            return true;
        }
        return false;
    }
}

    
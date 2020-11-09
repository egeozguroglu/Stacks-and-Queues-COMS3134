/** MyStack.java
 * Data Structures in Java (COMS3134) - Fall 2020 - HW2
 * @Author: Ege Ozguroglu (uni: eo2464)
 * @Date: 10/16/2020
 */
public class MyStack<T> implements MyStackInterface<T>{
    
    // instance variables: 
    private T[] theArray;
    private int topOfStack;
    // the number of valid elements in the stack
    private int theSize;
    
    // Constructor: 
    public MyStack(){
        topOfStack = -1;
        theSize = 0;
        theArray = (T[]) new Object[20];
    }
    @SuppressWarnings("unchecked")
    public void push(T x){
        // if theArray.length (capacity) isn't big enough:
        if(theSize + 1 > theArray.length){
            // the underlying array needs to be grown.
            // first, store the array in a temp variable called oldArray: 
            T[] oldArray = theArray;
            // we create a new array that's double theSize.
            // Important: we can't directly instansiate an array of Generic type. Instead,
            // we can create a new [] of Objects, then casting it to Generic type. 
            theArray = (T[]) new Object[2*theSize];
            for(int i = 0; i < theSize; i++){
                theArray[i] = oldArray[i];
            } 
        }
        // add the new item into the underlying array:
        // we add at theSize since we're adding after the last valid item. Then, theSize is incremented.
        theArray[theSize++] = x;
        topOfStack++;
    }
	public T pop(){
        if(!isEmpty()){
            theSize--;
            return theArray[topOfStack--];   
        }
       return null;        
    }
    
	public T peek(){
        if(!isEmpty()){
            return theArray[topOfStack];
        }
        else{
            return null;
        }
        
    }
    
	public boolean isEmpty(){
        if (theSize == 0){
            return true;
        }
        else{
            return false;
        }
        
    }
	public int size(){
        return theSize;
    }
    
}


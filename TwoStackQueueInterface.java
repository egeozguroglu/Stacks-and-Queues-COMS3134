public interface TwoStackQueueInterface<T> {

	public void enqueue(T x);
	public T dequeue();
	public int size();
	public boolean isEmpty();	
}

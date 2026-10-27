public class ArrayQueue<T> implements QueueInterface<T> {
    T[] queue;
    int head;
    int size;

    public ArrayQueue() {
        queue = (T[]) new Object[4];
        head = 0;
        size = 0;
    }
    
    // returns the logical size of the queue
    public int size() {
        return size;
    }    
    
    // tests if this queue is empty
    public boolean empty() {
        return size == 0;
    }
    
    // adds an item onto the rear of this queue
    public T add(T item) {
        // dynamic doubling
        if(size == queue.length) {
            T[] newQueue = (T[]) new Object[size * 2];

            for (int i = 0; i < size; i++) {
                int index = (head + i) % queue.length;
                newQueue[i] = queue[index];
            }

            queue = newQueue;
        }
        
        int index = (head + size) % queue.length;
        queue[index] = item;
        size++;
        return item;
    }
        
    // looks at the object at the front of this queue
    // without removing it from the queue
    public T peek() throws QueueUnderflowException {
        if(size == 0) {
            throw new QueueUnderflowException("There are no items in the Queue");
        }
        return queue[head];
    }
    
    // removes the object at the front of this queue 
    // and returns that object as the value of this function
    public T remove() throws QueueUnderflowException {
        if(size == 0) {
            throw new QueueUnderflowException("There are no items in the Queue");
        }
        
        // dynamic halving
        if(size == queue.length / 4) {
            T[] newQueue = (T[]) new Object[size / 2];

            for (int i = 0; i < size; i++) {
                int index = (head + i) % queue.length;
                newQueue[i] = queue[index];
            }

            queue = newQueue;
        }
        
        T removed = queue[head];
        queue[head] = null;
        head = (head - 1) % queue.length;
        size--;
        return removed;
    }
    
    // removes all of the elements from this stack
    public void clear() {
        queue = (T[]) new Object[4];
        head = 0;
        size = 0;
    }
}

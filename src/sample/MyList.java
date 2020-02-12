package sample;

public class MyList<T> {

    private T[] buffer;
    private static int DEFAULT_SIZE = 10;
    private int size;
    public MyList() {
        buffer = (T[]) new Object[DEFAULT_SIZE];
    }

    public MyList<T> clone() {
        MyList<T> cloneList = new MyList<>();
        for(int i = 0; i < size; i++)
            cloneList.add(buffer[i]);
        return cloneList;
    }
    private void ensureCapacity(int newSize) {
        if (newSize <= buffer.length)
            throw new RuntimeException("New size must greater than the old size.");
        T[] tmp = buffer.clone();
        buffer = (T[]) new Object[newSize];
        System.arraycopy(tmp, 0, buffer, 0, tmp.length);
    }
    public void add(T elm) {
        if (size == buffer.length)
            ensureCapacity(buffer.length * 2);
        buffer[size] = elm;
        size++;
    }

    public boolean remove(T elm) {
        for(int i = 0; i < size; i++)
            if (buffer[i].equals(elm)) // found elm in list
            {
                for (int j = i; j < size - 1; j++)
                    buffer[j] = buffer[j + 1];
                buffer[size - 1] = null;
                size--;
                return true;
            }
        //If elm does not exist in list.
        return false;
    }

    public T get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException();
        return buffer[index];
    }

    public int size() {
        return size;
    }

    public void prettyPrint() {
        String s = "";
        for(int i = 0; i < size; i++)
            s += buffer[i] + " ";
        System.out.println(s);
    }
}

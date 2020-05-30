package datastructure;

/**
 * @author qinzhu
 * @since 2020/5/12
 * 自定义的stack
 */
public class CustomStack<T> {
    private Object[] table = new Object[8];
    /**
     * 下一个元素存放的位置
     */
    private int index = 0;

    public void push(T e) {
        ensure();
        table[index++] = e;
    }

    public T pop() {
        Object top = table[index - 1];
        table[index - 1] = null;
        index--;
        return (T) top;
    }

    public T top() {
        if (index == 0) {
            return null;
        }
        return (T) table[index - 1];
    }

    private void ensure() {
        if (index < table.length) {
            return;
        }
        int newLen = table.length << 1;
        Object[] newTable = new Object[newLen];
        System.arraycopy(table, 0, newTable, 0, table.length);
        table = newTable;
    }
}

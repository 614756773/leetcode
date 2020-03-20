package dataStructure;

/**
 * @Date: 2019/8/19
 * @Author: qinzhu
 * 参考 https://bajdcc.github.io/html/heap.html
 */
public abstract class AbstractHeap<E> {
    private int size = 8;

    private Object[] elements = new Object[size];

    // 下一个元素存放的位置，当前元素的数量
    private int index = 0;

    public void add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("不允许null元素插入");
        }
        if (index >= size) {
            grow();
        }
        elements[index++] = e;
        shiftUp(index - 1, e);
    }

    @SuppressWarnings("unchecked")
    public E take() {
        if (index == 0) {
            throw new IllegalStateException("没有元素存在");
        }
        index--;
        E top = (E) elements[0];
        if (index == 0) {
            elements[index] = null;
            return top;
        } else {
            elements[0] = elements[index];
            elements[index] = null;
            shiftDown();
            return top;
        }
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        return (E) elements[0];
    }

    /**
     * @param i 当前元素的索引
     * @param e 当前元素
     */
    @SuppressWarnings("unchecked")
    private void shiftUp(int i, E e) {
        E parent = (E) elements[(i - 1) / 2];
        while (compare(e, parent)) {
            int pi = (i - 1) / 2;
            elements[pi] = e;
            elements[i] = parent;
            if (pi == 0) {
                break;
            }
            i = pi;
            parent = (E) elements[(pi - 1) / 2];
        }
    }

    @SuppressWarnings("unchecked")
    private void shiftDown() {
        if (index == 1) {
            return;
        }

        int currentIndex = 0;
        int childIndex;
        int left = 1;
        childIndex = elements[left + 1] == null ? left : min(left, left + 1);

        // 把元素向下移动
        while (!compare((E) elements[currentIndex], (E) elements[childIndex])) {
            E tmp = (E) elements[currentIndex];
            elements[currentIndex] = elements[childIndex];
            elements[childIndex] = tmp;
            currentIndex = childIndex;
            left = (childIndex << 1) + 1;
            if (elements[left] == null) {
                break;
            }
            childIndex = elements[left + 1] == null ? left : min(left, left + 1);
        }
    }

    private void grow() {
        if (size > Integer.MAX_VALUE >> 2) {
            size = Integer.MAX_VALUE;
        } else {
            size = size + ((size < 64) ?
                    (size + 2) :
                    (size >> 2));
        }
        Object[] newElements = new Object[size];
        System.arraycopy(elements, 0, newElements, 0, index);
        elements = newElements;
    }

    /**
     * 获取左孩子和右孩子当中值较小一个的索引
     */
    @SuppressWarnings("unchecked")
    private int min(int left, int right) {
        return (compare((E) elements[left], (E) elements[right]) ?
                left :
                right);
    }

    protected abstract boolean compare(E e, E e2);
}

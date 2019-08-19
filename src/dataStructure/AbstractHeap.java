package dataStructure;

/**
 * @Date: 2019/8/19
 * @Author: qinzhu
 * 参考 https://www.cnblogs.com/Elliott-Su-Faith-change-our-life/p/7472265.html
 */
public abstract class AbstractHeap {
    private int size = 10;

    private int[] elements = new int[size];

    // 下一个元素存放的位置
    private int index = 0;

    public void add(int e){
        elements[index++] = e;
        shiftUp(index - 1, e);
    }

    private void shiftUp(int i, int e) {
        int parent = elements[(i - 1) / 2];
    }

    protected abstract boolean compare(int e, int parent);
}

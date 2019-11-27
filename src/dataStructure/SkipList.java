package dataStructure;

import java.util.Random;

/**
 * @author qinzhu
 * @since 2019/11/26
 */
public class SkipList<E extends Comparable<? extends E>> {
    private final int MAX_LEVEL = 5;
    private Random random = new Random();
    private Node head;

    public SkipList() {
        // 初始化时指定链表头，且链表头的层数为最大
        head = new Node(null, MAX_LEVEL);
    }

    public static void main(String[] args) {
        SkipList<Integer> skipList = new SkipList<>();
        skipList.add(3);
        skipList.add(9);
        skipList.add(6);
        skipList.add(7);

        System.out.println(skipList);
        System.out.println("contains 6 : " + skipList.contains(6));
        System.out.println();
    }

    /**
     * 插入示意图：<a href="https://github.com/614756773/image/blob/master/algorithm/skip-list-add.jpg?raw=true"></a>
     * <p>
     *       我的算法不是按照示意图来的，有点小优化，示意图开始是从head[maxLevel - 1]开始扫描，
     *       我的算法是从head[newNodeLevel - 1]开始扫描
     * </p>
     * 1.先确定新节点的层数，比如3
     * 2.从头结点的第3层开始找
     * 3.每找到一层结点符合就把新节点插入到这儿
     */
    public void add(E element) {
        checkNull(element);
        checkDuplicate(element);

        int level = random.nextInt(MAX_LEVEL - 1) + 1;
        Node newNode = new Node(element, level);
        Node curNode = head;
        int curLevel = newNode.level - 1;
        while (curLevel >= 0) {
            // 前移
            while (curNode.next[curLevel] != null && curNode.next[curLevel].data.compareTo(element) < 0) {
                curNode = curNode.next[curLevel];
            }
            // 插入
            newNode.next[curLevel] = curNode.next[curLevel];
            curNode.next[curLevel] = newNode;
            // 下移继续找
            curLevel--;
        }
    }

    public boolean contains(E element) {
        return get(element) != null;
    }

    public Node get(E element) {
        checkNull(element);

        Node curNode = head;
        int level = head.level - 1;
        while (level >= 0) {
            // 为空则下移, 过大则下移
            if (curNode.next[level] == null || curNode.next[level].data.compareTo(element) > 0) {
                level--;
            // 小于则前移
            } else if (curNode.next[level].data.compareTo(element) < 0) {
                curNode = curNode.next[level];
            } else {
                return curNode.next[level];
            }
        }
        return null;
    }

    /**
     * 删除示意图：<a href="https://github.com/614756773/image/blob/master/algorithm/skip-list-remove.jpg?raw=true"></a>
     */
    public void remove(E element) {
        checkNull(element);

        Node target;
        Node curNode = head;
        int level = head.level - 1;
        while (level >= 0) {
            // if null, move down;
            // if big move down;
            if (curNode.next[level] == null || curNode.next[level].data.compareTo(element) > 0) {
                level--;
            // if less, move forward
            } else if (curNode.next[level].data.compareTo(element) < 0) {
                curNode = curNode.next[level];
            } else {
                target = curNode.next[level];
                curNode.next[level] = target.next[level];
                target.next[level] = null;
            }
        }
    }

    @Override
    public String toString() {
        Node curNode = head;
        if (head.next == null) {
            return "no nodes";
        }

        StringBuilder sb = new StringBuilder("nodes:");
        while (curNode.next[0] != null) {
            sb.append(curNode.next[0].data).append(" -> ");
            curNode = curNode.next[0];
        }
        sb.replace(sb.length() - 4, sb.length(), "");
        return sb.toString();
    }

    private void checkNull(E element) {
        if (element == null) {
            throw new RuntimeException("not permit null");
        }
    }

    private void checkDuplicate(E element) {
        if (contains(element)) {
            throw new RuntimeException("not permit duplicate");
        }
    }

    class Node<T> {
        private E data;
        private Node[] next;
        private int level;

        Node(E data, int level) {
            this.data = data;
            this.level = level;
            this.next = new Node[level];
        }
    }
}

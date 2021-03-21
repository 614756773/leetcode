package datastructure;

import java.util.Arrays;

/**
 * @author: hotpot
 * @since: 2021/03/19
 * 并查集
 * 并查集主要用于判断图中是否有环。
 * 并查集主要有两个方法：
 * 1.findRoot(x)  查找某个点的根节点
 * 2.union(x, y)  合并x和y节点，使它们在一颗树上
 * 为了防止并查集退化成一条长链表，所以还需要使用ranks来记录集合的层级，合并两个集合时，
 * 总是将层级更低的集合加入到层级更高的集合中
 */
public class DisjointSet {
    private int[] parent;
    private int[] ranks;

    public DisjointSet(int size) {
        parent = new int[size];
        ranks = new int[size];
        Arrays.fill(parent, -1);
        Arrays.fill(ranks, 0);
    }

    public int findRoot(int x) {
        int root = x;
        while (parent[root] != -1) {
            root = parent[root];
        }
        return root;
    }

    /**
     * @return false表示不需要合并，也就是有环了； true表示进行了合并。
     */
    public boolean union(int x, int y) {
        int rootX = findRoot(x);
        int rootY = findRoot(y);
        if (rootX == rootY) {
            return false;
        }

        if (ranks[rootX] > ranks[rootY]) {
            parent[rootY] = rootX;
        } else if (ranks[rootY] > ranks[rootX]) {
            parent[rootX] = rootY;
        } else {
            parent[rootX] = rootY;
            ranks[rootX]++;
        }
        return true;
    }

    public static void main(String[] args) {
        // 一共有4个点，所以声明的size为5（index为0的地方不用）
        DisjointSet disjointSet = new DisjointSet(5);
        // 如果把{3,4}这条边去掉，就没有环了
        int[][] side = new int[][]{
                {1, 2}, {1, 3},
                {3, 4}, {1, 4}
        };
        for (int i = 0; i < side.length; i++) {
            boolean ans = disjointSet.union(side[i][0], side[i][1]);
            if (!ans) {
                System.out.println("有环");
                return;
            }
        }
        System.out.println("无环");
    }
}

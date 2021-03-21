package com.medium.并查集;

/**
 * @author: hotpot
 * @since: 2021/03/21
 */
public class 字节跳动大闯关 {
    public static void main(String[] args) {
        int[][] relation = new int[][]{
                {0}, {5, 3, 0}, {8, 4, 0}, {9, 0}, {9, 0},
                {3, 0}, {0}, {7, 9, 0}, {0}, {9, 7, 0}};
        UnionFind uf = new UnionFind(10);
        // i代表当前是第几个人
        for (int i = 0; i < relation.length; i++) {
            int[] v = relation[i];
            int j = 0;
            // v[j]的值代表当前人所认识的人，如果值为0表示没有认识的人
            while (v[j] != 0) {
                uf.union(i, v[j]);
                j++;
            }
        }
        System.out.println(uf.count);
    }


    static class UnionFind {
        private int[] parent;
        private int[] ranks;
        // 集合数量
        int count;

        public UnionFind(int n) {
            parent = new int[n];
            ranks = new int[parent.length];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                ranks[i] = 0;
            }
            count = n;
        }

        public int findRoot(int x) {
            int root = x;
            while (parent[root] != root) {
                root = parent[root];
            }
            return root;
        }

        public void union(int x, int y) {
            int rootX = findRoot(x);
            int rootY = findRoot(y);
            if (rootX == rootY) {
                return;
            }
            count--;
            if (ranks[rootX] > ranks[rootY]) {
                parent[rootY] = rootX;
            } else if (ranks[rootY] > ranks[rootX]) {
                parent[rootX] = rootY;
            } else {
                parent[rootX] = rootY;
                ranks[rootY]++;
            }
        }
    }
}

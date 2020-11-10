package other;

/**
 * @author qinzhu
 * @since 2020/11/10
 * 思路：
 * 初始状态下使用一个二维数组e来各个顶点之间的距离，
 * 使用一维数组dis来记录起点到各个顶点之间的记录，
 * 用set记录已确定的dis元素
 * <p>
 * 计算过程中找dis中未确定的并且到顶点最近的元素，然后利用e对其进行松弛
 * <p>
 * 参考：https://wiki.jikexueyuan.com/project/easy-learn-algorithm/dijkstra.html
 */
public class Dijkstra {
    private final int N = 99999;

    /**
     * 下标为0的元素不用
     * e.g.
     * e[1,3]的值为12，表示从顶点1到地点3的距离为12
     */
    private int[][] e = new int[][]{
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 12, N, N, N},
            {0, N, 0, 9, 3, N, N},
            {0, N, N, 0, N, 5, N},
            {0, N, N, 4, 0, 13, 15},
            {0, N, N, N, N, 0, 4},
            {0, N, N, N, N, N, 0}};

    /**
     * 下标为0的元素不用
     */
    private int[] dis = new int[7];

    public static void main(String[] args) {
        int[] result = new Dijkstra().exec();
        for (int i = 1; i < result.length; i++) {
            System.out.println(String.format("起点到【%d】点的最小距离为：%d", i, result[i]));
        }
    }

    public int[] exec() {
        // 初始化dis数组
        for (int i = 1; i < dis.length; i++) {
           dis[i] = e[1][i];
        }

        // 使用hash来记录dis哪些元素是确定值，e.g. hash[3] = 1表示dis[3]的值已经是个确定值。若hash[3] = 0这表示dis[3]的值还需要计算
        int[] hash = new int[dis.length];
        hash[1] = 1;

        // 按道理说需要计算6次（即顶点的个数），但是由于起点到起点的距离是确定所以不需要计算，只用计算5次
        for (int i = 1; i < dis.length - 1; i++) {
            int min = Integer.MAX_VALUE;
            int computeIndex = -1;
            for (int j = 2; j < dis.length; j++) {
                if (hash[j] == 0 & dis[j] < min) {
                    min = dis[j];
                    computeIndex = j;
                }
            }
            // dis[computeIndex]值已经确定是最小的了，所以使用hash标记一下
            hash[computeIndex] = 1;

            // 进行松弛操作
            for (int j = 1; j < dis.length; j++) {
                if (e[computeIndex][j] + dis[computeIndex] < dis[j]) {
                    dis[j] = e[computeIndex][j] + dis[computeIndex];
                }
            }
        }
        return dis;
    }
}

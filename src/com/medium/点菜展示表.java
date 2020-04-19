package com.medium;

import java.util.*;

/**
 * @author hotpot
 * @since 2020-04-19 11:15:00
 * 思路：
 * 先遍历一遍订单，把以下三个元素存储
 * 1.`桌号`：使用TreeSet存储起来，按照字母的数字值排序
 * 2.`菜名`使用TreeSet存储起来，按照字母排序
 * 3.使用map缓存点菜的次数，key为桌号+菜名，value为被点的次数
 * 最后把这三个元素组装成结果就可以了
 */
public class 点菜展示表 {
    private TreeSet<String> tableSet;

    private Map<String, Integer> tableDishMap = new HashMap<>();

    private TreeSet<String> dishSet;

    public List<List<String>> displayTable(List<List<String>> orders) {
        dishSet = new TreeSet<>((o1, o2) -> o1.compareTo(o2));
        tableSet = new TreeSet<>((a, b) -> Integer.valueOf(a).compareTo(Integer.valueOf(b)));

        for (List<String> order : orders) {
            String table = order.get(1);
            String dish = order.get(2);
            tableSet.add(table);
            String key = table + dish;
            Integer count = tableDishMap.getOrDefault(key, 0);
            tableDishMap.put(key, count + 1);
            dishSet.add(dish);
        }

        List<List<String>> ans = new ArrayList<>();
        List<String> title = new ArrayList<>(1 + dishSet.size());
        title.add("Table");
        title.addAll(dishSet);
        ans.add(title);

        tableSet.forEach(tableNo -> {
            List<String> row = new ArrayList<>(1 + dishSet.size());
            row.add(tableNo);
            dishSet.forEach(dish -> {
                row.add(tableDishMap.getOrDefault(tableNo + dish, 0) + "");
            });
            ans.add(row);
        });

        return ans;
    }
}

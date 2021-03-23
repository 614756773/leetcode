package datastructure;

import java.util.HashMap;

/**
 * @author: hotpot
 * @since: 2021/03/23
 * 字典树
 */
public class Trie {
    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("hello");
        t.insert("hello world");
        t.insert("hello hmm");
        System.out.println(t.searchPrefix("hello"));
        System.out.println(t.searchPrefix("hmm"));
    }

    private Node root = new Node();

    public void insert(String str) {
        Node node = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch);
            }
            node = node.get(ch);
        }
        node.setEnd(true);
    }

    public boolean search(String str) {
        Node node = searchPrefix(str);
        return node != null && node.isEnd();
    }

    public Node searchPrefix(String strPrefix) {
        Node node = root;
        for (int i = 0; i < strPrefix.length(); i++) {
            char ch = strPrefix.charAt(i);
            if (!node.containsKey(ch)) {
                return null;
            }
            node = node.get(ch);
        }
        return node;
    }

    static class Node {
        // node[0]代表当前字符为a
        // node[1]代表当前字符为b
        // ...
        private HashMap<Character, Node> map = new HashMap<>();

        private boolean isEnd = false;

        public void put(char ch) {
            if (containsKey(ch)) {
                return;
            }
            map.put(ch, new Node());
        }

        public Node get(char ch) {
            return map.get(ch);
        }

        public boolean containsKey(char ch) {
            return map.containsKey(ch);
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }
    }
}

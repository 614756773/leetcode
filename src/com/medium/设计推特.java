package com.medium;

import java.util.*;

/**
 * @author hotpot
 * @since 2020-04-13 22:16:28
 * 思路：暴力方法，使用堆来存储用户以及用户的关注者的消息，然后取堆顶的10个元素
 */
public class 设计推特 {
    private static Integer TIME = 0;
    /**
     * 键为用户id，值为关注的id
     */
    private Map<Integer, List<Integer>> userMap;

    /**
     * 键为用户id，值为该用户的推文id（越新的推文越靠前）
     */
    private Map<Integer, LinkedList<Message>> tweetMap;

    /** Initialize your data structure here. */
    public 设计推特() {
        userMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    /**
     * new tweet
     */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new ArrayList<>());
        }

        LinkedList<Message> tweets = tweetMap.computeIfAbsent(userId, k -> new LinkedList<>());
        tweets.addFirst(new Message(tweetId));
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        if (!userMap.containsKey(userId)) {
            return new ArrayList<>();
        }

        LinkedList<Message> ownMessages = tweetMap.getOrDefault(userId, new LinkedList<>());
        List<Integer> followers = userMap.getOrDefault(userId, new ArrayList<>());
        PriorityQueue<Message> queue = new PriorityQueue<>();
        ownMessages.forEach(e -> queue.offer(e));
        for (Integer uid : followers) {
            LinkedList<Message> messages = tweetMap.get(uid);
            if (messages == null || messages.isEmpty()) {
                continue;
            }
            messages.forEach(m -> queue.offer(m));
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message msg = queue.poll();
            if (msg == null) {
                break;
            }
            ans.add(msg.id);
        }
        return ans;
    }

    /**
     * Follower follows a followee.
     * If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }

        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new ArrayList<>());
        }

        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new ArrayList<>());
        }

        List<Integer> follwers = userMap.get(followerId);
        if (follwers.contains(followeeId)) {
            return;
        }
        follwers.add(followeeId);
    }

    /**
     * Follower unfollows a followee.
     * If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followeeId == followerId) {
            return;
        }

        if (!userMap.containsKey(followeeId)) {
            return;
        }

        List<Integer> followers = userMap.get(followerId);
        if (followers == null || followers.isEmpty()) {
            return;
        }
        followers.removeIf(e -> e == followeeId);
    }

    static class Message implements Comparable{
        private Integer id;
        private Integer time;

        public Message(Integer id) {
            this.id = id;
            this.time = ++TIME;
        }

        @Override
        public int compareTo(Object o) {
            Message another = (Message) o;
            return another.time - time;
        }
    }
}

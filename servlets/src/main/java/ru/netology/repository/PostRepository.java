package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    private ConcurrentHashMap<Long, Post> map = new ConcurrentHashMap<>();
    private AtomicLong count = new AtomicLong(map.size());

    public Map<Long, Post> all() {
        return map;
    }

    public Optional<Post> getById(long id) {
        if (map.containsKey(id)) {
            return Optional.of(map.get(id));
        }
        return Optional.empty();
    }

    public Post save(Post post) {
        if (map.containsKey(post.getId())) {
            map.put(post.getId(), new Post(post.getId(), post.getContent()));
            return map.get(post.getId());
        }
        long tmpCount = count.incrementAndGet();
        map.put(tmpCount, new Post(tmpCount, post.getContent()));
        return map.get(tmpCount);
    }

    public void removeById(long id) {
        if (map.containsKey(id)) {
            map.remove(id);
        }
    }
}

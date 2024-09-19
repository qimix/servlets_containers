package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;

// Stub
public class PostRepository {
    Map<Long, Post> map = Collections.synchronizedMap(new HashMap<>());
    long count = map.size();

    public  Map<Long, Post> all() {
        return map;
    }

    public Optional<Post> getById(long id) {
        if(map.containsKey(id)){
            return Optional.of(map.get(id));
        }
        return Optional.empty();
    }

    public Post save(Post post) {
        if(map.containsKey(post.getId())) {
            map.put(post.getId(), new Post(post.getId(), post.getContent()));
            return map.get(post.getId());
        }
        long tmpcount = count++;
        map.put(tmpcount, new Post(tmpcount, post.getContent()));
        return map.get(tmpcount);
    }

    public void removeById(long id) {
        if(map.containsKey(id)){
            map.remove(id);
        }
    }
}

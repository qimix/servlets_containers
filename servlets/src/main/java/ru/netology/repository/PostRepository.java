package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Stub
public class PostRepository {
    List<Post> list = Collections.synchronizedList(new ArrayList<Post>());
    long count = list.size();

    public List<Post> all() {
        return list;
    }

    public Optional<Post> getById(long id) {
        for (Post i : list) {
            if (i.getId() == id) {
                return Optional.of(i);
            }
            ;
        }
        return Optional.empty();
    }

    public Post save(Post post) {
        for (Post i : list) {
            if (i.getId() == post.getId()) {
                Post old_post = new Post(i.getId(), post.getContent());
                list.add(old_post);
                return old_post;
            }
        }
        ;
        Post new_post = new Post(count++, post.getContent());
        list.add(new_post);
        return new_post;
    }

    public void removeById(long id) {
    }
}

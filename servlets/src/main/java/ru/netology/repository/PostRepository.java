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
        for (int i = 0; i < list.size(); i++) {
            Post tmp_post = list.get(i);
            if (post.getId() == tmp_post.getId()) {
                list.set(i, post);
                return list.get(i);
            }
        }
        ;
        list.add(new Post(count++, post.getContent()));
        return list.get(list.size() - 1);
    }

    public void removeById(long id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                return;
            }
        }
    }
}

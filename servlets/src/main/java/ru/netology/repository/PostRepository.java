package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Stub
public class PostRepository {
  List<Post> list = Collections.synchronizedList(new ArrayList<Post>());
  long count;
  public List<Post> all() {
    return list;
  }

  public Optional<Post> getById(long id) {
    for(Post i : list){
      if(i.getId() == id){
        return Optional.of(i);
      };
    }
    return Optional.empty();
  }

  public Post save(Post post) {
    for(Post i : list){
      if(i.getId() == post.getId()){
        list.set(i.getId(), post.getContent());
      }
    };
    return post;
  }

  public void removeById(long id) {
  }
}

package tn.esprit.pidev.consommitounsi.services.forum;

import tn.esprit.pidev.consommitounsi.entities.forum.Post;

import java.util.List;

public interface IPostService {
    void addPost(Post p, long userId, long topicId);
    void updatePost(Post p);
    Post getPostById(long id);
    List<Post> getAllPosts();
    List<Post> getAllPostsOrderedByDate(long topicId);
    void deletePost(long id);
    void likePost(long postId, long userId, boolean like);
    String getForbiddenWords();
    void setForbiddenWords(String words);
}

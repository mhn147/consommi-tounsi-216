package tn.esprit.pidev.consommitounsi.services.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.forum.Post;
import tn.esprit.pidev.consommitounsi.entities.forum.PostLike;
import tn.esprit.pidev.consommitounsi.entities.forum.Topic;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;
import tn.esprit.pidev.consommitounsi.repositories.forum.PostLikeRepository;
import tn.esprit.pidev.consommitounsi.repositories.forum.PostRepository;
import tn.esprit.pidev.consommitounsi.repositories.forum.TopicRepository;

import java.util.Date;
import java.util.List;

@Service
public class PostService implements IPostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostLikeRepository postLikeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TopicRepository topicRepository;

    public void addPost(Post p, long userId, long topicId) {
        User user = userRepository.findById(userId).orElse(null);
        Topic topic = topicRepository.findById(topicId).orElse(null);
        if (user!=null && topic!=null) {
            p.setUser(user);
            p.setTopic(topic);
            p.setDate(new Date());
            postRepository.save(p);
        }
    }

    public void updatePost(Post p) {
        Post post = postRepository.findById(p.getId()).orElse(null);
        if (post!=null) {
            p.setLikes(post.getLikes());
            p.setTopic(post.getTopic());
            p.setUser(post.getUser());
            p.setDate(post.getDate());
            postRepository.save(p);
        }
    }

    public Post getPostById(long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> getAllPostsOrderedByDate(long topicId) {
        return postRepository.getAllPostsOrderedByDate(topicId);
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    public void likePost(long postId, long userId, boolean like) {
        PostLike pl = postLikeRepository.findPostLike(postId, userId);
        if (pl!=null) {
            if (pl.isLiked()==like)
                postLikeRepository.deleteById(pl.getId());
            else {
                pl.setLiked(like);
                postLikeRepository.save(pl);
            }
        } else {
            User user = userRepository.findById(userId).orElse(null);
            Post post = postRepository.findById(postId).orElse(null);
            if (user!=null && post!=null) {
                pl=new PostLike();
                pl.setPost(post);
                pl.setUser(user);
                pl.setLiked(like);
                postLikeRepository.save(pl);
            }
        }
    }
}

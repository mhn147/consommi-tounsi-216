package tn.esprit.pidev.consommitounsi.services.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.User;
import tn.esprit.pidev.consommitounsi.entities.products.Comment;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.repositories.UserRepository;
import tn.esprit.pidev.consommitounsi.repositories.products.CommentRepository;
import tn.esprit.pidev.consommitounsi.repositories.products.ProductRepository;

import java.util.Date;
import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public void addComment(Comment c, long userId, long productId) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = ProductRepository.findById(productId).orElse(null);
        c.setContent(String.join(" ", c.getContent()));
        c.setUser(user);
        c.setProduct(product);
        c.setDate(new Date());
        commentRepository.save(c);

    }


    public void updateComment(Comment c) {
        Comment comment = commentRepository.findById(c.getId()).orElse(null);
        if (comment != null) {

            c.setProduct(comment.getProduct());
            c.setUser(comment.getUser());
            c.setDate(comment.getDate());
            commentRepository.save(c);
        }
    }

        public Comment getCommentById(long id) {
            return commentRepository.findById(id).orElse(null);
        }

    public List<Comment> getAllCommentsOrderedByDate(long topicId) {
        return commentRepository.getAllCommentsOrderedByDate(productId);
    }

    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

}

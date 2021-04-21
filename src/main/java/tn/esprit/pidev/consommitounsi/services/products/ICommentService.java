package tn.esprit.pidev.consommitounsi.services.products;

import tn.esprit.pidev.consommitounsi.entities.products.Comment;

import java.util.List;

public interface ICommentService {
    void addComment(Comment c, long userId, long productId);
    void updateComment(Comment c);
    Comment getCommentById(long id);
    List<Comment> getAllCommentsOrderedByDate(long productId);
    void deleteComment(long id);


}

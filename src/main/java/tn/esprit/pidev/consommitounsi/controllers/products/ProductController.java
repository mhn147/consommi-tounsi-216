package tn.esprit.pidev.consommitounsi.controllers.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.forum.Topic;
import tn.esprit.pidev.consommitounsi.entities.products.Comment;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.entities.products.ProductRating;
import tn.esprit.pidev.consommitounsi.services.forum.TopicService;
import tn.esprit.pidev.consommitounsi.services.products.CommentService;
import tn.esprit.pidev.consommitounsi.services.products.ProductService;
import tn.esprit.pidev.consommitounsi.utils.UserSession;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class ProductController {


    @Autowired
    ProductService productService;
    @Autowired
    CommentService commentService;

    @GetMapping("/products/ordered/")
    // url + ?order=popularity&take=2
    private List<Product>getAllProductsOrdered(@RequestParam(required = false) String order,
                                               @RequestParam(required = false) int take)
    {
            List<Product> products = null;

            if (order == null)
            {
                return this.productService.getAllProducts(null, null, null);
            }

            if (order.equals("popularity"))
            {
                products = TopicService.orderByPopularity(productService.getAllProducts(null,null,null),
                        new int[]{1,2},
                        p->(double)p.getComments().size(),
                        p->p.getRatings().stream().mapToDouble(ProductRating::getStarRate).average().orElse(0));
            }
            if (take > 0 && take < products.size())
            {
                products  = products.stream().limit(take).collect(Collectors.toList());
            }

            return products;
         //return productService.getAllProducts(q,min,max);
    }

    //@GetMapping("/products")
    private List<Product>getAllProducts(@RequestParam (required = false) Double min,
                                        @RequestParam(required = false)Double max,
                                        @RequestParam(required = false) String q)
    {
        try {
            return productService.getAllProducts(q,min,max);
        }
        catch (Exception ex) {
            return null;
        }
    }


    @GetMapping("/products")
    public List<Product> getAll() {
        return this.productService.getAllProducts(null, null, null);
    }

    @GetMapping("/products/{id}")
   public Product getProduct(@PathVariable("id") Long id)
    {

        return productService.getProductById(id);
    }

    @DeleteMapping ("/products/{id}")
    private void deleteProduct(@PathVariable("id") Long id)
    {
        productService.delete(id);
    }

    //creating post mapping that post the prod detail in the database
    @PostMapping("/products")
    private long saveProduct(@RequestBody Product product)
    {
        productService.saveOrUpdate(product);
        return  product.getId();
    }

    //creating put mapping that updates the prod detail
    @PutMapping("/products/{id}")
    private Product update(@RequestBody Product product)
    {
        productService.saveOrUpdate(product);
        return product;
    }


    //hedhy
    @PostMapping("/comments/{userId}/{productId}")
    @ResponseBody
    public void addComment(@RequestBody Comment c,
                           @PathVariable("userId") long userId,
                           @PathVariable("productId") long productId)
    {
        commentService.addComment(c ,userId,productId);
        //if (UserSession.hasId(userId))
          //  commentService.addComment(c, userId, productId);
    }

    @PostMapping("/comments/edit")
    @ResponseBody
    public void updateComment(@RequestBody Comment c) {
        Comment comment=commentService.getCommentById(c.getId());
        if (comment!=null && (UserSession.hasId(comment.getUser().getId())))
            commentService.updateComment(c);
    }

    @GetMapping("/comments/{id}")
    @ResponseBody
    public Comment getCommentById(@PathVariable("id") long id) {
        return commentService.getCommentById(id);
    }

    @DeleteMapping("/customer/comments/{id}")
    @ResponseBody
    public void deleteComment(@PathVariable("id") long id) {
        Comment c = commentService.getCommentById(id);
        if (c!=null&&(UserSession.hasId(c.getUser().getId())||UserSession.isAdmin()))
            commentService.deleteComment(id);
    }



    //hedhy
    @PutMapping ("/customer/{userId}/products/{productId}/ratings/{rating}")
    @ResponseBody
    public void addRateProduct(@PathVariable("productId")long productId,
                                        @PathVariable("userId")long userId,
                                        @PathVariable("rating") int rating) {
        productService.addRateProduct(productId,userId,rating);
    }
       //  if (UserSession.hasId(userId)) {
//             try {
//                 this.productService.addRateProduct(productId, userId, rating);
//             } catch (IllegalArgumentException ex) {
//                 // this.productService.updateRate(...);
//             }
           //  return this.productService.addRateProduct(productId, userId, rating);
         //}
        // return null;
//    }
       @PutMapping("/customer/comments/{commentId}/{userId}/{like}")
       @ResponseBody
       public void likeComment(@PathVariable("commentId")long commentId,
                               @PathVariable("userId")long userId,
                               @PathVariable("like")boolean like)
       {
           commentService.likeComment(commentId, userId, like);
       }



}

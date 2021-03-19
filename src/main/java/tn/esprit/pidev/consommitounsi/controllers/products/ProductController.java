package tn.esprit.pidev.consommitounsi.controllers.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.products.Comment;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.services.products.CommentService;
import tn.esprit.pidev.consommitounsi.services.products.ProductService;

import java.util.List;

@RestController
@RequestMapping
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping("/products")
    private List<Product>getAllProducts(@RequestParam (required = false) Double min,@RequestParam(required = false)Double max,
                                        @RequestParam(required = false) String q )
    {
         return productService.getAllProducts(q,min,max);

    }

    @GetMapping("/products/{id}")
    private Product getProduct(@PathVariable("productid") Long id)
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
    @PutMapping("/products")
    private Product update(@RequestBody Product product)
    {
        productService.saveOrUpdate(product);
        return product;
    }


    //hedhy
    @PostMapping("/customer/comments/{userId}/{productId}")
    @ResponseBody
    public void addComment(@RequestBody Comment c, @PathVariable("userId") long userId, @PathVariable("productId") long productId) {
        if (UserSession.hasId(userId))
            CommentService.addComment(c, userId, productId);
    }

    @PostMapping("/customer/comments/edit")
    @ResponseBody
    public void updateComment(@RequestBody Comment p) {
        Comment comment=CommentService.getCommentById(p.getId());
        if (comment!=null && (UserSession.hasId(comment.getUser().getId())||UserSession.isAdmin()))
            commentService.updateComment(c);
    }

    @GetMapping("/comments/{id}")
    @ResponseBody
    public Comment getCommentById(@PathVariable("id") long id) {
        return commentService.getPostById(id);
    }

    @DeleteMapping("/customer/comments/{id}")
    @ResponseBody
    public void deleteComment(@PathVariable("id") long id) {
        Comment c = commentService.getCommentById(id);
        if (c!=null&&(UserSession.hasId(c.getUser().getId())||UserSession.isAdmin()))
            commentService.deleteComment(id);
    }


}

package tn.esprit.pidev.consommitounsi.services.products;

import tn.esprit.pidev.consommitounsi.entities.products.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct();
    Product getProductById(Long id);
    void saveOrUpdate(Product product);
    void delete( Long id);
    void update(Product product, Long id);
    public  List<Product> search (double min, double max);
}

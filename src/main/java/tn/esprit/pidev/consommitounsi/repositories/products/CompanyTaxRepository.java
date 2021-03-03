package tn.esprit.pidev.consommitounsi.repositories.products;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.pidev.consommitounsi.entities.advertisements.Advertisement;
import tn.esprit.pidev.consommitounsi.entities.products.CompanyTax;

public interface CompanyTaxRepository extends CrudRepository<CompanyTax,Long> {
}

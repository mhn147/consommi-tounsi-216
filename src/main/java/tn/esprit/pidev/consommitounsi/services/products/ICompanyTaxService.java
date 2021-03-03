package tn.esprit.pidev.consommitounsi.services.products;

import tn.esprit.pidev.consommitounsi.entities.products.CompanyTax;

import java.util.List;

public interface ICompanyTaxService {
    List<CompanyTax> getAllCompanyTax();
    CompanyTax getCompanyTaxById(Long id);
    void saveOrUpdate(CompanyTax companyTax);
    void delete( Long id);
    void update(CompanyTax companyTax, Long id);
}

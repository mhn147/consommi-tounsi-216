package tn.esprit.pidev.consommitounsi.services.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.advertisements.Advertisement;
import tn.esprit.pidev.consommitounsi.entities.products.CompanyTax;
import tn.esprit.pidev.consommitounsi.repositories.products.AdvertisementRepository;
import tn.esprit.pidev.consommitounsi.repositories.products.CompanyTaxRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyTaxService implements ICompanyTaxService {
    @Autowired
   CompanyTaxRepository companyTaxRepository;

    //getting all ads record by using the method findaAll() of CrudRepository
    public List<CompanyTax> getAllCompanyTax()
    {
        List<CompanyTax> companyTax = new ArrayList<CompanyTax>();
        companyTaxRepository.findAll().forEach(companyTax1-> companyTax.add(companyTax1));
        return companyTax;

    }
    //getting a specific record by using the method findById() of CrudRepository
    public CompanyTax getCompanyTaxById(Long id)
    {
        return companyTaxRepository.findById(id).get();
    }

    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(CompanyTax companyTax)
    {
        companyTaxRepository.save(companyTax);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository

    public void delete( Long id)
    {
        companyTaxRepository.deleteById(id);
    }

    //updating a record
    public void update(CompanyTax companyTax, Long id)
    {
        companyTaxRepository.save(companyTax);
    }


}

package tn.esprit.pidev.consommitounsi.controllers.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.advertisements.Advertisement;
import tn.esprit.pidev.consommitounsi.entities.products.CompanyTax;
import tn.esprit.pidev.consommitounsi.services.products.AdvertisementService;
import tn.esprit.pidev.consommitounsi.services.products.CompanyTaxService;

import java.util.List;

@RestController
@RequestMapping
public class CompanyTaxController {
    @Autowired
    CompanyTaxService companyTaxService;

    @GetMapping("/companytaxs")
    private List<CompanyTax> getAllCompanyTaxs()
    {
        return companyTaxService.getAllCompanyTax();

    }

    @GetMapping("/companytaxs/{id}")
    private CompanyTax getCompanyTax(@PathVariable("companytaxid") Long id)
    {
        return companyTaxService.getCompanyTaxById(id);
    }


    @DeleteMapping("/companytaxs/{id}")
    private void deleteCompanytax(@PathVariable("id") Long id )
    {
        companyTaxService.delete(id);
    }

    //creating post mapping that post the ad detail in the database
    @PostMapping("/companytaxs")
    private long saveCompanyTax(@RequestBody CompanyTax companyTax)
    {
        companyTaxService.saveOrUpdate(companyTax);
        return  companyTax.getId();
    }

    //creating put mapping that updates the ad detail
    @PutMapping("/companytaxs/{id}")
    private CompanyTax update(@RequestBody CompanyTax companyTax)
    {
        companyTaxService.saveOrUpdate(companyTax);
        return companyTax;
    }
}

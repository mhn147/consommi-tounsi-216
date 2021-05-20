package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.products.CompanyTax;
import tn.esprit.pidev.consommitounsi.entities.products.ValueNature;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.ITaxesService;

import java.util.List;

@Service
public class TaxesService implements ITaxesService {
    @Override
    public double calculateTaxes(List<Item> items) {
        double taxTotal = 0;
        for (Item item: items) {
            if (item.getProduct().getTaxes() != null) {
                for (CompanyTax tax: item.getProduct().getTaxes()) {
                    if (tax.getTaxType() == ValueNature.FIXED) {
                        taxTotal += tax.getTaxValue();
                    } else if (tax.getTaxType() == ValueNature.RATE) {
                        taxTotal += item.getProduct().getPrice() * tax.getTaxValue();
                    }
                }
            }
        }
        return taxTotal;
    }

    @Override
    public double calculateSubTotal(List<Item> items) {
        double subTotal = 0;
        for (Item item: items) {
            subTotal += item.getProduct().getPrice();
        }
        return subTotal;
    }
}

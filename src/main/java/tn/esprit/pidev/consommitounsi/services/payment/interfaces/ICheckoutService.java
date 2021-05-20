package tn.esprit.pidev.consommitounsi.services.payment.interfaces;

import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.models.payment.PaymentModel;

public interface ICheckoutService {
    Order createOnlineOrder(PaymentModel payment);
    Order createAtDeliveryOrder(PaymentModel payment);
}

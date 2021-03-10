package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;
import tn.esprit.pidev.consommitounsi.repositories.payment.IInvoiceRepository;
import tn.esprit.pidev.consommitounsi.repositories.payment.IOrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService extends OrderService implements ICartService {

    public CartService(IOrderRepository orderRepository) {
        super(orderRepository);
    }


    @Override
    public Order addItem(long cartId, Item item) {
        Order cart = super.getById(cartId);
        List<Item> cartItems = cart.getItems();
        cartItems.add(item);
        cart.setItems(cartItems);
        return super.addOrUpdate(cart);
    }

    @Override
    public Order updateItem(Item item) {
        return null;
    }

    @Override
    public Order removeItem(Item item) {
        return null;
    }

    @Override
    public Order confirmCart() {
        return null;
    }
}

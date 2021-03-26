package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.payment.OrderStatus;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;
import tn.esprit.pidev.consommitounsi.repositories.payment.IInvoiceRepository;
import tn.esprit.pidev.consommitounsi.repositories.payment.IItemRepository;
import tn.esprit.pidev.consommitounsi.repositories.payment.IOrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService extends OrderService implements ICartService {

    private final IItemRepository itemRepository;

    @Autowired
    public CartService(IOrderRepository orderRepository,
                       IItemRepository itemRepository) {
        super(orderRepository);
        this.itemRepository = itemRepository;
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
    public boolean itemProductExistsInCart(long cartId, long productId) {
        Order cart = super.getById(cartId);
        for (Item item: cart.getItems()) {
            if (item.getProduct().getId() == productId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Order removeItem(long cartId, Item item) {
        Order cart = super.getById(cartId);
        cart.getItems().remove(item);
        this.orderRepository.save(cart);
        return cart;
    }

    @Override
    public boolean containsItem(long cartId, long itemId) {
        Order cart = this.orderRepository.findById(cartId).orElse(null);
        if (cart == null) {
            return false;
        }

        for (Item item : cart.getItems()) {
            if (item.getId() == itemId)
                return true;
        }

        return false;
    }

    @Override
    public Order getByUserId(long userId) {
        Order cart = this.orderRepository.getCartByUserId(userId);

        return cart;
    }

    @Override
    public Order confirmCart() {
        return null;
    }
}

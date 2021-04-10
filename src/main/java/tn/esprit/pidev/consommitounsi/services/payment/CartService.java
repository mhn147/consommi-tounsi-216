package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.Cart;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.repositories.payment.ICartRepository;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.ICartService;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IItemService;
import tn.esprit.pidev.consommitounsi.services.user.IUserService;

import java.util.Calendar;

@Service
public class CartService implements ICartService {

    private final ICartRepository cartRepository;
    private final IItemService itemService;

    @Autowired
    public CartService(ICartRepository cartRepository,
                       IItemService itemService) {
        this.cartRepository = cartRepository;
        this.itemService = itemService;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setItems(null);
        cart.setCreationDate(Calendar.getInstance());
        cart.setUser(user);
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart getByUserId(long userId) {
        return this.cartRepository.getCartByUserId(userId);
    }

    @Override
    public Cart addItem(long cartId, Item item) throws IllegalArgumentException {
        Cart cart = this.cartRepository.findById(cartId).orElseThrow(IllegalArgumentException::new);
        cart.getItems().add(item);
        return this.cartRepository.save(cart);
    }

    @Override
    public Item updateItemQuantity(Item item, int quantity) {
        item.setQuantity(quantity);
        return this.itemService.addOrUpdate(item);
    }


    @Override
    public boolean itemProductExistsInCart(long cartId, long productId) {
        Cart cart = this.cartRepository.findById(cartId).orElseThrow(IllegalArgumentException::new);
        for (Item item : cart.getItems()) {
            if (item.getProduct().getId() == productId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Cart removeItem(long cartId, Item item) {
        Cart cart = this.cartRepository.findById(cartId).orElseThrow(IllegalArgumentException::new);
        cart.getItems().remove(item);
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart empty(long cartId) {
        Cart cart = this.cartRepository.findById(cartId).orElseThrow(IllegalArgumentException::new);
        cart.setItems(null);
        return this.cartRepository.save(cart);
    }

    @Override
    public boolean containsItem(long cartId, long itemId) {
        Cart cart = this.cartRepository.findById(cartId).orElseThrow(IllegalArgumentException::new);
        if (cart == null) {
            return false;
        }

        for (Item item : cart.getItems()) {
            if (item.getId() == itemId)
                return true;
        }

        return false;
    }


}

package ru.shariktlt.marketplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shariktlt.marketplace.controller.dto.SetCartRequest;
import ru.shariktlt.marketplace.model.CartItem;
import ru.shariktlt.marketplace.model.Product;
import ru.shariktlt.marketplace.storage.repository.CartItemRepository;
import ru.shariktlt.marketplace.storage.repository.ProductRepository;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository repository;

    @Autowired
    private ProductRepository repositoryProduct;

    public Iterable<CartItem> getCartItems(String id){
        return repository.findByIdId(id);
    }

    public void set(String cartId, SetCartRequest request) {

        Optional<Product> product = repositoryProduct.findById(request.getProductId());
        if(product.isEmpty()){
            throw new IllegalArgumentException("invalid product id");
        }

        CartItem item = new CartItem();
        item.setId(new CartItem.CartItemId(cartId, product.get().getId()));
        item.setCount(request.getCount());
        item.setProduct(product.get());

        repository.save(item);
    }

    public void remove(String cartId, Long productId) {
        Product product = new Product();
        product.setId(productId);
        repository.deleteById(new CartItem.CartItemId(cartId, product.getId()));
    }

    public void removeAll(String cartId) {

        repository.deleteById(cartId);
    }

}

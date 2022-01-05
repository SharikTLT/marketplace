package ru.shariktlt.marketplace.storage.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.shariktlt.marketplace.model.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, CartItem.CartItemId> {

    @Query("SELECT c FROM CartItem c WHERE id.id = :id")
    Iterable<CartItem> findById(String id);

    Iterable<CartItem> findByIdId(String id);

    void deleteById(String id);
}

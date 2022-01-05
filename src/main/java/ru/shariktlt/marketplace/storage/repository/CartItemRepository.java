package ru.shariktlt.marketplace.storage.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shariktlt.marketplace.model.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, CartItem.CartItemId> {

    /**
     * Поиск по CartItem.id.id
     *
     * @param id
     * @return
     */
    Iterable<CartItem> findByIdId(String id);

    void deleteById(String id);
}

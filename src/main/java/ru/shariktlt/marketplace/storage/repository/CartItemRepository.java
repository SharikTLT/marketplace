package ru.shariktlt.marketplace.storage.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.shariktlt.marketplace.model.CartItem;

import javax.transaction.Transactional;

public interface CartItemRepository extends CrudRepository<CartItem, CartItem.CartItemId> {

    /**
     * Поиск по CartItem.pk.id
     *
     * @param id
     * @return
     */
    Iterable<CartItem> findByPkId(String id);

    /**
     * Удаление всех элементов по CartItem.pk.id
     * @param id
     */
    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.pk.id = :id")
    @Transactional
    int deleteAllByPkId(String id);
}

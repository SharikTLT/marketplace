package ru.shariktlt.marketplace.storage.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.shariktlt.marketplace.model.Product;

@Service
public interface ProductRepository extends CrudRepository<Product, Long> {
}

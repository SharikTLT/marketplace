package ru.shariktlt.marketplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shariktlt.marketplace.model.Product;
import ru.shariktlt.marketplace.storage.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Iterable<Product> getProducts(){
        return productRepository.findAll();
    }
}

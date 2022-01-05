package ru.shariktlt.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shariktlt.marketplace.model.Product;
import ru.shariktlt.marketplace.services.ProductService;

@RestController
@RequestMapping("/rest/product")
public class ProductControllerRest {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public Iterable<Product> index() {
        return productService.getProducts();
    }

}


package ru.shariktlt.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shariktlt.marketplace.services.ProductService;

@RequestMapping("/product/")
@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("productList", service.getProducts());
        return "product/index";
    }


    @GetMapping("/{id}/view")
    public String view(Model model, @PathVariable("id") Long id){
        model.addAttribute("product", service.getProduct(id));
        return "product/view";
    }

    @GetMapping("/{id}/addCart")
    public String addCart(Model model, @PathVariable("id") Long id){
        model.addAttribute("product", service.getProduct(id));
        return "product/view";
    }
}

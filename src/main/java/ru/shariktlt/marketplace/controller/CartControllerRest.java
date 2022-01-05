package ru.shariktlt.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shariktlt.marketplace.controller.dto.SetCartRequest;
import ru.shariktlt.marketplace.model.CartItem;
import ru.shariktlt.marketplace.services.CartService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/rest/cart")
public class CartControllerRest {

    @Autowired
    private CartService service;

    @GetMapping("/")
    public Iterable<CartItem> index(@CookieValue(name = "cartId", required = false) String cartId, HttpServletResponse response) {
        if (cartId == null) {
            cartId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("cartId", cartId);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return service.getCartItems(cartId);
    }

    @PostMapping("/set")
    public Iterable<CartItem> set(@CookieValue(name = "cartId") String cartId, @RequestBody SetCartRequest request) {
        service.set(cartId, request);
        return service.getCartItems(cartId);
    }

    @PostMapping("/remove")
    public Iterable<CartItem> remove(@CookieValue(name = "cartId") String cartId, @RequestBody SetCartRequest request) {
        service.remove(cartId, request.getProductId());
        return service.getCartItems(cartId);
    }

    @GetMapping("/clear")
    public Iterable<CartItem> clear(@CookieValue(name = "cartId") String cartId) {
        service.removeAll(cartId);
        return service.getCartItems(cartId);
    }


}


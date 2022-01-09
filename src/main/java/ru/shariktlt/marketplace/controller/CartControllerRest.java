package ru.shariktlt.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shariktlt.marketplace.controller.dto.CartResponse;
import ru.shariktlt.marketplace.controller.dto.SetCartRequest;
import ru.shariktlt.marketplace.services.CartService;
import ru.shariktlt.marketplace.services.mappers.ClientCartMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/rest/cart")
public class CartControllerRest {

    @Autowired
    private CartService service;

    @Autowired
    private ClientCartMapper clientCartMapper;

    @GetMapping("/")
    public CartResponse index(@CookieValue(name = "cartId", required = false) String cartId, HttpServletResponse response) {
        if (cartId == null) {
            cartId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("cartId", cartId);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return getCartResponse(cartId);
    }

    private CartResponse getCartResponse(String cartId) {
        return clientCartMapper.prepareResponse(service.getCartItems(cartId));
    }


    @PostMapping("/set")
    public CartResponse set(@CookieValue(name = "cartId") String cartId, @RequestBody SetCartRequest request) {
        service.set(cartId, request);
        return getCartResponse(cartId);
    }

    @PostMapping("/remove")
    public CartResponse remove(@CookieValue(name = "cartId") String cartId, @RequestBody SetCartRequest request) {
        if(request.getProductIds().isEmpty()){
            service.remove(cartId, request.getProductId());
        }else{
            service.remove(cartId, request.getProductIds());
        }
        return getCartResponse(cartId);
    }

    @GetMapping("/clear")
    public CartResponse clear(@CookieValue(name = "cartId") String cartId) {
        service.removeAll(cartId);
        return getCartResponse(cartId);
    }


}


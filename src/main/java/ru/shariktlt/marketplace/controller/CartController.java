package ru.shariktlt.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shariktlt.marketplace.controller.dto.CartResponse;
import ru.shariktlt.marketplace.model.CartItem;
import ru.shariktlt.marketplace.services.CartService;
import ru.shariktlt.marketplace.services.mappers.ClientCartMapper;

import static java.util.Collections.emptyList;

@RequestMapping("/cart")
@Controller
public class CartController {

    public static final String CART_ITEMS_ATTR = "cartItems";
    public static final String CART_ITEMS_SUM_ATTR = "cartItemsSum";

    @Autowired
    private CartService cartService;

    @Autowired
    private ClientCartMapper clientCartMapper;


    @GetMapping("/")
    public String index(@CookieValue(value = "cartId", required = false) String cartId, Model model) {
        if (cartId != null) {
            Iterable<CartItem> cartItems = cartService.getCartItems(cartId);

            CartResponse cartResponse = clientCartMapper.prepareResponse(cartItems);

            model.addAttribute(CART_ITEMS_ATTR, cartItems);
            model.addAttribute(CART_ITEMS_SUM_ATTR, cartResponse.getFormattedSum());
        } else {
            model.addAttribute(CART_ITEMS_ATTR, emptyList());
        }
        return "cart/index";
    }

}

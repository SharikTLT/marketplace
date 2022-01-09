package ru.shariktlt.marketplace.controller.dto;

import ru.shariktlt.marketplace.model.CartItem;
import ru.shariktlt.marketplace.model.Product;

import java.math.BigDecimal;

public class ClientCartItemAdapter {

    private final CartItem cartItem;

    private final BigDecimal sum;

    private final String formattedSum;

    public ClientCartItemAdapter(CartItem cartItem, BigDecimal sum, String formattedSum) {
        this.cartItem = cartItem;
        this.sum = sum;
        this.formattedSum = formattedSum;
    }

    public Product getProduct(){
        return cartItem.getProduct();
    }

    public Long getCount(){
        return cartItem.getCount();
    }

    public BigDecimal getSum(){
        return sum;
    }

    public String getFormattedSum(){
        return formattedSum;
    }
}

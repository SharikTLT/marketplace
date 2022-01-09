package ru.shariktlt.marketplace.controller.dto;

import java.math.BigDecimal;

import static java.util.Collections.emptyList;

public class CartResponse {

    private Iterable<ClientCartItemAdapter> items;

    private BigDecimal sum;

    private String formattedSum;

    public CartResponse() {
        items = emptyList();
    }

    public CartResponse(Iterable<ClientCartItemAdapter> items, BigDecimal sum, String formattedSum) {
        this.items = items;
        this.sum = sum;
        this.formattedSum = formattedSum;
    }

    public Iterable<ClientCartItemAdapter> getItems() {
        return items;
    }

    public void setItems(Iterable<ClientCartItemAdapter> items) {
        this.items = items;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getFormattedSum() {
        return formattedSum;
    }

    public void setFormattedSum(String formattedSum) {
        this.formattedSum = formattedSum;
    }
}

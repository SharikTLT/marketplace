package ru.shariktlt.marketplace.controller.dto;

public class SetCartRequest {
    private Long productId;
    private long count;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}

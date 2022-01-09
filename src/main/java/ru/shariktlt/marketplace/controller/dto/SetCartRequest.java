package ru.shariktlt.marketplace.controller.dto;

import java.util.Collections;
import java.util.List;

public class SetCartRequest {
    private Long productId;

    private long count;

    private List<Long> productIds = Collections.emptyList();

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

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}

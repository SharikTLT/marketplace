package ru.shariktlt.marketplace.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class CartItem {

    @EmbeddedId
    private CartItemId id;

    @MapsId("productId")
    @ManyToOne(optional = true)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    private long count;

    public CartItemId getId() {
        return id;
    }

    public void setId(CartItemId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return count == cartItem.count && Objects.equals(id, cartItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count);
    }

    @Embeddable
    public static class CartItemId implements Serializable {
        private String id;

        private Long productId;

        public CartItemId() {
        }

        public CartItemId(final String id, final Long product) {
            this.id = id;
            this.productId = product;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CartItemId that = (CartItemId) o;
            return Objects.equals(id, that.id) && Objects.equals(productId, that.productId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, productId);
        }
    }
}

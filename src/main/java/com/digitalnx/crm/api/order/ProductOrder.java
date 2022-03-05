package com.digitalnx.crm.api.order;

import com.digitalnx.crm.api.product.Product;

import javax.persistence.*;

@Entity
public class ProductOrder {
    public ProductOrder() {}
    public ProductOrder(Product product, int quantities, String discountCode) {
        this.product = product;
        this.quantities = quantities;
        this.discountCode = discountCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Product product;
    private int quantities;
    private String discountCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}

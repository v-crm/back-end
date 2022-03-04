package com.digitalnx.crm.api.product;

import com.digitalnx.crm.api.product.productproperty.ProductProperty;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    public Product() {}

    public Product(String name, int price, String priceUnit, int discount, String description, Set<ProductProperty> properties) {
        this.name = name;
        this.price = price;
        this.priceUnit = priceUnit;
        this.discount = discount;
        this.description = description;
        this.properties = properties;
    }
    public Product(String name, int price, String priceUnit,int discount, String description) {
        this(name, price, priceUnit, discount, description, new HashSet<>());
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private int price;
    private String priceUnit;
    private int discount;
    private String description;
    private int unitsInStock;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<ProductProperty> properties;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProductProperty> getProperties() {
        return properties;
    }

    public void setProperties(Set<ProductProperty> properties) {
        this.properties = properties;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }
}
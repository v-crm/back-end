package com.digitalnx.crm.api.product.productproperty;

import javax.persistence.*;

@Entity
public class ProductProperty {
    public ProductProperty() {}
    public ProductProperty(String property) {
        this.property = property;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String property;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}

package com.digitalnx.crm.api.order;

import com.digitalnx.crm.api.product.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Temporal(value= TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Product> product;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

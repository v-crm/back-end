package com.digitalnx.crm.api.order.customerproductorder;

import com.digitalnx.crm.api.order.ProductOrder;
import com.digitalnx.crm.api.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class CustomerProductOrder {
    public CustomerProductOrder() {}

    public CustomerProductOrder(User user, Set<ProductOrder> orders, Date date ) {
        this.date = date;
        this.orders = orders;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Temporal(value= TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    Set<ProductOrder> orders;

    @OneToOne
    private User user;

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getId() {
        return Id;
    }

    public Set<ProductOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<ProductOrder> orders) {
        this.orders = orders;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

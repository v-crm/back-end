package com.digitalnx.crm.api.user.user;
import com.digitalnx.crm.api.user.userrole.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class User {
    public User() {}

    public User(String firstname, String surname, String username, String password, String postalAddress, String city, String phoneNumber, int postalCode) {
        this.firstname = firstname;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.postalAddress = postalAddress;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String firstname;
    private String surname;
    private String username;
    private String password;
    private String postalAddress;
    private String city;
    private String phoneNumber;
    private int postalCode;

    @ManyToMany(fetch=FetchType.EAGER)
    private Collection<UserRole>  userRoles = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}

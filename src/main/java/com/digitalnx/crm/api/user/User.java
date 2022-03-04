package com.digitalnx.crm.api.user;
import javax.persistence.*;

@Entity
public class User {
    public User() {}

    public User(String firstname, String surname, String password, UserRole userRole, String postalAddress, String city, String phoneNumber, int postalCode) {
        this.firstname = firstname;
        this.surname = surname;
        this.password = password;
        this.userRole = userRole;
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
    private String password;
    private UserRole userRole;
    private String postalAddress;
    private String city;
    private String phoneNumber;
    private int postalCode;

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

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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

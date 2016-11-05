package com.swinburne.timur.assignment5task1.address;

import static java.lang.String.valueOf;

public class Address {
    private Integer id;
    private String name;
    private String number;
    private String email;

    public Address(String name, String number, String email) {
        this.id = 0;
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public Address(Integer id, String name, String number, String email) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public Integer getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
       this.number = number;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "{ id: " + valueOf(id) + ", name: " + name + ", number: " + number + ", email: " + email + " }";
    }

}

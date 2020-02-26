package com.greenfoxtakeout.greenfoxtakeout.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TakeoutOrder {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @JsonIgnore
    private String address;
    private String base;
    private String topping;
    private String status;

    public TakeoutOrder() {
    }

    public TakeoutOrder(String name, String address, String base, String topping) {
        this.name = name;
        this.address = address;
        this.base = base;
        this.topping = topping;
        this.status = "ordered";
    }

    public TakeoutOrder(Long id, String name, String address, String base, String topping) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.base = base;
        this.topping = topping;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }
}

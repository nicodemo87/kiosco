/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Nico
 */
@Entity
@Table
public class Item {

    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column(unique = true)
    private String code;
    @Column
    private String description;
    @Column
    private float price;
    @Column
    private float cost;
    @Column
    private int stock;
    @ManyToOne
    private ItemKind kind;
    @ManyToOne
    private ItemBrand brand;
            

    public Item() {
    }

    public Item(String code, String description, float cost, float price, int stock) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.cost = cost;
        this.stock = stock;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the cost
     */
    public float getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * @return the kind
     */
    public ItemKind getKind() {
        return kind;
    }

    /**
     * @param kind the kind to set
     */
    public void setKind(ItemKind kind) {
        this.kind = kind;
    }

    /**
     * @return the brand
     */
    public ItemBrand getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(ItemBrand brand) {
        this.brand = brand;
    }
}

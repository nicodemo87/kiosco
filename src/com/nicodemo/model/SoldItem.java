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
public class SoldItem {

    @Id
    @GeneratedValue
    @Column
    private int id;
    @ManyToOne
    private Item item;
    @Column
    private int quantity;
    @Column
    private float cost;
    @Column
    private float sellingPrice;

    public SoldItem(){}
    
    public SoldItem(Item item, int cant) {
        this.item = item;
        this.quantity = cant;
        this.cost = item.getCost();
        this.sellingPrice = item.getPrice();
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
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the sellingPrice
     */
    public float getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @param sellingPrice the sellingPrice to set
     */
    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double subTotal() {
        return sellingPrice * quantity;
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

    void increaseQuantity(int cant) {
        quantity = quantity + cant;
    }

    void updateItemStock() {
        this.item.setStock(this.item.getStock() - this.quantity);
    }
}

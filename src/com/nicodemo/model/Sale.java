/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.model;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Nico
 */
@Entity
@Table
public class Sale {

    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private ArrayList<SoldItem> soldItems;
    @Column
    private float discount;
    @Column
    private Date date;
    @Column
    private User user;
    
    public Sale(){
        soldItems = new ArrayList<SoldItem>();
        date = new Date();
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
     * @return the soldItems
     */
    public ArrayList<SoldItem> getSoldItems() {
        return soldItems;
    }

    /**
     * @param soldItems the soldItems to set
     */
    public void setSoldItems(ArrayList<SoldItem> soldItems) {
        this.soldItems = soldItems;
    }

    /**
     * @return the discount
     */
    public float getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    public void addItem(Item item, int cant) {
        this.soldItems.add(new SoldItem(item,cant));
    }

    public float total() {
        float total = 0;
        for(SoldItem s :soldItems){
            total = total + s.subTotal();
        }
        return total;
    }   
}

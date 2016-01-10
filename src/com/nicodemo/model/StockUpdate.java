/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.model;

import java.util.Date;
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
public class StockUpdate {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private Date date;
    @ManyToOne
    private Item item;
    @ManyToOne
    private User user;
    @Column
    private int prevStock;
    @Column
    private int newStock;

    public StockUpdate(Item item, int prevStock) {
        this.date = new Date();
        this.item = item;
        this.user = User.getCurrentUser();
        this.prevStock = prevStock;
        this.newStock = item.getStock();
    }
    
    public StockUpdate(){}
    
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

    /**
     * @return the prevStock
     */
    public int getPrevStock() {
        return prevStock;
    }

    /**
     * @param prevStock the prevStock to set
     */
    public void setPrevStock(int prevStock) {
        this.prevStock = prevStock;
    }

    /**
     * @return the newStock
     */
    public int getNewStock() {
        return newStock;
    }

    /**
     * @param newStock the newStock to set
     */
    public void setNewStock(int newStock) {
        this.newStock = newStock;
    }
}

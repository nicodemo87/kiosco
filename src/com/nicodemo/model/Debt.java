/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.model;

import java.util.Date;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
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
public class Debt {

    @Id
    @GeneratedValue
    @Column
    private int id;
    @ManyToOne
    private Sale sale;
    @Column
    private double paid;
    @Column
    private double debtAmount;
    @Column
    private Date date;
    @Column
    private Date paidDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Client client;

    public Debt(Sale sale) {
        this.sale = sale;
        this.user = User.getCurrentUser();
        this.date = new Date();
        this.debtAmount = sale.total();
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
     * @return the sale
     */
    public Sale getSale() {
        return sale;
    }

    /**
     * @param sale the sale to set
     */
    public void setSale(Sale sale) {
        this.sale = sale;
    }

    /**
     * @return the paid
     */
    public double getPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid(double paid) {
        this.paid = paid;
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
     * @return the paidDate
     */
    public Date getPaidDate() {
        return paidDate;
    }

    /**
     * @param paidDate the paidDate to set
     */
    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
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

    public double totalDebt() {
        return debtAmount - paid;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    public boolean hasDebt() {
        return paid < debtAmount;
    }

    public void cancel(double amount) {
        if (amount > 0) {
            if (amount > debtAmount - paid) {
                paid = debtAmount;
            } else {
                paid = paid + amount;
            }
        }
    }

    /**
     * @return the debtAmount
     */
    public double getDebtAmount() {
        return debtAmount;
    }

    /**
     * @param debtAmount the debtAmount to set
     */
    public void setDebtAmount(double debtAmount) {
        this.debtAmount = debtAmount;
    }
}

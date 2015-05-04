/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private double originalDebtAmount;
    @Column
    private Date date;
    @ManyToOne
    private User user;
    @ManyToOne
    private Client client;

    @OneToMany
    private List<Payment> payments;

    public Debt(Sale sale) {
        this.sale = sale;
        this.user = User.getCurrentUser();
        this.date = new Date();
        this.originalDebtAmount = sale.total();
        this.payments = new ArrayList<>();
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

    public double totalPaid() {
        return payments.stream()
                .mapToDouble(p -> p.getAmount())
                .sum();
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
        return totalPaid() < originalDebtAmount;
    }
    
    public double currentDebtAmount(){
        return originalDebtAmount - totalPaid();
    }

    public Payment cancel(double amount) {
        Payment payment = null;
        if (amount > 0 && hasDebt()) {
            if (amount > currentDebtAmount()) {
                payment = new Payment(currentDebtAmount());
            } else {
                payment = new Payment(amount);
            }
            payments.add(payment);
        }        
        return payment;
    }
    

    /**
     * @return the debtAmount
     */
    public double getOriginalDebtAmount() {
        return originalDebtAmount;
    }

    /**
     * @param debtAmount the debtAmount to set
     */
    public void setOriginalDebtAmount(double debtAmount) {
        this.originalDebtAmount = debtAmount;
    }

    /**
     * @return the payments
     */
    public List<Payment> getPayments() {
        return payments;
    }

    /**
     * @param payments the payments to set
     */
    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}

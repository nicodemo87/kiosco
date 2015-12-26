/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
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
public class CashBox {

    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private double startAmount;    
    @Column
    private Date startDateTime;
    @Column
    private Date endDateTime;
    @ManyToOne(optional = false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Sale> sales;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Debt> debits;
    @OneToMany
    private List<Payment> payments;
    
    @Column
    private double endSold;
    @Column
    private double endDebited;
    @Column
    private double endPayments;
    @Column
    private double endAmount;
    @Column
    private double endCost;
    @Column
    private double endProfit;
    

    public CashBox() {
        startDateTime = new Date();
        sales = new HashSet<>();
        debits = new HashSet<>();
        user = User.getCurrentUser();
        payments = new ArrayList<>();
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
     * @return the startAmount
     */
    public double getStartAmount() {
        return startAmount;
    }

    /**
     * @param startAmount the startAmount to set
     */
    public void setStartAmount(double startAmount) {
        this.startAmount = startAmount;
    }

    /**
     * @return the endAmount
     */
    public double getEndAmount() {
        return endAmount;
    }

    /**
     * @param endAmount the endAmount to set
     */
    public void setEndAmount(double endAmount) {
        this.endAmount = endAmount;
    }

    /**
     * @return the startTime
     */
    public Date getStartDateTime() {
        return startDateTime;
    }

    /**
     * @param startDateTime the startTime to set
     */
    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndDateTime() {
        return endDateTime;
    }

    /**
     * @param endDateTime the endTime to set
     */
    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
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

    public void addSale(Sale sale) {
        this.sales.add(sale);
    }

    /**
     * @return the sales
     */
    public Set<Sale> getSales() {
        return sales;
    }

    /**
     * @param sales the sales to set
     */
    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public double sold() {
        if(isClosed())
            return endSold;
        else
            return sales.stream()
                .mapToDouble(s -> s.total())
                .sum();
    }

    public double debited() {
        if(isClosed())
            return endDebited;
        else
            return debits.stream()
                .mapToDouble(d -> d.getOriginalDebtAmount())
                .sum();
    }

    public double totalCash() {
        return startAmount + sold() + payments() - debited();
    }

    public void addDebt(Debt debt) {
        debits.add(debt);
    }

    public void removeSale(Sale saleToRemove) throws Exception {
        if (!debits.stream().anyMatch(d -> d.getSale().equals(saleToRemove))) {
            sales.remove(saleToRemove);
        } else {
            throw new Exception("No se puede remover la venta por que la misma pertenece a una deuda");
        }
    }
    
    public void addPayments(List<Payment> payments) {
        this.getPayments().addAll(payments);
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

    public double payments() {
        if(isClosed())
            return endPayments;
        return payments.stream()
                .mapToDouble(p->p.getAmount())
                .sum();
    }

    public void close() {
        endSold = sold();
        endDebited = debited();
        endPayments = payments();
        endAmount = totalCash();
        endCost = costs();
        endProfit = profit();
        setEndDateTime(new Date());
    }

    /**
     * @return the endSold
     */
    public double getEndSold() {
        return endSold;
    }

    /**
     * @param endSold the endSold to set
     */
    public void setEndSold(double endSold) {
        this.endSold = endSold;
    }

    /**
     * @return the endDebited
     */
    public double getEndDebited() {
        return endDebited;
    }

    /**
     * @param endDebited the endDebited to set
     */
    public void setEndDebited(double endDebited) {
        this.endDebited = endDebited;
    }
    
    public boolean isClosed(){
        return endDateTime != null;
    }

    /**
     * @return the endCost
     */
    public double getEndCost() {
        return endCost;
    }

    /**
     * @param endCost the endCost to set
     */
    public void setEndCost(double endCost) {
        this.endCost = endCost;
    }

    /**
     * @return the endProfit
     */
    public double getEndProfit() {
        return endProfit;
    }

    /**
     * @param endProfit the endProfit to set
     */
    public void setEndProfit(double endProfit) {
        this.endProfit = endProfit;
    }
    
    public double costs(){
        if(isClosed())
            return endCost;
        else
            return sales.stream()
                .mapToDouble(s -> s.costs())
                .sum();            
    }
    
    public double profit(){
        if(isClosed())
            return endProfit;
        else
            return sold() - costs() - debited();          
    }
}

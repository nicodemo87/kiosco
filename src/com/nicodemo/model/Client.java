/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nico
 */
@Entity
@Table
public class Client {

    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column(unique = true)
    private int dni;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String phone;
    @Column
    private String address;    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Debt> debts;

    public Client(int dni, String firstName, String lastName, String phone) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.debts = new HashSet<>();
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public double debt() {
        return getDebts().stream()
                .mapToDouble(d->d.total())
                .sum();
    }

    /**
     * @return the debts
     */
    public Set<Debt> getDebts() {
        return debts;
    }

    /**
     * @param debts the debts to set
     */
    public void setDebts(Set<Debt> debts) {
        this.debts = debts;
    }
    
    public void addDebt(Debt debt){
        this.debts.add(debt);
        debt.setClient(this);
    }

    /**
     * @return the dni
     */
    public int getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(int dni) {
        this.dni = dni;
    }
}

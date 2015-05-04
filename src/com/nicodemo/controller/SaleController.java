/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.controller;

import com.nicodemo.model.CashBox;
import com.nicodemo.model.Client;
import com.nicodemo.model.Debt;
import com.nicodemo.model.Item;
import com.nicodemo.model.Payment;
import com.nicodemo.model.Sale;
import com.nicodemo.model.SoldItem;
import com.nicodemo.persistence.DAOs.CashBoxesDAO;
import com.nicodemo.persistence.DAOs.ClientsDAO;
import com.nicodemo.persistence.DAOs.ItemsDAO;
import com.nicodemo.persistence.exceptions.ElementNotFoundException;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nico
 */
public class SaleController {

    private CashBox cashBox;
    private Sale sale;

    private ItemsDAO itemsDAO;
    private CashBoxesDAO cashBoxesDAO;
    private ClientsDAO clientsDAO;

    @Autowired
    public SaleController(ItemsDAO itemsDAO, CashBoxesDAO cashBoxesDAO, ClientsDAO clientsDAO) {
        this.itemsDAO = itemsDAO;
        this.cashBoxesDAO = cashBoxesDAO;
        this.clientsDAO = clientsDAO;
        initCashBox();
    }

    public void newSale() {
        sale = new Sale();
    }

    public Sale getSale() {
        return sale;
    }

    public List<Item> getItems() {
        return itemsDAO.getAll();
    }

    public void addItem(String keyword) throws ElementNotFoundException {
        sale.addItem(itemsDAO.getItemByCode(keyword), 1);
    }

    public Set<SoldItem> getSoldItems() {
        return sale.getSoldItems();
    }

    public double getTotal() {
        return sale.total();
    }

    public void sell() {
        cashBox.addSale(sale);
        cashBoxesDAO.save(cashBox);
        sale = new Sale();

    }

    public CashBox getCurrentCashBox() {
        return this.cashBox;
    }

    public final void initCashBox() {
        cashBox = new CashBox();
        sale = new Sale();
    }

    public void debit(Client client) {
        cashBox.addSale(sale);
        Debt debt = new Debt(sale);
        client.addDebt(debt);
        cashBox.addDebt(debt);
        cashBoxesDAO.save(cashBox);
        sale = new Sale();
    }

    public void removeSale(Sale saleToRemove) throws Exception {
        cashBox.removeSale(saleToRemove);
        cashBoxesDAO.save(cashBox);
    }

    public void addPayments(List<Payment> payments) {
        if (!payments.isEmpty()) {
            cashBox.addPayments(payments);
            cashBoxesDAO.save(cashBox);
        }
    }
}

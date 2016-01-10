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
import com.nicodemo.model.User;
import com.nicodemo.persistence.DAOs.CashBoxesDAO;
import com.nicodemo.persistence.DAOs.ClientsDAO;
import com.nicodemo.persistence.DAOs.ItemsDAO;
import com.nicodemo.persistence.exceptions.ElementNotFoundException;
import java.util.Date;
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

    public Item addItem(String keyword) throws ElementNotFoundException {
        Item item = itemsDAO.getItemByCode(keyword);
        sale.addItem(item, 1);
        return item;
    }

    public Set<SoldItem> getSoldItems() {
        return sale.getSoldItems();
    }

    public double getTotal() {
        return sale.total();
    }

    public void sell() {
        if(!sale.getSoldItems().isEmpty())
        {
            cashBox.addSale(sale);
            cashBoxesDAO.save(cashBox);
            updateStock();
            sale = new Sale();
        }
    }

    public CashBox getCurrentCashBox() {
        return this.cashBox;
    }

    public final void initCashBox() {
        cashBox = cashBoxesDAO.getOpenCashBox();
        if(cashBox == null)
            cashBox = new CashBox();
        sale = new Sale();
    }

    public void debit(Client client) {
        cashBox.addSale(sale);
        Debt debt = new Debt(sale);
        client.addDebt(debt);
        cashBox.addDebt(debt);
        cashBoxesDAO.save(cashBox);
        updateStock();
        sale = new Sale();
    }
    
    private void updateStock(){
        sale.updateItemsStock();
        sale.getSoldItems().forEach(s->itemsDAO.save(s.getItem()));
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

    public void removeItem(String code) {
        sale.removeItemByCode(code);
    }

    public SoldItem getSoldItemBy(String code) {
        return sale.getSoldItemBy(code);
    }
    
    public void closeCashBox(){
        cashBox.close();
        cashBoxesDAO.save(cashBox);
        cashBox = new CashBox();
    }

    public boolean existAlreadyOpenCashBox() {
        return cashBox.getId() != 0;
    }

    public void setStartAmount(Double startAmount) {
        cashBox.setStartAmount(startAmount);
        cashBoxesDAO.save(cashBox);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.controller;

import com.nicodemo.model.Item;
import com.nicodemo.model.Sale;
import com.nicodemo.persistence.DAOs.ItemsDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nico
 */
public class SaleController {
    private Sale sale;
    
    private ItemsDAO itemsDAO;
    
    @Autowired
    public SaleController(ItemsDAO itemsDAO){
        sale = new Sale();
        
        this.itemsDAO = itemsDAO;
    }
    
    public void newSale(){
        sale = new Sale();
    }
    
    public Sale getSale(){
        return sale;
    }
    
    public List<Item> getItems(){
        return itemsDAO.getAll();
    }

    public Item findItem(String keyword) {
        return itemsDAO.getItemByCode(keyword);
    }
    
    

}

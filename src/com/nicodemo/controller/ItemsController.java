/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.controller;

import com.nicodemo.model.Item;
import com.nicodemo.persistence.DAOs.ItemsDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nico
 */
public class ItemsController {
    
    private ItemsDAO itemsDAO;
    
    @Autowired
    public ItemsController(ItemsDAO itemsDAO){
        this.itemsDAO = itemsDAO;
    }
    
    public void saveItem(String code, String descrption, float cost, float price){
        Item item = new Item(code, descrption, cost, price, 0);
        
        itemsDAO.save(item);        
    }

    public List<Item> getItems() {
        return itemsDAO.getAll();
    }
}

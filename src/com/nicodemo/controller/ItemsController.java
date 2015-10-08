/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.controller;

import com.nicodemo.model.Item;
import com.nicodemo.model.ItemBrand;
import com.nicodemo.model.ItemKind;
import com.nicodemo.model.StockUpdate;
import com.nicodemo.persistence.DAOs.ItemsDAO;
import com.nicodemo.persistence.DAOs.StockUpdateDAO;
import com.nicodemo.persistence.exceptions.ElementNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nico
 */
public class ItemsController {
    
    private ItemsDAO itemsDAO;
    private StockUpdateDAO stockUpdateDAO;
    
    @Autowired
    public ItemsController(ItemsDAO itemsDAO, StockUpdateDAO stockUpdateDAO){
        this.itemsDAO = itemsDAO;
        this.stockUpdateDAO = stockUpdateDAO;
    }
    
    public void saveItem(Item item, int prevStock){
        itemsDAO.save(item); 
        if(item.getStock() != prevStock)
            stockUpdateDAO.save(new StockUpdate(item, prevStock));
    }

    public List<Item> getItems() {
        return itemsDAO.getAll();
    }

    public Item getByCode(String code) throws ElementNotFoundException {
        return itemsDAO.getItemByCode(code);
    }
    
    public ItemKind getTypeById(int id){
       return itemsDAO.getTypeById(id);
    }
    
    public List<ItemKind> getAllKinds() {
        return itemsDAO.getAllKinds();
    }
    
    public ItemKind saveKind(ItemKind kind){
        return itemsDAO.saveKind(kind);
    }
    
    public ItemBrand getBrandById(int id){
       return itemsDAO.getBrandById(id);
    }
    
    public List<ItemBrand> getAllBrands() {
        return itemsDAO.getAllBrands();
    }
    
    public ItemBrand saveBrand(ItemBrand brand){
        return itemsDAO.saveBrand(brand);
    }  
}

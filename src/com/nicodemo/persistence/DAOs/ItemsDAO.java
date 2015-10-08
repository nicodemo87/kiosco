/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import com.nicodemo.model.Item;
import com.nicodemo.model.ItemBrand;
import com.nicodemo.model.ItemKind;
import com.nicodemo.model.Sale;
import com.nicodemo.persistence.exceptions.ElementNotFoundException;
import java.util.List;
import org.jinq.jpa.JPQL;

/**
 *
 * @author Nico
 */
public class ItemsDAO extends DAO<Item> {

    @Override
    public List<Item> getAll() {
        List<Item> items = streams.streamAll(entityManager, Item.class)
                .toList();
        //List items = entityManager.createQuery("SELECT i FROM Item  i").getResultList();
        return items;
    }

    @Override
    public Item getById(int id) {
        return (Item) entityManager.find(Item.class, id);
    }
    
    public Item getItemByCode(String code) throws ElementNotFoundException {
        List<Item> items = streams.streamAll(entityManager, Item.class)
                .where(i->i.getCode().equals(code))
                .toList();
        if (items.isEmpty())
                throw new ElementNotFoundException("Item with code ["+code+"] not found");
        return !items.isEmpty()? items.get(0) : null;
    }
    
        public List<Item> getItemsByDescription(String description) {
        List<Item> items = streams.streamAll(entityManager, Item.class)
                .where(i->JPQL.like(i.getDescription(), description))
                .toList();
        return items;
    }
        
    public ItemKind getTypeById(int id){
       return (ItemKind) entityManager.find(ItemKind.class, id); 
    }
    
    public List<ItemKind> getAllKinds() {
        List<ItemKind> items = streams.streamAll(entityManager, ItemKind.class)
                .toList();
        return items;
    }
    
    public ItemKind saveKind(ItemKind kind){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(kind);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if(entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
            entityManager = entityManagerFactory.createEntityManager();
            throw ex;
        }
        return kind;
    }
    
    public ItemBrand getBrandById(int id){
       return (ItemBrand) entityManager.find(ItemBrand.class, id); 
    }
    
    public List<ItemBrand> getAllBrands() {
        List<ItemBrand> items = streams.streamAll(entityManager, ItemBrand.class)
                .toList();
        return items;
    }
    
    public ItemBrand saveBrand(ItemBrand brand){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(brand);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if(entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
            entityManager = entityManagerFactory.createEntityManager();
            throw ex;
        }
        return brand;
    }   
    
}

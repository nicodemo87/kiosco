/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import com.nicodemo.model.Item;
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

}

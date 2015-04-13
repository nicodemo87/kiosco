/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import com.nicodemo.model.Item;
import java.util.List;

/**
 *
 * @author Nico
 */
public class ItemsDAO extends DAO<Item> {

    @Override
    public List<Item> getAll() {
        List items = entityManager.createQuery("SELECT i FROM Item  i").getResultList();
        return items;
    }

    @Override
    public Item getById(int id) {
        return (Item) entityManager.find(Item.class, id);
    }
}

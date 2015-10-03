/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import com.nicodemo.model.StockUpdate;
import java.util.List;

/**
 *
 * @author Nico
 */
public class StockUpdateDAO extends DAO<StockUpdate> {

    @Override
    public List<StockUpdate> getAll() {
             List<StockUpdate> items = streams.streamAll(entityManager, StockUpdate.class)
                .toList();
        return items;
    }

    @Override
    public StockUpdate getById(int id) {
        return (StockUpdate) entityManager.find(StockUpdate.class, id);
    }
    
}

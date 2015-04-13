/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import com.nicodemo.model.Item;
import com.nicodemo.model.Sale;
import java.util.List;

/**
 *
 * @author Nico
 */
public class SalesDAO extends DAO<Sale> {

    @Override
    public List<Sale> getAll() {
        List sales = entityManager.createQuery("SELECT s Sale s").getResultList();
        return sales;
    }

    @Override
    public Sale getById(int id) {
        return (Sale) entityManager.find(Sale.class, id);
    }
}

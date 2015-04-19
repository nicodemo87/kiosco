/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import com.nicodemo.model.CashBox;
import java.util.List;

/**
 *
 * @author Nico
 */
public class CashBoxesDAO extends DAO<CashBox> {

    @Override
    public List<CashBox> getAll() {
        List<CashBox> cashBoxes = streams.streamAll(entityManager, CashBox.class)
                .toList();
        return cashBoxes;
    }

    @Override
    public CashBox getById(int id) {
        return (CashBox) entityManager.find(CashBox.class, id);
    }
    
    public CashBox getOpenCashBox() {
        List<CashBox> openCashBoxes = streams.streamAll(entityManager, CashBox.class)
                .where(cb-> cb.getEndDateTime() != null)
                .sortedDescendingBy(cb-> cb.getStartDateTime())
                .toList();
        
        return openCashBoxes.isEmpty()? null : openCashBoxes.get(openCashBoxes.size() - 1);
    }
}

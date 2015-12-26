/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import com.nicodemo.model.CashBox;
import com.nicodemo.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jinq.jpa.JPAJinqStream;
import org.jinq.jpa.JPQL;

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
                .where(cb-> cb.getEndDateTime() == null)                
                .sortedDescendingBy(cb-> cb.getStartDateTime())
                .toList();
        
        return openCashBoxes.isEmpty()? null : openCashBoxes.stream().findFirst().get();
    }

    public List<CashBox> getAllByDateAndUser(Date since, Date to, User user) {
        List<CashBox> cashBoxes = new ArrayList<>();

        JPAJinqStream<CashBox> query = streams.streamAll(entityManager, CashBox.class);
        query = query.where(c -> c.getStartDateTime().after(since))
                    .where(c -> c.getEndDateTime().before(to));        
        if (user != null && user.getId() > 0) {
            int userId = user.getId();
            query = query.where(c -> c.getUser().getId() == userId);                    
        }
        cashBoxes = query.toList();
        
        return cashBoxes;
    }
}

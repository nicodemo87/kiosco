/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import com.nicodemo.persistence.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Nico
 */
abstract public class  DAO<T> {
    protected Session session;
    public DAO(){
        session = NewHibernateUtil.getSessionFactory().openSession();
    }
    
    public T save(T model){        
        session.beginTransaction();
        model = (T)session.save(model);
        session.getTransaction().commit();
        
        return model;
    }
    
    public T update(T model){        
        session.beginTransaction();
        session.update(model);
        session.getTransaction().commit();
        
        return model;
    }
        
    public void delete(T model){        
        session.beginTransaction();
        session.delete(model);
        session.getTransaction().commit();
    }
    
    public abstract List<T> getAll();
    
    public abstract T getById(int id);
}

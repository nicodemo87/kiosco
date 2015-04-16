/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Nico
 * @param <T>
 */
abstract public class DAO<T> {

    protected EntityManager entityManager;
    protected JinqJPAStreamProvider streams;

    public DAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("KioscoManagerPU");
        entityManager = entityManagerFactory.createEntityManager();
        streams = new JinqJPAStreamProvider(entityManager.getMetamodel());
    }

    public T save(T model) {        
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(model);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        }
        return model;
    }

    public T update(T model) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(model);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        }
        return model;
    }

    public void delete(T model) {
        try {
            entityManager.getTransaction().begin();
            entityManager.detach(model);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }

    public abstract List<T> getAll();

    public abstract T getById(int id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import com.nicodemo.model.User;
import java.util.List;

/**
 *
 * @author Nico
 */
public class UsersDAO extends DAO<User> {

    @Override
    public List<User> getAll() {        
        List<User> users = streams.streamAll(entityManager, User.class)
                .toList();
        //List sales = entityManager.createQuery("SELECT s Sale s").getResultList();
        return users;
    }

    @Override
    public User getById(int id) {
        return (User) entityManager.find(User.class, id);
    }
}
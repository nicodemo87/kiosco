/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import com.nicodemo.model.User;
import com.nicodemo.persistence.exceptions.ElementNotFoundException;
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
        return users;
    }

    @Override
    public User getById(int id) {
        return (User) entityManager.find(User.class, id);
    }

    public User getUserByName(String name) throws ElementNotFoundException {
        User user = streams.streamAll(entityManager, User.class)
                .where(c -> c.getName() == name)
                .findFirst()
                .get();
        if (user == null) {
            throw new ElementNotFoundException("No se encontro el usaurio: "+ name);
        }
        return user;
    }
}
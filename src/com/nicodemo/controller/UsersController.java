/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.controller;

import com.nicodemo.model.User;
import com.nicodemo.persistence.DAOs.UsersDAO;
import com.nicodemo.persistence.exceptions.ElementNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nico
 */
public class UsersController {

    private UsersDAO usersDAO;

    @Autowired
    public UsersController(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public List<User> getUsers() {
        return usersDAO.getAll();
    }

    public void saveUser(User user) {
        usersDAO.save(user);
    }

    public void login(String name, char[] password) throws ElementNotFoundException {
        try {
            User user = usersDAO.getUserByName(name);
            if (!Arrays.equals(user.getPassword().toCharArray(), password)) {
                throw new ElementNotFoundException("Password incorrecto para usuario: " + name);
            }
            User.setCurrentUser(user);
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
            throw new ElementNotFoundException("Nombre o Password incorrecto.");
        }
    }
}

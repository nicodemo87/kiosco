/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.controller;

import com.nicodemo.model.User;
import com.nicodemo.persistence.DAOs.UsersDAO;
import java.util.List;
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
    
    public void saveUser(User user){
        usersDAO.save(user);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import com.nicodemo.model.Client;
import java.util.List;

/**
 *
 * @author Nico
 */
public class ClientsDAO extends DAO<Client> {

    @Override
    public List<Client> getAll() {        
        List<Client> users = streams.streamAll(entityManager, Client.class)
                .toList();
        //List sales = entityManager.createQuery("SELECT s Sale s").getResultList();
        return users;
    }

    @Override
    public Client getById(int id) {
        return (Client) entityManager.find(Client.class, id);
    }
}
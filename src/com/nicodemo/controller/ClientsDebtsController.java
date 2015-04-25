/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.controller;

import com.nicodemo.model.Client;
import com.nicodemo.persistence.DAOs.ClientsDAO;
import com.nicodemo.persistence.exceptions.ElementNotFoundException;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nico
 */
public class ClientsDebtsController {
    private ClientsDAO clientsDAO;
    
    @Autowired
    public ClientsDebtsController(ClientsDAO clientsDAO){
        this.clientsDAO = clientsDAO;
    }
    
    public List<Client> allClients(){      
        return clientsDAO.getAll();
    }

    public List<Client> findClients(String keyword) throws ElementNotFoundException {
        return clientsDAO.findClients(keyword);
    }

    public Client getClientByDni(int dni) throws ElementNotFoundException {
        return clientsDAO.getByDni(dni);
    }
}

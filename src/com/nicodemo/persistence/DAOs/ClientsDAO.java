/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.persistence.DAOs;

import com.nicodemo.model.Client;
import com.nicodemo.persistence.exceptions.ElementNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.jinq.jpa.JPQL;

/**
 *
 * @author Nico
 */
public class ClientsDAO extends DAO<Client> {

    @Override
    public List<Client> getAll() {
        List<Client> clients = streams.streamAll(entityManager, Client.class)
                .toList();
        return clients;
    }

    @Override
    public Client getById(int id) {
        return (Client) entityManager.find(Client.class, id);
    }

    public List<Client> findClients(String keyword) throws ElementNotFoundException {

        List<Client> clients = new ArrayList<>();

        try {
            int dni = Integer.valueOf(keyword.trim());
            clients = streams.streamAll(entityManager, Client.class)
                    .where(c -> c.getDni() == dni)
                    .toList();
        } catch (NumberFormatException ex) {
        }

        if (clients.isEmpty()) {
            clients = streams.streamAll(entityManager, Client.class)
                    .where(c -> JPQL.like(keyword, c.getFirstName()))
                    .toList();
        }
        if (clients.isEmpty()) {
            clients = streams.streamAll(entityManager, Client.class)
                    .where(c -> JPQL.like(keyword, c.getLastName()))
                    .toList();
        }
        if (clients.isEmpty()) {
            throw new ElementNotFoundException("No se encontraron clientes con dni, nombre o apellido igual a " + keyword);
        }
        return clients;
    }

    public Client getByDni(Integer dni) throws ElementNotFoundException {
        List<Client> clients = streams.streamAll(entityManager, Client.class)
                .where(c -> c.getDni() == dni)
                .toList();
        if (clients.isEmpty()) {
            throw new ElementNotFoundException("No se encontraron clientes con dni " + dni);
        }
        return clients.get(0);
    }
}

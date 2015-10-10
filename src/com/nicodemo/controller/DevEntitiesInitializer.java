/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.controller;

import com.nicodemo.model.Client;
import com.nicodemo.model.Item;
import com.nicodemo.model.ItemBrand;
import com.nicodemo.model.ItemKind;
import com.nicodemo.model.User;
import com.nicodemo.persistence.DAOs.CashBoxesDAO;
import com.nicodemo.persistence.DAOs.ClientsDAO;
import com.nicodemo.persistence.DAOs.ItemsDAO;
import com.nicodemo.persistence.DAOs.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nico
 */
public class DevEntitiesInitializer {

    private ItemsDAO itemsDAO;
    private CashBoxesDAO cashBoxesDAO;
    private UsersDAO usersDAO;
    private ClientsDAO clientsDAO;

    @Autowired
    public DevEntitiesInitializer(ItemsDAO itemsDAO, CashBoxesDAO cashBoxesDAO, UsersDAO usersDAO, ClientsDAO clientsDAO) {
        this.itemsDAO = itemsDAO;
        this.cashBoxesDAO = cashBoxesDAO;
        this.usersDAO = usersDAO;
        this.clientsDAO = clientsDAO;
    }

    public void Initialize() {
        ItemKind kind1 = new ItemKind("Gaseosas");
        ItemKind kind2 = new ItemKind("Alfajores");
        ItemKind kind3 = new ItemKind("Chicles");

        itemsDAO.saveKind(kind1);
        itemsDAO.saveKind(kind2);
        itemsDAO.saveKind(kind3);

        ItemBrand brand1 = new ItemBrand("CocaCola");
        ItemBrand brand2 = new ItemBrand("JOrgito");
        ItemBrand brand3 = new ItemBrand("Beldent");
        ItemBrand brand4 = new ItemBrand("Pepsi");

        itemsDAO.saveBrand(brand1);
        itemsDAO.saveBrand(brand2);
        itemsDAO.saveBrand(brand3);
        itemsDAO.saveBrand(brand4);

        Item item1 = new Item("123-1", "CocaCola 600ml", 10, 15, 10, kind1, brand1);
        Item item11 = new Item("123-2", "Sprite 600ml", 10, 15, 10, kind1, brand1);
        Item item12 = new Item("123-3", "Fanta 600ml", 10, 15, 10, kind1, brand1);
        Item item2 = new Item("321-1", "Aljajor Jorgito Choco", 5, 8, 10, kind2, brand2);
        Item item22 = new Item("321-2", "Aljajor Jorgito Fruta", 5, 8, 10, kind2, brand2);
        Item item3 = new Item("213", "Chicles Beldent Menta", 5, 6, 10, kind3, brand3);
        Item item4 = new Item("1233-1", "Pepsi Cola 500ml", 10, 15, 10, kind1, brand4);
        Item item41 = new Item("1233-2", "7up 500ml", 10, 15, 10, kind1, brand4);

        itemsDAO.save(item1);
        itemsDAO.save(item11);
        itemsDAO.save(item12);
        itemsDAO.save(item2);
        itemsDAO.save(item22);
        itemsDAO.save(item3);
        itemsDAO.save(item4);
        itemsDAO.save(item41);

        User user = new User("root");
        user.setPassword("1234");
        user.addPermission(User.Permission.Root);

        usersDAO.save(user);
        //User.setCurrentUser(user);

        Client client1 = new Client(11222333, "Juan", "Perez", "11223344");
        Client client2 = new Client(22333444, "Maria", "Rodriguez", "223344");
        Client client3 = new Client(33444555, "Carlos", "Sanches", "15654123");

        clientsDAO.save(client1);
        clientsDAO.save(client2);
        clientsDAO.save(client3);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Nico
 */
@Entity
@Table
public class User {

    public enum Permission {

        Root("Todos los permisos"),
        CashBoxPanel("Ver Caja"),
        ClientsPanel("Ver Clientes"),
        CurrentSalePanel("Operar venta actual"),
        DebtsDetailsDialog("Cobrar deuda de Cliente"),
        FindClientDialog("Buscar clientes"),
        ItemsPanel("Ver Artículos"),
        LatestSalesPanel("Ver ultimas ventas"),
        UsersPanel("Administrar usuarios"), 
        AddItem("Agregar nuevo Artículo"), 
        UpdateItem("Modificar datos de Artículo"),
        UpdateItemStock("Puede editar Stock"),
        SeeItemsCost("Puede ver el costo de los artículos"),
        CashBoxesHistory("Puede consultar historial de cajas");
        
        private String description;

        private Permission(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    private static User currentUser;

    public User() {
    }    
    
    /**
     * @return the curretnUser
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * @param aCurretnUser the curretnUser to set
     */
    public static void setCurrentUser(User aCurrentUser) {
        currentUser = aCurrentUser;
    }

    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String password;
    @OneToMany
    private Set<Rol> roles;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions;

    public User(String name) {
        this.name = name;
        this.permissions = new HashSet();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the roles
     */
    public Set<Rol> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public boolean hasPermission(Permission permission) {
        return this.permissions.contains(permission);
    }
    
    public boolean hasPermissionOrIsRoot(Permission permission) {
        return this.permissions.contains(permission) || this.permissions.contains(Permission.Root);
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}

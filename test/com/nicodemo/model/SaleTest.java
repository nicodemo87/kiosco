/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.model;

import junit.framework.TestCase;

/**
 *
 * @author Nico
 */
public class SaleTest extends TestCase {
    
    public SaleTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAddItemsAndGetTotal() {
        System.out.println("getId");
        Sale sale = new Sale();
        
        sale.addItem(new Item("item1","",100,200,0, null, null), 1);
        sale.addItem(new Item("item2","",200,200,0, null, null), 2);
        sale.addItem(new Item("item3","",300,200,0, null, null), 1);
        
        double total = sale.total();
               
        assertEquals(total, 800d);
    }
    
    public void testAddTwoTimesTheSameItem(){
        Item item1 = new Item("item1","item1",100,250,0, null, null);
        
        Sale sale = new Sale();
        
        sale.addItem(item1, 1);
        sale.addItem(item1, 1);
        
        double total = sale.total();
        int intem1Quantity = sale.getSoldItems().stream().findFirst().get().getQuantity();
        
        assertEquals(intem1Quantity, 2);
        assertEquals(total, item1.getPrice() * 2d);
    }
}

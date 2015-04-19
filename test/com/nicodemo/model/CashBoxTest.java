/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.model;

import java.util.Date;
import java.util.Set;
import junit.framework.TestCase;

/**
 *
 * @author Nico
 */
public class CashBoxTest extends TestCase {
    
    public CashBoxTest(String testName) {
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

    public void testAddSalesToCashBoxAndGetTotal() {
        Item item100 = new Item("item100","item1",100,100,0);
        Item item200 = new Item("item200","item1",100,200,0);
        Item item300 = new Item("item300","item1",100,300,0);
        
        Sale sale200 = new Sale();
        sale200.addItem(item100, 2);
        
        Sale sale500 = new Sale();
        sale500.addItem(item200, 1);        
        sale500.addItem(item300, 1);
        
        CashBox cashBox700 = new CashBox();
        cashBox700.addSale(sale200);
        cashBox700.addSale(sale500);
        
        double total = cashBox700.sold();
        double expectedTotal = item100.getPrice()*2 + item200.getPrice() + item300.getPrice();
        assertEquals(total, expectedTotal);
        
    }
    
}

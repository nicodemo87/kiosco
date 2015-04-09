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

    public void testGetTotal() {
        System.out.println("getId");
        Sale sale = new Sale();
        
        sale.addItem(new Item("","",100,200,0), 1);
        sale.addItem(new Item("","",200,200,0), 2);
        sale.addItem(new Item("","",300,200,0), 1);
        
        float total = sale.total();
               
        assertEquals(total, 800f);
    }      
}

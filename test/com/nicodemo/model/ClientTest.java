/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.model;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 *
 * @author Nico
 */
public class ClientTest extends TestCase {

    public ClientTest(String testName) {
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

    public void testAddDebtsToClient() {
        Item item100 = new Item("item100", "item1", 100, 100, 0, null, null);
        Item item200 = new Item("item200", "item1", 100, 200, 0, null, null);
        Item item300 = new Item("item300", "item1", 100, 300, 0, null, null);

        Sale sale200 = new Sale();
        sale200.addItem(item100, 2);

        Sale sale500 = new Sale();
        sale500.addItem(item200, 1);
        sale500.addItem(item300, 1);

        Client client = new Client(1, "", "", "", "");
        client.addDebt(new Debt(sale200));
        client.addDebt(new Debt(sale500));

        double total = client.debt();
        double expectedTotal = item100.getPrice() * 2 + item200.getPrice() + item300.getPrice();
        assertEquals(total, expectedTotal);
    }

    public void testCancelPartialDebtsToClient() {
        Item item100 = new Item("item100", "item1", 100, 100, 0, null, null);
        Item item200 = new Item("item200", "item1", 100, 200, 0, null, null);
        Item item300 = new Item("item300", "item1", 100, 300, 0, null, null);

        Sale sale200 = new Sale();
        sale200.addItem(item100, 2);

        Sale sale500 = new Sale();
        sale500.addItem(item200, 1);
        sale500.addItem(item300, 1);

        Client client = new Client(1, "", "", "", "");
        client.addDebt(new Debt(sale200));
        client.addDebt(new Debt(sale500));

        client.cancelDebt(100d);

        double total = client.debt();
        double expectedTotal = item100.getPrice() * 2 + item200.getPrice() + item300.getPrice() - 100;
        assertEquals(expectedTotal, total);
    }

    public void testCancelHalfDebtsToClient() {
        Item item100 = new Item("item100", "item1", 100, 100, 0, null, null);
        Item item200 = new Item("item200", "item1", 100, 200, 0, null, null);
        Item item300 = new Item("item300", "item1", 100, 300, 0, null, null);

        Sale sale200 = new Sale();
        sale200.addItem(item100, 2);

        Sale sale500 = new Sale();
        sale500.addItem(item200, 1);
        sale500.addItem(item300, 1);

        Client client = new Client(1, "", "", "", "");
        client.addDebt(new Debt(sale200));
        client.addDebt(new Debt(sale500));

        client.cancelDebt(client.debt() / 2);

        double total = client.debt();
        double expectedTotal = (item100.getPrice() * 2 + item200.getPrice() + item300.getPrice()) / 2;
        assertEquals(expectedTotal, total);
    }

    public void testCancelTotalDebtsToClient() {
        Item item100 = new Item("item100", "item1", 100, 100, 0, null, null);
        Item item200 = new Item("item200", "item1", 100, 200, 0, null, null);
        Item item300 = new Item("item300", "item1", 100, 300, 0, null, null);

        Sale sale200 = new Sale();
        sale200.addItem(item100, 2);

        Sale sale500 = new Sale();
        sale500.addItem(item200, 1);
        sale500.addItem(item300, 1);

        Client client = new Client(1, "", "", "", "");
        client.addDebt(new Debt(sale200));
        client.addDebt(new Debt(sale500));

        client.cancelDebt(client.debt());

        double total = client.debt();
        double expectedTotal = 0;
        assertEquals(expectedTotal, total);
    }

    public void testCancelTotalDebtsToClientInTwoPayments() {
        Item item100 = new Item("item100", "item1", 100, 100, 0, null, null);
        Item item200 = new Item("item200", "item1", 100, 200, 0, null, null);
        Item item300 = new Item("item300", "item1", 100, 300, 0, null, null);

        Sale sale200 = new Sale();
        sale200.addItem(item100, 2);

        Sale sale500 = new Sale();
        sale500.addItem(item200, 1);
        sale500.addItem(item300, 1);

        Client client = new Client(1, "", "", "", "");
        client.addDebt(new Debt(sale200));
        client.addDebt(new Debt(sale500));

        client.cancelDebt(client.debt() / 2);
        client.cancelDebt(client.debt());

        double total = client.debt();
        double expectedTotal = 0;
        assertEquals(expectedTotal, total);
    }

    public void testCancelHalfDebtsToClientInTwoPayments() {
        Item item100 = new Item("item100", "item1", 100, 100, 0, null, null);
        Item item200 = new Item("item200", "item1", 100, 200, 0, null, null);
        Item item300 = new Item("item300", "item1", 100, 300, 0, null, null);

        Sale sale200 = new Sale();
        sale200.addItem(item100, 2);

        Sale sale500 = new Sale();
        sale500.addItem(item200, 1);
        sale500.addItem(item300, 1);

        Client client = new Client(1, "", "", "", "");
        client.addDebt(new Debt(sale200));
        client.addDebt(new Debt(sale500));

        double originalDebt = client.debt();

        client.cancelDebt(originalDebt / 4);
        client.cancelDebt(originalDebt / 4);

        double total = client.debt();
        double expectedTotal = originalDebt / 2;
        assertEquals(expectedTotal, total);
    }

    public void testCancelHalfDebtsToClientInFourPayments() {
        Item item100 = new Item("item100", "item1", 100, 100, 0, null, null);
        Item item200 = new Item("item200", "item1", 100, 200, 0, null, null);
        Item item300 = new Item("item300", "item1", 100, 300, 0, null, null);

        Sale sale100 = new Sale();
        sale100.addItem(item100, 1);
        
        Sale sale500 = new Sale();
        sale500.addItem(item200, 1);
        sale500.addItem(item300, 1);

        Sale sale400 = new Sale();
        sale400.addItem(item100, 4);

        Client client = new Client(1, "", "", "", "");
        client.addDebt(new Debt(sale100));
        client.addDebt(new Debt(sale400));
        client.addDebt(new Debt(sale500));

        double originalDebt = client.debt();

        client.cancelDebt(originalDebt / 8);
        client.cancelDebt(originalDebt / 8);
        client.cancelDebt(originalDebt / 8);
        client.cancelDebt(originalDebt / 8);

        double total = client.debt();
        double expectedTotal = originalDebt / 2;
        assertEquals(expectedTotal, total);
    }

}

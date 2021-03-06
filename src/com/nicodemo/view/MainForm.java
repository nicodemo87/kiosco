/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.view;

import com.nicodemo.controller.ClientsDebtsController;
import com.nicodemo.controller.DevEntitiesInitializer;
import com.nicodemo.controller.ItemsController;
import com.nicodemo.controller.SaleController;
import com.nicodemo.controller.UsersController;
import com.nicodemo.model.CashBox;
import com.nicodemo.model.User;
import com.nicodemo.persistence.DAOs.CashBoxesDAO;
import com.nicodemo.persistence.DAOs.ClientsDAO;
import com.nicodemo.persistence.DAOs.ItemsDAO;
import com.nicodemo.persistence.DAOs.UsersDAO;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Nico
 */
public class MainForm extends javax.swing.JFrame {

    private static ApplicationContext context;

    /**
     * Creates new form Main
     */
    public MainForm(ApplicationContext context) {
        this.context = context;

        initComponents();

        //TODO LOGIN
        //User.setCurrentUser(context.getBean(UsersController.class).getUsers().stream().findFirst().get());
        // *** Current CashBox ***
        SaleController saleController = context.getBean(SaleController.class);
        CashBoxPanel currentCashBoxPanel = new CashBoxPanel(saleController, context.getBean(UsersDAO.class), context.getBean(CashBoxesDAO.class));
        currentCashBoxPanel.setVisible(User.getCurrentUser().hasPermissionOrIsRoot(User.Permission.CashBoxPanel));
        this.jPanel_tabCurrentCashBox.setLayout(new BorderLayout());
        this.jPanel_tabCurrentCashBox.add(currentCashBoxPanel, BorderLayout.CENTER);
        this.jPanel_tabCurrentCashBox.setEnabled(User.getCurrentUser().hasPermissionOrIsRoot(User.Permission.CashBoxPanel));

        // *** Current Sale ***
        if (User.getCurrentUser().hasPermissionOrIsRoot(User.Permission.CurrentSalePanel)) {
            CurrentSalePanel currentSalePanel = new CurrentSalePanel(this, saleController, currentCashBoxPanel, context.getBean(ClientsDebtsController.class), context.getBean(ItemsController.class));
            currentSalePanel.setVisible(User.getCurrentUser().hasPermissionOrIsRoot(User.Permission.CurrentSalePanel));
            this.jPanel_tabCurrentSale.setLayout(new BorderLayout());
            this.jPanel_tabCurrentSale.add(currentSalePanel, BorderLayout.CENTER);
            this.jPanel_tabCurrentSale.setEnabled(User.getCurrentUser().hasPermissionOrIsRoot(User.Permission.CurrentSalePanel));

            CashBox cashBox = saleController.getCurrentCashBox();
            if (saleController.existAlreadyOpenCashBox()) {

                JOptionPane.showMessageDialog(null, "Recuperando caja abierta. \n"
                        + "Fecha apertura: " + cashBox.getStartDateTime() + "\n"
                        + "Usuario: " + cashBox.getUser().getName() + "\n"
                        + "Monto inicial: " + cashBox.getStartAmount() + "\n"
                        + "Total en caja:" + cashBox.totalCash());
            } else {
                Double initialAmount = null;
                while (initialAmount == null) {
                    try {
                        initialAmount = Double.valueOf(JOptionPane.showInputDialog(null, "Ingrese monto $ inicial de la caja:", "0"));
                        currentCashBoxPanel.refresh(cashBox);
                    } catch (Exception ex) {
                    }
                }
                saleController.setStartAmount(initialAmount);
            }

        }

        // *** Items ***
        ItemsPanel itemsPanel = new ItemsPanel(this, context.getBean(ItemsController.class));
        itemsPanel.setVisible(User.getCurrentUser().hasPermissionOrIsRoot(User.Permission.ItemsPanel));
        this.jPanel_tabItems.setLayout(new BorderLayout());
        this.jPanel_tabItems.add(itemsPanel, BorderLayout.CENTER);

        // *** Clients ***       
        ClientsPanel clientsPanel = new ClientsPanel(context.getBean(ClientsDebtsController.class), context.getBean(ClientsDAO.class));
        clientsPanel.setVisible(User.getCurrentUser().hasPermissionOrIsRoot(User.Permission.ClientsPanel));
        this.jPanel_tabClients.setLayout(new BorderLayout());
        this.jPanel_tabClients.add(clientsPanel, BorderLayout.CENTER);

        // *** Latest Sales ***
        LatestSalesPanel latestSalesPanel = new LatestSalesPanel(saleController, null);
        latestSalesPanel.setVisible(User.getCurrentUser().hasPermissionOrIsRoot(User.Permission.LatestSalesPanel));
        this.jPanel_tabLastestSales.setLayout(new BorderLayout());
        this.jPanel_tabLastestSales.add(latestSalesPanel, BorderLayout.CENTER);

        // *** Users ***
        UsersPanel usersPanel = new UsersPanel(context.getBean(UsersController.class));
        usersPanel.setVisible(User.getCurrentUser().hasPermissionOrIsRoot(User.Permission.UsersPanel));
        this.jPanel_Users.setLayout(new BorderLayout());
        this.jPanel_Users.add(usersPanel, BorderLayout.CENTER);

        addFnKeyBindings(jTabbedPane_main);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane_main = new javax.swing.JTabbedPane();
        jPanel_tabCurrentSale = new javax.swing.JPanel();
        jPanel_tabItems = new javax.swing.JPanel();
        jPanel_tabCurrentCashBox = new javax.swing.JPanel();
        jPanel_tabClients = new javax.swing.JPanel();
        jPanel_tabLastestSales = new javax.swing.JPanel();
        jPanel_Users = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 500));

        jPanel_tabCurrentSale.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel_tabCurrentSaleFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel_tabCurrentSaleLayout = new javax.swing.GroupLayout(jPanel_tabCurrentSale);
        jPanel_tabCurrentSale.setLayout(jPanel_tabCurrentSaleLayout);
        jPanel_tabCurrentSaleLayout.setHorizontalGroup(
            jPanel_tabCurrentSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
        jPanel_tabCurrentSaleLayout.setVerticalGroup(
            jPanel_tabCurrentSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        jTabbedPane_main.addTab("Venta Actual", jPanel_tabCurrentSale);

        javax.swing.GroupLayout jPanel_tabItemsLayout = new javax.swing.GroupLayout(jPanel_tabItems);
        jPanel_tabItems.setLayout(jPanel_tabItemsLayout);
        jPanel_tabItemsLayout.setHorizontalGroup(
            jPanel_tabItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
        jPanel_tabItemsLayout.setVerticalGroup(
            jPanel_tabItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        jTabbedPane_main.addTab("Articulos", jPanel_tabItems);

        javax.swing.GroupLayout jPanel_tabCurrentCashBoxLayout = new javax.swing.GroupLayout(jPanel_tabCurrentCashBox);
        jPanel_tabCurrentCashBox.setLayout(jPanel_tabCurrentCashBoxLayout);
        jPanel_tabCurrentCashBoxLayout.setHorizontalGroup(
            jPanel_tabCurrentCashBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
        jPanel_tabCurrentCashBoxLayout.setVerticalGroup(
            jPanel_tabCurrentCashBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        jTabbedPane_main.addTab("Caja", jPanel_tabCurrentCashBox);

        javax.swing.GroupLayout jPanel_tabClientsLayout = new javax.swing.GroupLayout(jPanel_tabClients);
        jPanel_tabClients.setLayout(jPanel_tabClientsLayout);
        jPanel_tabClientsLayout.setHorizontalGroup(
            jPanel_tabClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
        jPanel_tabClientsLayout.setVerticalGroup(
            jPanel_tabClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        jTabbedPane_main.addTab("Clientes", jPanel_tabClients);

        javax.swing.GroupLayout jPanel_tabLastestSalesLayout = new javax.swing.GroupLayout(jPanel_tabLastestSales);
        jPanel_tabLastestSales.setLayout(jPanel_tabLastestSalesLayout);
        jPanel_tabLastestSalesLayout.setHorizontalGroup(
            jPanel_tabLastestSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
        jPanel_tabLastestSalesLayout.setVerticalGroup(
            jPanel_tabLastestSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        jTabbedPane_main.addTab("Últimas Ventas", jPanel_tabLastestSales);

        javax.swing.GroupLayout jPanel_UsersLayout = new javax.swing.GroupLayout(jPanel_Users);
        jPanel_Users.setLayout(jPanel_UsersLayout);
        jPanel_UsersLayout.setHorizontalGroup(
            jPanel_UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
        jPanel_UsersLayout.setVerticalGroup(
            jPanel_UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        jTabbedPane_main.addTab("Users", jPanel_Users);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane_main)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane_main))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_tabCurrentSaleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel_tabCurrentSaleFocusGained
        //jPanel_tabCurrentSale.getComponent(0).requestFocus();
    }//GEN-LAST:event_jPanel_tabCurrentSaleFocusGained

    public void addFnKeyBindings(JComponent jc) {
        jc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, true), "F1 released");
        jc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, true), "F2 released");
        jc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0, true), "F3 released");
        jc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0, true), "F4 released");
        jc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, true), "F5 released");
        jc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0, true), "F6 released");

        jc.getActionMap().put("F1 released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jTabbedPane_main.setSelectedIndex(0);
            }
        });
        jc.getActionMap().put("F2 released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jTabbedPane_main.setSelectedIndex(1);
            }
        });
        jc.getActionMap().put("F3 released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jTabbedPane_main.setSelectedIndex(2);
            }
        });
        jc.getActionMap().put("F4 released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jTabbedPane_main.setSelectedIndex(3);
            }
        });
        jc.getActionMap().put("F5 released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jTabbedPane_main.setSelectedIndex(4);
            }
        });
        jc.getActionMap().put("F6 released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jTabbedPane_main.setSelectedIndex(5);
            }
        });
    }

    @Override
    public void setVisible(boolean b) {
        if (b) {

        }
        super.setVisible(b); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        context = new ClassPathXmlApplicationContext("main/resources/beans.xml");

        //context.getBean(DevEntitiesInitializer.class).Initialize();
        LoginForm loginForm = new LoginForm(context, context.getBean(UsersController.class));
        loginForm.setLocationRelativeTo(null);
        loginForm.setVisible(true);
    }

    public static ApplicationContext getContext() {
        return context;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel_Users;
    private javax.swing.JPanel jPanel_tabClients;
    private javax.swing.JPanel jPanel_tabCurrentCashBox;
    private javax.swing.JPanel jPanel_tabCurrentSale;
    private javax.swing.JPanel jPanel_tabItems;
    private javax.swing.JPanel jPanel_tabLastestSales;
    private javax.swing.JTabbedPane jTabbedPane_main;
    // End of variables declaration//GEN-END:variables
}

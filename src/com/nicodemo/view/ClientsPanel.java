/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.view;

import com.nicodemo.controller.ClientsDebtsController;
import com.nicodemo.model.Client;
import com.nicodemo.persistence.DAOs.ClientsDAO;
import com.nicodemo.persistence.exceptions.ElementNotFoundException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nico
 */
public class ClientsPanel extends javax.swing.JPanel {

    private ClientsDebtsController clientsDebtsController;
    private DefaultTableModel clientsTableModel;
    private ClientsDAO clientsDAO;

    /**
     * Creates new form ClientsPanel
     */
    public ClientsPanel(ClientsDebtsController clientsDebtsController, ClientsDAO clientsDAO) {
        this.clientsDAO = clientsDAO;
        this.clientsDebtsController = clientsDebtsController;
        initComponents();
        clearTableModel();
        jTable_Clients.setAutoCreateRowSorter(true);
        jButton_findActionPerformed(null);      
    }

    private void clearTableModel() {
        clientsTableModel = new NoEditableTableModel();

        String header[] = new String[]{"Dni", "Nombre", "Apellido", "Telefono",
            "Deuda"};
        clientsTableModel.setColumnIdentifiers(header);
        jTable_Clients.setModel(clientsTableModel);
    }

    private Client getSelectedClient() {
        Client client = null;
        int rowIndex = jTable_Clients.getSelectedRow();
        if (rowIndex >= 0) {
            try {
                int dni = Integer.parseInt(clientsTableModel.getValueAt(rowIndex, 0).toString());
                client = clientsDebtsController.getClientByDni(dni);
            } catch (ElementNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe elegir un cliente de la lista");
        }
        return client;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton_addClient = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Clients = new javax.swing.JTable();
        jTextField_keyword = new javax.swing.JTextField();
        jButton_find = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jButton_payDebt = new javax.swing.JButton();
        jButton_details = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Listado de Clientes");

        jButton_addClient.setText("+ Cliente");
        jButton_addClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addClientActionPerformed(evt);
            }
        });

        jTable_Clients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable_Clients);

        jButton_find.setText("Buscar");
        jButton_find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_findActionPerformed(evt);
            }
        });

        jButton_update.setText("Modificar Cliente");
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });

        jButton_payDebt.setText("Saldar Deuda");
        jButton_payDebt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_payDebtActionPerformed(evt);
            }
        });

        jButton_details.setText("Ver Deudas");
        jButton_details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_detailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField_keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_find)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_addClient))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_details)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_payDebt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_update)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_keyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_find)
                    .addComponent(jButton_addClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_update)
                    .addComponent(jButton_payDebt)
                    .addComponent(jButton_details))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_addClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addClientActionPerformed
        showAddUpdateDialog(null);
    }//GEN-LAST:event_jButton_addClientActionPerformed

    private void showAddUpdateDialog(Client client) {
        AddClientDialog dialog = new AddClientDialog(null, true, clientsDAO, client);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {                
                dialog.dispose();
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        jButton_findActionPerformed(null);
    }

    private void jButton_findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_findActionPerformed
        try {
            List<Client> clients;
            if (jTextField_keyword.getText().isEmpty()) {
                clients = clientsDebtsController.allClients();
            } else {
                clients = clientsDebtsController.findClients(jTextField_keyword.getText());
            }
            jTextField_keyword.setText("");
            clearTableModel();
            clients.stream().forEach(c -> clientsTableModel.addRow(new Object[]{c.getDni(), c.getFirstName(), c.getLastName(), c.getPhone(), String.valueOf(c.debt())}));
            jTable_Clients.setRowSelectionInterval(0, 0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton_findActionPerformed

    private void jButton_payDebtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_payDebtActionPerformed
        Client client = getSelectedClient();
        if (client != null) {
            try {
                double amount = Double.parseDouble(JOptionPane
                        .showInputDialog("Ingrese el monto que el cliente desea pagar", client.debt()));
                clientsDebtsController.addPayments(client, amount);
                jTextField_keyword.setText(String.valueOf(client.getDni()));
                jButton_findActionPerformed(evt);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_payDebtActionPerformed

    private void jButton_detailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_detailsActionPerformed
        Client client = this.getSelectedClient();
        DebtsDetailsDialog dialog = new DebtsDetailsDialog(null, true, client, clientsDebtsController);
        dialog.setVisible(true);
        jTextField_keyword.setText(String.valueOf(client.getDni()));
        jButton_findActionPerformed(evt);
    }//GEN-LAST:event_jButton_detailsActionPerformed

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        showAddUpdateDialog(getSelectedClient());
    }//GEN-LAST:event_jButton_updateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_addClient;
    private javax.swing.JButton jButton_details;
    private javax.swing.JButton jButton_find;
    private javax.swing.JButton jButton_payDebt;
    private javax.swing.JButton jButton_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Clients;
    private javax.swing.JTextField jTextField_keyword;
    // End of variables declaration//GEN-END:variables
}

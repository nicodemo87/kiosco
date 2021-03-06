/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.view;

import com.nicodemo.controller.ClientsDebtsController;
import com.nicodemo.model.Client;
import com.nicodemo.model.Debt;
import com.nicodemo.model.Sale;
import java.awt.event.ActionEvent;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nico
 */
public class DebtsDetailsDialog extends javax.swing.JDialog {

    private Client client;
    private DefaultTableModel debtsTableModel;
    private DefaultTableModel debtItemsTableModel;
    private DefaultTableModel paymentsTableModel;
    private ClientsDebtsController clientsDebtsController;

    /**
     * Creates new form DebtsDetailsDialog
     */
    public DebtsDetailsDialog(java.awt.Frame parent, boolean modal, Client client, ClientsDebtsController clientsDebtsController) {
        super(parent, modal);
        this.client = client;
        this.clientsDebtsController = clientsDebtsController;
        initComponents();
        refreshDebts();
        refreshDebtItems();
        refreshPayments();

        jTable_debts.getSelectionModel()
                .addListSelectionListener(evt -> refreshSelectedDebtDetails());
        jTable_debtItems.setAutoCreateRowSorter(true);
        jTable_debts.setAutoCreateRowSorter(true);
        jTable_payments.setAutoCreateRowSorter(true);
    }

    private void refreshSelectedDebtDetails() {
        refreshDebtItems();
        refreshPayments();
    }

    private void refreshDebts() {
        debtsTableModel = new NoEditableTableModel();
        String header[] = new String[]{
            "Id",
            "Fecha y Hora",
            "Cantidad de Items",
            "Deuda Original",
            "Pagado",
            "Debe"
        };
        debtsTableModel.setColumnIdentifiers(header);
        jTable_debts.setModel(debtsTableModel);

        Set<Debt> debts = this.client.getDebts();

        debts.stream().forEach(debt
                -> debtsTableModel.addRow(new Object[]{
                    debt.getId(),
                    debt.getDate(),
                    debt.getSale().getSoldItems()
                    .stream()
                    .mapToInt(s -> s.getQuantity())
                    .sum(),
                    debt.getOriginalDebtAmount(),
                    debt.totalPaid(),
                    debt.currentDebtAmount()
                })
        );
    }

    private void refreshDebtItems() {
        debtItemsTableModel = new DefaultTableModel(0, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String header[] = new String[]{
            "Artículo",
            "Cantidad",
            "Precio de Venta",
            "SubTotal",};
        debtItemsTableModel.setColumnIdentifiers(header);
        jTable_debtItems.setModel(debtItemsTableModel);

        Debt selectedDebt = getSelectedDebt();
        if (selectedDebt != null) {
            selectedDebt.getSale().getSoldItems()
                    .stream()
                    .forEach(soldItem -> debtItemsTableModel.addRow(new Object[]{
                        soldItem.getItem().getDescription(),
                        soldItem.getQuantity(),
                        soldItem.getSellingPrice(),
                        soldItem.subTotal()})
                    );
        }
    }

    private void refreshPayments() {
        paymentsTableModel = new NoEditableTableModel();
        String header[] = new String[]{
            "Fecha de Pago",
            "Cantidad"
        };
        paymentsTableModel.setColumnIdentifiers(header);
        jTable_payments.setModel(paymentsTableModel);

        Debt selectedDebt = getSelectedDebt();
        if (selectedDebt != null) {

            selectedDebt.getPayments()
                    .stream()
                    .forEach(payment -> paymentsTableModel.addRow(new Object[]{
                        payment.getDateTime(),
                        payment.getAmount()
                    }));
        }
    }

    private Debt getSelectedDebt() {
        Debt selectedDebt = null;
        int rowIndex = jTable_debts.getSelectedRow();
        if (rowIndex >= 0) {
            int id = Integer.parseInt(debtsTableModel.getValueAt(rowIndex, 0).toString());
            selectedDebt = client.getDebts()
                    .stream()
                    .filter(debt -> debt.getId() == id)
                    .findFirst().get();
        }
        return selectedDebt;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_debts = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_debtItems = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_payments = new javax.swing.JTable();
        jButton_payDebts = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Detalle de Deudas");

        jTable_debts.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable_debts);

        jTable_debtItems.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable_debtItems);

        jTable_payments.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable_payments);

        jButton_payDebts.setText("Saldar Deudas");
        jButton_payDebts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_payDebtsActionPerformed(evt);
            }
        });

        jLabel2.setText("Articulos Vendidos");

        jLabel3.setText("Pagos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton_payDebts, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_payDebts))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_payDebtsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_payDebtsActionPerformed
        try {
            double amount = Double.parseDouble(JOptionPane
                    .showInputDialog("Ingrese el monto que el cliente desea pagar", client.debt()));
            
            clientsDebtsController.addPayments(client, amount);
            int rowIndex = jTable_debts.getSelectedRow();
            refreshDebts();
            jTable_debts.setRowSelectionInterval(rowIndex, rowIndex);
            refreshDebtItems();
            refreshPayments();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton_payDebtsActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DebtsDetailsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DebtsDetailsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DebtsDetailsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DebtsDetailsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                DebtsDetailsDialog dialog = new DebtsDetailsDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_payDebts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_debtItems;
    private javax.swing.JTable jTable_debts;
    private javax.swing.JTable jTable_payments;
    // End of variables declaration//GEN-END:variables

    @Override
    protected JRootPane createRootPane() { 
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action actionListener = new AbstractAction() { 
          public void actionPerformed(ActionEvent actionEvent) { 
            setVisible(false);
            dispose();
          } 
        } ;
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", actionListener);

        return rootPane;
    } 
}

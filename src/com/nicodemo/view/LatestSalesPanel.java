/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.view;

import com.nicodemo.controller.SaleController;
import com.nicodemo.model.Sale;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nico
 */
public class LatestSalesPanel extends javax.swing.JPanel {

    private SaleController saleController;
    private DefaultTableModel salesTableModel;
    private DefaultTableModel saleDetailTableModel;

    /**
     * Creates new form LatestSalesPanel
     */
    @Autowired
    public LatestSalesPanel(SaleController saleController) {
        this.saleController = saleController;
        initComponents();
        refreshSales();
        refreshSalesDetails();

        jTable_lastSales.getSelectionModel()
                .addListSelectionListener(evt -> refreshSalesDetails());
    }

    private void refreshSales() {
        salesTableModel = new DefaultTableModel(0, 0);
        String header[] = new String[]{
            "Id",
            "Fecha y Hora",
            "Cantidad de Items",
            "Precio",};
        salesTableModel.setColumnIdentifiers(header);
        jTable_lastSales.setModel(salesTableModel);

        Set<Sale> sales = this.saleController.getCurrentCashBox().getSales();

        sales.stream().forEach(sale
                -> salesTableModel.addRow(new Object[]{
                    sale.getId(),
                    sale.getDate(),
                    sale.getSoldItems()
                    .stream()
                    .mapToInt(s -> s.getQuantity())
                    .sum(),
                    sale.total()
                })
        );
    }

    private void refreshSalesDetails() {
        saleDetailTableModel = new DefaultTableModel(0, 0);
        String header[] = new String[]{
            "Artículo",
            "Cantidad",
            "Precio de Venta",
            "SubTotal",};
        saleDetailTableModel.setColumnIdentifiers(header);
        jTable_saleDetails.setModel(saleDetailTableModel);

        int rowIndex = jTable_lastSales.getSelectedRow();
        if (rowIndex >= 0) {
            int id = Integer.parseInt(salesTableModel.getValueAt(rowIndex, 0).toString());
            Sale selectedSale = saleController.getCurrentCashBox()
                    .getSales().stream()
                    .filter(sale -> sale.getId() == id)
                    .findFirst().get();

            selectedSale.getSoldItems()
                    .stream()
                    .forEach(soldItem -> saleDetailTableModel.addRow(new Object[]{
                        soldItem.getItem().getDescription(),
                        soldItem.getQuantity(),
                        soldItem.getSellingPrice(),
                        soldItem.subTotal()})
                    );
        }
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
        jTable_lastSales = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_saleDetails = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton_refresh = new javax.swing.JButton();
        jButton_removeSale = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Últimas Ventas");

        jTable_lastSales.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_lastSales.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable_lastSales);

        jTable_saleDetails.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable_saleDetails);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Detalle de artículos vendidos");

        jButton_refresh.setText("Refrescar");
        jButton_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_refreshActionPerformed(evt);
            }
        });

        jButton_removeSale.setText("Eliminar venta");
        jButton_removeSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_removeSaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_removeSale)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_refresh)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton_refresh)
                    .addComponent(jButton_removeSale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_refreshActionPerformed
        refreshSales();
        refreshSalesDetails();
    }//GEN-LAST:event_jButton_refreshActionPerformed

    private void jButton_removeSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_removeSaleActionPerformed
        int rowIndex = jTable_lastSales.getSelectedRow();
        if (rowIndex >= 0) {
            try {
                int id = Integer.parseInt(salesTableModel.getValueAt(rowIndex, 0).toString());
                Sale selectedSale = saleController.getCurrentCashBox()
                        .getSales().stream()
                        .filter(sale -> sale.getId() == id)
                        .findFirst().get();

                saleController.removeSale(selectedSale);
                refreshSales();
                refreshSalesDetails();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_removeSaleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_refresh;
    private javax.swing.JButton jButton_removeSale;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_lastSales;
    private javax.swing.JTable jTable_saleDetails;
    // End of variables declaration//GEN-END:variables
}

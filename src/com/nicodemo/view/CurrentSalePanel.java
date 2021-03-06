/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.view;

import com.nicodemo.controller.ClientsDebtsController;
import com.nicodemo.controller.ItemsController;
import com.nicodemo.controller.SaleController;
import com.nicodemo.model.Client;
import com.nicodemo.model.Item;
import com.nicodemo.model.SoldItem;
import com.nicodemo.persistence.exceptions.ElementNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nico
 */
public class CurrentSalePanel extends javax.swing.JPanel {

    private SaleController saleController;
    private DefaultTableModel tableModel;
    private CashBoxPanel currentCashBoxPanel;
    private ClientsDebtsController clientsDebtsController;
    private ItemsController itemsController;
    private JFrame parent;

    /**
     * Creates new form CurrentSalePanel
     */
    public CurrentSalePanel(JFrame parent, SaleController saleController, CashBoxPanel currentCashBoxPanel, ClientsDebtsController clientsDebtsController, ItemsController itemsController) {
        this.parent = parent;
        this.saleController = saleController;
        this.saleController.initCashBox();
        this.currentCashBoxPanel = currentCashBoxPanel;
        this.clientsDebtsController = clientsDebtsController;
        this.itemsController = itemsController;
        initComponents();

        refreshSoldItemsTable(null);
        jTable1.setAutoCreateRowSorter(true);
        addFnKeyBindings(this);
    }

    private void refreshSoldItemsTable(Item selectedItem) {
        tableModel = new NoEditableTableModel();
        String header[] = new String[]{"Codigo", "Descripcion", "Precio", "Cantidad"};
        tableModel.setColumnIdentifiers(header);
        jTable1.setModel(tableModel);

        Set<SoldItem> soldItems = saleController.getSoldItems();

        jButton_Sell.setEnabled(!soldItems.isEmpty());

        soldItems.stream()
                .forEach(si -> tableModel.addRow(
                                new Object[]{
                                    si.getItem().getCode(),
                                    si.getItem().getDescription(),
                                    si.getItem().getPrice(),
                                    si.getQuantity()
                                })
                );

        refreshTotal();

        int selectedItemIndex = saleController.getSoldItems().size() - 1;
        if (selectedItem != null) {
            for (int i = 0; i < saleController.getSoldItems().size(); i++) {
                if (((SoldItem) saleController.getSoldItems().toArray()[i]).getItem().getCode().equals(selectedItem.getCode())) {
                    selectedItemIndex = i;
                    break;
                }
            }
        }
        if (selectedItemIndex >= 0) {
            jTable1.setRowSelectionInterval(selectedItemIndex, selectedItemIndex);
        }

        currentCashBoxPanel.refresh(saleController.getCurrentCashBox());
        jTextField_itemCode.requestFocus();
    }

    private void refreshTotal() {
        jLabel_total.setText(String.valueOf(saleController.getTotal()));
        jTextField_payWith.setText(jLabel_total.getText());
        jLabel_moneyBack.setText("0");
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
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel_total = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_itemCode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel_moneyBack = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField_payWith = new javax.swing.JTextField();
        jButton_addItem = new javax.swing.JButton();
        jButton_Sell = new javax.swing.JButton();
        jButton_debit = new javax.swing.JButton();
        jButton_cancel = new javax.swing.JButton();
        jButton_multiplyItem = new javax.swing.JButton();
        jButton_removeItem = new javax.swing.JButton();

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jLabel1.setText("Artículo:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Total:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("$");

        jLabel_total.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel_total.setText("0.00");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Paga con:");

        jTextField_itemCode.setNextFocusableComponent(jTextField_payWith);
        jTextField_itemCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_itemCodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_itemCodeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_itemCodeKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Vuelto:");

        jLabel_moneyBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_moneyBack.setText("0.00");

        jLabel8.setText("$");

        jTextField_payWith.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_payWith.setText("0.00");
        jTextField_payWith.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_payWithFocusGained(evt);
            }
        });
        jTextField_payWith.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_payWithActionPerformed(evt);
            }
        });
        jTextField_payWith.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_payWithKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_payWithKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_payWithKeyTyped(evt);
            }
        });

        jButton_addItem.setText("Agregar");
        jButton_addItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addItemActionPerformed(evt);
            }
        });

        jButton_Sell.setText("Vender");
        jButton_Sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SellActionPerformed(evt);
            }
        });
        jButton_Sell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_SellKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButton_SellKeyTyped(evt);
            }
        });

        jButton_debit.setText("Debitar a Cliente");
        jButton_debit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_debitActionPerformed(evt);
            }
        });

        jButton_cancel.setText("Cancelar");
        jButton_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelActionPerformed(evt);
            }
        });

        jButton_multiplyItem.setText("*");
        jButton_multiplyItem.setToolTipText("Cambiar cantidad del artículo");
        jButton_multiplyItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_multiplyItemActionPerformed(evt);
            }
        });

        jButton_removeItem.setText("Supr");
        jButton_removeItem.setToolTipText("Remueve el artículo seleccionado");
        jButton_removeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_removeItemActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_itemCode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_addItem))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_total))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_moneyBack))
                            .addComponent(jTextField_payWith, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton_multiplyItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_removeItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_debit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Sell)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_itemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_addItem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel_total))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_payWith, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_moneyBack)
                            .addComponent(jLabel8))
                        .addGap(0, 140, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Sell)
                    .addComponent(jButton_debit)
                    .addComponent(jButton_cancel)
                    .addComponent(jButton_multiplyItem)
                    .addComponent(jButton_removeItem))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_addItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addItemActionPerformed
        addItemToSale();
    }//GEN-LAST:event_jButton_addItemActionPerformed

    private void jButton_SellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SellActionPerformed
        //if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere confirmar la venta? \n una vez confirmado no se puede deshacer", "Confirmar venta", JOptionPane.YES_NO_OPTION)) {
        saleController.sell();
        refreshSoldItemsTable(null);
        //}
    }//GEN-LAST:event_jButton_SellActionPerformed

    private void jButton_debitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_debitActionPerformed
        FindClientDialog findClientDialog = new FindClientDialog(parent, true, clientsDebtsController);
        Client client = findClientDialog.showDialog();
        if (client != null) {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere debitar la venta a "+client.getLastName()+" "+client.getFirstName()+"? \n una vez confirmado no se puede deshacer", "Confirmar Debito", JOptionPane.YES_NO_OPTION)) {
                saleController.debit(client);
                refreshSoldItemsTable(null);
            }
        }
    }//GEN-LAST:event_jButton_debitActionPerformed

    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        saleController.newSale();
        refreshSoldItemsTable(null);
    }//GEN-LAST:event_jButton_cancelActionPerformed

    private void jButton_removeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_removeItemActionPerformed
        int rowIndex = jTable1.getSelectedRow();
        if (rowIndex >= 0) {
            try {
                String code = tableModel.getValueAt(rowIndex, 0).toString();
                saleController.removeItem(code);
                refreshSoldItemsTable(null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe elegir un artículo para quitar");
        }
    }//GEN-LAST:event_jButton_removeItemActionPerformed

    private void jButton_multiplyItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_multiplyItemActionPerformed
        int rowIndex = jTable1.getSelectedRow();
        if (rowIndex >= 0) {
            try {
                String code = tableModel.getValueAt(rowIndex, 0).toString();
                SoldItem soldItem = saleController.getSoldItemBy(code);
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Artículo: " + soldItem.getItem().getDescription() + "\nIngrese cantidad", String.valueOf(soldItem.getQuantity())));
                soldItem.setQuantity(quantity);
                refreshSoldItemsTable(soldItem.getItem());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe elegir un artículo");
        }
    }//GEN-LAST:event_jButton_multiplyItemActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        //jTextField_itemCode.requestFocus();
    }//GEN-LAST:event_formFocusGained

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased

    }//GEN-LAST:event_formMouseReleased

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased

    }//GEN-LAST:event_formKeyReleased

    private void jTextField_payWithFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_payWithFocusGained
        jTextField_payWith.selectAll();
    }//GEN-LAST:event_jTextField_payWithFocusGained

    private void jTextField_payWithKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_payWithKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            jButton_Sell.requestFocus();
        }
    }//GEN-LAST:event_jTextField_payWithKeyPressed

    private void jTextField_itemCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_itemCodeKeyPressed

    }//GEN-LAST:event_jTextField_itemCodeKeyPressed

    private void jTextField_payWithKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_payWithKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            jButton_Sell.requestFocus();
        } else {
            try {
                Double paywith = Double.valueOf(jTextField_payWith.getText());
                Double total = saleController.getTotal();
                Double moneyback = paywith - total;
                jLabel_moneyBack.setText(moneyback.toString());
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jTextField_payWithKeyReleased

    private void jTextField_itemCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_itemCodeKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !jTextField_itemCode.getText().isEmpty()) {
            addItemToSale();
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            jTextField_payWith.requestFocus();
        }
    }//GEN-LAST:event_jTextField_itemCodeKeyReleased

    private void jTextField_itemCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_itemCodeKeyTyped
        if (evt.getKeyChar() == ' ' && jTextField_itemCode.getText().isEmpty()) {
            evt.consume();
            jTextField_payWith.requestFocus();            
        } else if (evt.getKeyChar() == '*') {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_itemCodeKeyTyped

    private void jTextField_payWithKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_payWithKeyTyped
        if (evt.getKeyChar() == ' ') {
            evt.consume();
            jButton_Sell.requestFocus();            
        } else if (evt.getKeyChar() == '*') {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_payWithKeyTyped

    private void jTextField_payWithActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_payWithActionPerformed

    }//GEN-LAST:event_jTextField_payWithActionPerformed

    private void jButton_SellKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_SellKeyTyped

    }//GEN-LAST:event_jButton_SellKeyTyped

    private void jButton_SellKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_SellKeyPressed
                
        if(evt.getKeyCode() == KeyEvent.VK_LEFT){
            jButton_debit.requestFocus();
            evt.consume();
        }
    }//GEN-LAST:event_jButton_SellKeyPressed

    public void addFnKeyBindings(JComponent jc) {
        jc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, true), "F1 released");
        jc.getActionMap().put("F1 released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jTextField_payWith.requestFocus();
            }
        });
        jc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, true), "Delete released");
        jc.getActionMap().put("Delete released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jButton_removeItemActionPerformed(ae);
            }
        });
        jc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0, true), "Multiply released");
        jc.getActionMap().put("Multiply released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jButton_multiplyItemActionPerformed(ae);
            }
        });
    }

    private void addItemToSale() {
        try {
            Item addedItem = saleController.addItem(jTextField_itemCode.getText());
            jTextField_itemCode.setText("");
            refreshSoldItemsTable(addedItem);
        } catch (ElementNotFoundException ex) {

            try {
                SelectItemDialog dialog = new SelectItemDialog(parent, true, itemsController, jTextField_itemCode.getText());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                    }
                });
                dialog.setVisible(true);
                Item selectedItem = dialog.getSelectedItem();
                if (selectedItem != null) {
                    saleController.addItem(selectedItem.getCode());
                    jTextField_itemCode.setText("");
                    refreshSoldItemsTable(selectedItem);
                }
                dialog.dispose();
            } catch (ElementNotFoundException ex1) {
                Logger.getLogger(CurrentSalePanel.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Sell;
    private javax.swing.JButton jButton_addItem;
    private javax.swing.JButton jButton_cancel;
    private javax.swing.JButton jButton_debit;
    private javax.swing.JButton jButton_multiplyItem;
    private javax.swing.JButton jButton_removeItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_moneyBack;
    private javax.swing.JLabel jLabel_total;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_itemCode;
    private javax.swing.JTextField jTextField_payWith;
    // End of variables declaration//GEN-END:variables
}

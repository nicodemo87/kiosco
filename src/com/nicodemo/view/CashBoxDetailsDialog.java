/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.view;

import com.nicodemo.model.CashBox;
import com.nicodemo.model.User;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Nico
 */
public class CashBoxDetailsDialog extends javax.swing.JDialog {

    /**
     * Creates new form CashBoxDetailsDialog
     */
    public CashBoxDetailsDialog(java.awt.Frame parent, boolean modal, CashBox cashBox) {
        super(parent, modal);
        initComponents();
        LatestSalesPanel latestSalesPanel = new LatestSalesPanel(null, cashBox);
        latestSalesPanel.setVisible(true);
        this.setLayout(new BorderLayout());
        this.add(latestSalesPanel, BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
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

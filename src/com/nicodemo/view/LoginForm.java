/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.view;

import com.nicodemo.controller.UsersController;
import com.nicodemo.model.User;
import com.nicodemo.persistence.exceptions.ElementNotFoundException;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Nico
 */
public class LoginForm extends javax.swing.JFrame {

    private UsersController usersController;
    private final ApplicationContext context;

    /**
     * Creates new form NewJFrame
     */
    public LoginForm(ApplicationContext context, UsersController usersController) {
        initComponents();
        this.context = context;
        this.usersController = usersController;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTextField_Name = new javax.swing.JTextField();
        jButton_Loggin = new javax.swing.JButton();
        jPasswordField_Password = new javax.swing.JPasswordField();
        jLabel_ErrorMsg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 300));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 5, 30);
        getContentPane().add(jTextField_Name, gridBagConstraints);

        jButton_Loggin.setLabel("Login");
        jButton_Loggin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LogginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 30, 4, 30);
        getContentPane().add(jButton_Loggin, gridBagConstraints);

        jPasswordField_Password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordField_PasswordKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 5, 30);
        getContentPane().add(jPasswordField_Password, gridBagConstraints);

        jLabel_ErrorMsg.setForeground(new java.awt.Color(255, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        getContentPane().add(jLabel_ErrorMsg, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_LogginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LogginActionPerformed
        if (jTextField_Name.getText().trim().isEmpty()) {
            jLabel_ErrorMsg.setText("Debe ingresar el nombre de usuario");
        } else {
            try {
                usersController.login(jTextField_Name.getText(), jPasswordField_Password.getPassword());
                this.dispose();
                if (User.getCurrentUser() != null) {
                    /* Create and display the form */
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            MainForm main = new MainForm(context);
                            main.setLocationRelativeTo(null);
                            main.setVisible(true);
                        }
                    });
                }
            } catch (Exception ex) {
                jLabel_ErrorMsg.setText(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_LogginActionPerformed

    private void jPasswordField_PasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField_PasswordKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton_LogginActionPerformed(null);
        }
    }//GEN-LAST:event_jPasswordField_PasswordKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Loggin;
    private javax.swing.JLabel jLabel_ErrorMsg;
    private javax.swing.JPasswordField jPasswordField_Password;
    private javax.swing.JTextField jTextField_Name;
    // End of variables declaration//GEN-END:variables
}

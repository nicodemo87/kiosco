/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.view;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nico
 */
public class NoEditableTableModel extends DefaultTableModel{

    public NoEditableTableModel()  {
        super(0, 0);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

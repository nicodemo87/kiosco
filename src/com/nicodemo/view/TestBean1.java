/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nicodemo.view;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Nico
 */
public class TestBean1 {
    private TestBean2 bean;
    
    @Autowired
    public TestBean1(TestBean2 bean){
        this.bean = bean;
    }
    
    public String showBeanName(){
        return bean.getName();
    }
}

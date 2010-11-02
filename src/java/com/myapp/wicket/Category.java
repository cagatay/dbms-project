/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.wicket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author cagatay
 */
public class Category implements Serializable{
    private List<Category> subs;
    private List<Product> products;
    private int id;
    private String name;
    private String parent;

    public Category(int i, String s, String p){
        id = i;
        name = s;
        parent = p;
        subs = new ArrayList<Category>();
        products = new ArrayList<Product>();
    }

    public void setId(int i){
        id = i;
    }

    public void setName(String s){
        name = s;
    }

    public void setParent(String s){
        parent = s;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getParent(){
        return parent;
    }

    public List<Category> getSubs(){
        return Collections.unmodifiableList(subs);
    }

    public List<Product> getProducts(){
        return Collections.unmodifiableList(products);
    }
}

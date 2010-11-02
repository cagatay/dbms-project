/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.wicket;

import java.io.Serializable;

/**
 *
 * @author cagatay
 */
class Product implements Serializable{
    private int id;
    private String brand;
    private String model;
    private String category;
    private String image;
    private int votes;
    private int total;

    public Product(int ix, String s, String m, String c, String im, int v, int t){
        id = ix;
        brand = s;
        model = m;
        category = c;
        image = im;
        votes = v;
        total = t;
    }
    public int getId(){
        return id;
    }

    public void setId(int i){
        id = i;
    }

    public String getBrand(){
        return brand;
    }

    public void setBrand(String s){
        brand = s;
    }

    public String getModel(){
        return model;
    }

    public void setModel(String s){
        model = s;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String s){
        category = s;
    }

    public int getVotes(){
        return votes;
    }

    public void setVotes(int i){
        votes = i;
    }

    public void setTotal(int i){
        total = i;
    }

    public float getRating(){
        if(votes == 0) return 0;
        return total / votes ;
    }

    public String getImage(){
        return image;
    }
}

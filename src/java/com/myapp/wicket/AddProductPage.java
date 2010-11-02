/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.wicket;

import java.sql.*;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

/**
 *
 * @author chasan
 */
public class AddProductPage extends BasePage {

    private TextField Brand, Model;
    private Form form;

    public AddProductPage(final String category) throws SQLException {
        Brand = new TextField("Brand", new Model(""));
        Model = new TextField("Model", new Model(""));

        form = new Form1("addproductform", category);
        form.add(Brand);
        form.add(Model);
        add(form);
    }

    class Form1 extends Form {
        String s;

        public Form1(String id) {
            super(id);
        }

        public Form1(String id, String category){
            super(id);
            s = category;
        }

        @Override
        public void onSubmit() {
            String brand = getBrand();
            String model = getModelName();
            try {
                Application.get().addProduct(brand, model, s);
                setResponsePage(HomePage.class);
            } catch (SQLException ex) {
                //Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected String getBrand() {
        return Brand.getInput();
    }

    protected String getModelName() {
        return Model.getInput();
    }
}


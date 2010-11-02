/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.wicket;

import java.sql.SQLException;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

public class SignUp extends BasePage {

    /**
     * Login page constituents are the same as Login.html except that
     * it is made up of equivalent Wicket components
     */
    private TextField userIdField;
    private PasswordTextField passField;
    private PasswordTextField confirmField;
    private Form form;

    public SignUp() {
        userIdField = new TextField("userId", new Model(""));
        passField = new PasswordTextField("password", new Model(""));
        confirmField = new PasswordTextField("confirmation", new Model(""));

        passField.setResetPassword(false);
        confirmField.setResetPassword(false);

        form = new LoginForm("loginForm");
        form.add(userIdField);
        form.add(confirmField);
        form.add(passField);
        add(form);
        add(new FeedbackPanel("fpanel"));

    }

    class LoginForm extends Form {

        public LoginForm(String id) {
            super(id);
        }

        @Override
        public void onSubmit() {
            String userId = getUserId();
            String password = getPassword();
            String confirm = getConfirmation();
            try {
                if(!Application.get().IsValidMail(userId)){
                    info("Not a valid mail adress");
                } else {
                if(!confirm.equals(password)){
                    info("Passwords do not match");
                } else {
                if(!Application.get().IsAtDataBase(userId)){
                    info("E-mail is already at database");
                } else {
                Application.get().addMember(userId, password);
                setResponsePage(HomePage.class);
                }
                }
            }}catch (SQLException ex) {
                //Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected String getUserId()
    {
        return userIdField.getInput();
    }

    protected String getPassword()
    {
        return passField.getInput();
    }

    protected String getConfirmation()
    {
        return confirmField.getInput();
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.wicket;
import java.sql.SQLException;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

/**
 *
 * @author cagatay
 */
public class UserPage extends BasePage {
        private PasswordTextField currField;
        private PasswordTextField newField;
        private PasswordTextField confField;        
        private Form form;
        SignInSession session;


    public UserPage() {
        currField = new PasswordTextField("currPass", new Model(""));
        newField = new PasswordTextField("newPass", new Model(""));
        confField = new PasswordTextField("conPass", new Model(""));

        currField.setResetPassword(false);
        newField.setResetPassword(false);
        confField.setResetPassword(false);

        form = new Form1("EditDetails");
        form.add(currField);
        form.add(newField);
        form.add(confField);
        add(form);
        add(new FeedbackPanel("fpanel"));            
    }

    class Form1 extends Form {
        public Form1(String id) {
            super(id);
        }
        @Override
        public void onSubmit() {
            String currPass = getCurr();
            String newPass  = getNew();
            String ConfPass = getConf();
            String mail = session.getUser();
            try {
                if(!Application.get().IsPassTrue(mail, currPass)){
                    info("Current Password is incorrect");
                } else {
                if(!newPass.equals(ConfPass)){
                    info("Passwords do not match");
                } else {
                    Application.get().changePassword(mail, newPass);
                    setResponsePage(HomePage.class);
                }
               }
             } catch (SQLException ex) {
                //Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }

    protected String getCurr()
    {
        return currField.getInput();
    }

    protected String getNew()
    {
        return newField.getInput();
    }

    protected String getConf()
    {
        return confField.getInput();
    }


    public UserPage(PageParameters params) {
        //TODO:  process page parameters
    }
}


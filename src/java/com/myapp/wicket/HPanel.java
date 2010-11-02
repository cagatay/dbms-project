/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.wicket;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 *
 * @author cagatay
 */
public final class HPanel extends Panel {

    private TextField userIdField;
    private PasswordTextField passField;
    private Form form;

    public HPanel(String id) {
        super(id);
        Link l2 = new Link("newuser") {

            @Override
            public void onClick() {
                setResponsePage(new SignUp());
            }
        };
        l2.add(new Label("newuserlabel", "New User"));
        //add(l2);

        userIdField = new TextField("userId", new Model(""));
        passField = new PasswordTextField("password", new Model(""));

        passField.setResetPassword(false);

        form = new LoginForm("loginForm");
        form.add(userIdField);
        form.add(passField);
        form.add(l2);
        add(form);
    }

    class LoginForm extends Form {

        public LoginForm(String id) {
            super(id);
        }

        @Override
        public void onSubmit() {
            String userId = userIdField.getInput();
            String password = passField.getInput();
            SignInSession session = (SignInSession) getSession();

            // Sign the user in
            if (session.authenticate(userId, password)) {
                if (!continueToOriginalDestination()) {
                    setResponsePage(getApplication().getHomePage());
                }
            } else {
                setResponsePage(getApplication().getHomePage());
            }
        }
    }
}

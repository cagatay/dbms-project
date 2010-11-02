/*
 * HomePage.java
 *
 * Created on 18 November 2009, 01:43
 */
package com.myapp.wicket;

import java.sql.SQLException;

public class HomePage extends BasePage {

    SignInSession session = (SignInSession) getSession();

    public HomePage() throws SQLException {
       // add(new StyleSheetReference("stylesheet", BasePage.class, "style.css"));
        if (session.isSignedIn()) {
            add(new UserPanel("header"));
        } else {
            add(new HPanel("header"));
        }

        add(new CategoryPanel("categories", "none"));
        add(new ListProducts("products", "none"));
    }
}

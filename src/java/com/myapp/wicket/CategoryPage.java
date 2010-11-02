/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.wicket;
import java.sql.SQLException;

/**
 *
 * @author cagatay
 */
public final class CategoryPage extends BasePage {
    SignInSession session = (SignInSession) getSession();
    public CategoryPage() {
        super ();
    }

    public CategoryPage(String param) throws SQLException {
        super();

        if (session.isSignedIn()) {
            add(new UserPanel("header"));
        } else {
            add(new HPanel("header"));
        }

        add(new CategoryPanel("categories", param));
        add(new ListProducts("products", param));
    }
}


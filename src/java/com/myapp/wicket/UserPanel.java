/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.wicket;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author cagatay
 */
public final class UserPanel extends Panel {
    public UserPanel(String id) {
        super (id);
        
        Link editlink = new Link("edit") {

            @Override
            public void onClick() {
                setResponsePage(new UserPage());
            }
        };
        editlink.add(new Label("editlabel", "Edit details"));
        add(editlink);

        Link outlink = new Link("out"){

            @Override
            public void onClick() {
                ((SignInSession)getSession()).logOut();
                setResponsePage(HomePage.class);
            }
        };
        outlink.add(new Label("outlabel", "Log out"));
        add(outlink);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.wicket;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 *
 * @author cagatay
 */
public final class ListProducts extends Panel {

    List<Product> list;
    SignInSession session = (SignInSession) getSession();

    public ListProducts(String id, final String param) {
        super(id);
        try {
            list = Application.get().getProducts(param);
        } catch (SQLException ex) {
            //Logger.getLogger(ListProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        final PageableListView products = new PageableListView("products", list, 4) {

            @Override
            protected void populateItem(ListItem item) {
                final Product p = (Product) item.getModelObject();
                Link l = new Link("link", item.getModel()) {

                    @Override
                    public void onClick() {
                        setResponsePage(new ProductPage(p));
                    }
                };
                l.add(new Label("brand", p.getBrand()));
                l.add(new Label("model", p.getModel()));
                l.add(new Image("image", new Model<String>(p.getImage())));
                item.add(l);
            }
        };
        add(products);
        MarkupContainer nav = add(new PagingNavigator("navigator", products));
        //add(nav);
        Link link = new Link("addlink") {
            @Override
            public void onClick() {
                if(session.isSignedIn())
                    try {
                    setResponsePage(new AddProductPage(param));
                } catch (SQLException ex) {
                    //Logger.getLogger(ListProducts.class.getName()).log(Level.SEVERE, null, ex);
                }
                else
                    setResponsePage(SignUp.class);
            }
        };
        link.add(new Label("addlabel", "add new"));
        add(link);
    }

    public ListProducts(String id) {
        super(id);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.wicket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author cagatay
 */
public final class CategoryPanel extends Panel {
    public CategoryPanel(String id, String param) {
        super (id);
        try {
            PageableListView categories = new PageableListView("categories", Application.get().getSubs(param), -1) {

                @Override
                protected void populateItem(ListItem item) {
                    final Category cat = (Category) item.getModelObject();
                    Link l = new Link("link", item.getModel()) {

                        @Override
                        public void onClick() {
                            try {
                                setResponsePage(new CategoryPage(cat.getName()));
                            } catch (SQLException ex) {
                                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    l.add(new Label("name", cat.getName()));
                    item.add(l);
                }
            };
            add(categories);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CategoryPanel(String id){
        super(id);
    }
}

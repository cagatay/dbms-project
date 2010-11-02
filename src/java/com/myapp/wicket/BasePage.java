/*
 * WicketExamplePage.java
 *
 * Created on October 30, 2009, 4:20 PM
 */
 
package com.myapp.wicket;           

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.resources.StyleSheetReference;
import org.apache.wicket.model.IModel;

/** 
 *
 * @author chasan
 * @version 
 */

public class BasePage extends WebPage {

    /**
     * Constructor
     */
    public BasePage() {
        this(null);
    }
    
    /**
     * Construct.
     * @param model
     */
    public BasePage(IModel model) {
        super(model);
        final String packageName = getClass().getPackage().getName();
        add(new StyleSheetReference("stylesheet", HomePage.class, "style.css"));
    }
}

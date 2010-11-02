/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.wicket;

import java.io.Serializable;

/**
 *
 * @author Chasan
 */
public class Comment implements Serializable {
    private String comment;

    public Comment(String com){
        comment = com;
    }
    public String getComment()
    {
        return comment;
    }

}

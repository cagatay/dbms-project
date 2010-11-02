/*
 * Application.java
 *
 * Created on 18 November 2009, 01:43
 */
package com.myapp.wicket;

import java.util.regex.*;
import org.apache.wicket.protocol.http.WebApplication;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;

/** 
 *
 * @author cagatay
 * @version 
 */
public class Application extends WebApplication {

    private Connection dbConn;
    private String dbUserName;
    private String dbPassword;
    private String dbURL;

    public Application() {

        dbConn = null;
        try {
            dbUserName = new String("root");
            dbPassword = new String("");
            dbURL = new String("jdbc:mysql://localhost/blg361");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            dbConn = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
            System.out.println("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            dbConn.close();
            System.out.println("Database connection terminated");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
        } finally {
            super.finalize();
        }
    }

    @Override
    protected void init() {
        getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy() {

            public boolean isActionAuthorized(Component component, Action action) {
                return true;
            }

            public <T extends Component> boolean isInstantiationAuthorized(
                    Class<T> componentClass) {
                if (HomePage.class.isAssignableFrom(componentClass)) {
                    // Is user signed in?
                    if (((SignInSession) Session.get()).isSignedIn()) {
                        // okay to proceed
                        return true;
                    }

                    // Force sign in
                    //throw new RestartResponseAtInterceptPageException(SignIn.class);
                }
                return true;
            }
        });
    }

    public static Application get() {
        return (Application) org.apache.wicket.Application.get();
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    public List<Category> getSubs(String parent) throws SQLException {
        List<Category> list = new ArrayList<Category>();
        list.clear();
        Statement stmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet srs = stmt.executeQuery("SELECT * FROM categories WHERE (parent = '" + parent + "' )");
        while (srs.next()) {
            list.add(new Category(srs.getInt("id"), srs.getString("name"), srs.getString("parent")));
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public String getConfigurationType() {
        return Application.DEPLOYMENT;
    }

    boolean IsValidMail(String mail)
    {

        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(mail);
        boolean matchFound = m.matches();
        if (matchFound)
                return true;
        else
                return false;
     }

     boolean IsAtDataBase(String mail) throws SQLException
     {
          int value = 0;
        try {
            Statement s = dbConn.createStatement();
            ResultSet r = s.executeQuery("SELECT COUNT(*) FROM members WHERE( email = '" + mail +"')");
            while(r.next()){
                value = r.getInt(1);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
                if(value == 1)
                    return false;
                else
                    return true;
      }

    void addMember(String email, String pass) throws SQLException {
        PreparedStatement s;
        s = dbConn.prepareStatement("INSERT INTO members (email, pass) VALUES(?,?)");
        s.setString(1, email);
        s.setString(2, pass);
        s.executeUpdate();
        s.close();
    }

    void addProduct(String brand, String model, String category) throws SQLException {
        PreparedStatement s;
        s = dbConn.prepareStatement("INSERT INTO products(brand, model, category) VALUES(?,?,?)");
        s.setString(1, brand);
        s.setString(2, model);
        s.setString(3, category);
        s.executeUpdate();
        s.close();
    }

    void changePassword(String mail, String newPass) throws SQLException {
        PreparedStatement s;
        s = dbConn.prepareStatement("UPDATE members SET pass = ? WHERE ( email = ?)");
        s.setString(1, newPass);
        s.setString(2, mail);
        s.executeUpdate();
        s.close();
    }

    public boolean IsPassTrue(String email, String pass) throws SQLException {
            boolean returnVal = false;
        try {
            Statement s = dbConn.createStatement();
            ResultSet r = s.executeQuery("SELECT pass FROM members WHERE( email = '" + email +"')");
            while(r.next()){
                returnVal = r.getString(1).equals(pass);
            }
            } catch (SQLException ex) {
            //Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
                return returnVal;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new SignInSession(request);
    }

    public boolean getUser(String email, String pass) {
        try {
            Statement s = dbConn.createStatement();
            ResultSet r = s.executeQuery("SELECT COUNT(*) AS rowcount FROM members WHERE (email = '" + email + "' AND pass = '" + pass + "')");
            System.out.println("SELECT COUNT(*) AS rowcount FROM members WHERE (email = '" + email + "' AND pass = '" + pass + "')");
            r.next();
            int count = r.getInt("rowcount");
            r.close();
            if (count > 0) {
                return true;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Comment> getComments(String userID) throws SQLException {
        List<Comment> list = new ArrayList<Comment>();
        list.clear();
        ResultSet set;
        Statement stmt = dbConn.createStatement();
        String query = "SELECT id FROM members WHERE(email = '" + userID + "')";
        set = stmt.executeQuery(query);
        int idNumber = set.getInt(1);
        String query1 = "SELECT com FROM comments, members WHERE(userid = '" + idNumber + "')";
        set = stmt.executeQuery(query1);
        while(set.next()){
            list.add(new Comment(set.getString("com")));
        }
            return Collections.unmodifiableList(list);
     }
    
    public List<Product> getProducts(String category) throws SQLException {
        List<Product> list = new ArrayList<Product>();
        list.clear();
        ResultSet srs;
        Statement stmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        if(category.equals("none")){
            srs = stmt.executeQuery("SELECT * FROM products ORDER BY RAND() LIMIT 8");
        }
        else srs = stmt.executeQuery("SELECT * FROM products WHERE (category = '" + category + "' )");
        while (srs.next()) {
            list.add(new Product(srs.getInt("id"), srs.getString("brand"), srs.getString("model"),
                    srs.getString("category"), srs.getString("image"), srs.getInt("votes"), srs.getInt("total")));
        }
        return Collections.unmodifiableList(list);
    }
}

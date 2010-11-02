package com.myapp.wicket;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;


public final class SignInSession extends WebSession
{
    private String user;

    /**
     * Constructor
     *
     * @param request
     */
    protected SignInSession(Request request)
    {
        super(request);
    }

    public final boolean authenticate(final String username, final String password)
    {
        if (user == null)
        {
            // Trivial password "db"
            if (Application.get().getUser(username, password))
            {
                user = username;
            }
        }

        return user != null;
    }

    /**
     * @return True if user is signed in
     */
    public boolean isSignedIn()
    {
        return user != null;
    }

    /**
     * @return User
     */
    public String getUser()
    {
        return user;
    }

    /**
     * @param user
     *            New user
     */
    public void setUser(final String user)
    {
        this.user = user;
    }

    public void logOut(){
        user = null;
    }
}
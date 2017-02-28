
package model;

public class User 
{
    private int id;
    private String email;
    private String password;
    private String name;

    public User(int id, String email, String password, String name)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }
    
    public int getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }
}

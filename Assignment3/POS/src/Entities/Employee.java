package Entities;

/**
 * Created by nashm on 20/05/2017.
 */
public class Employee {
    private String id;
    private String name;
    private String SSN;
    private String username;
    private String password;

    public Employee(String id, String name, String SSN, String username, String password) {
        this.id = id;
        this.name = name;
        this.SSN = SSN;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

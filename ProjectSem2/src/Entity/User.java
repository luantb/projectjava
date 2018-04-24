/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Hoang's PC
 */
public class User {

    private int userId;
    private String username, password;
    private int userRole;

    public User(int userId, String username, String password, int userRole) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entity.User;
import java.util.ArrayList;

/**
 *
 * @author Hoang's PC
 */
public interface UserInterface {

    public void insert(User u);

    public void edit(User u);

    public void delete(int userID);

    public ArrayList<User> getAllUser();

    public User getUserById(int Id);

    public boolean login(User u);

    public User getUser(User u);
}

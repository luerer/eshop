package com.luerer.dao;

/**
 * Created by luerer on 28/06/2017.
 */
import com.luerer.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IUserDao {

    public User searchByName(String name);
    public List<User> listall();
    public void addUser(User user);
    public void deleteUser(String username);

}

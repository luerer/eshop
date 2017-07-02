package com.luerer.dao;

/**
 * Created by luerer on 28/06/2017.
 */
import com.luerer.model.User;
import org.springframework.stereotype.Component;

@Component
public interface IUserDao {

    public User search(String name);

}

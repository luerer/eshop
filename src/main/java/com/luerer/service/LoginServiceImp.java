package com.luerer.service;

import com.luerer.dao.IUserDao;
import com.luerer.model.User;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

/**
 * Created by luerer on 30/06/2017.
 */
@Service("LoginService")
public class LoginServiceImp implements LoginService {
    @Autowired
    IUserDao mapper;

    @Override
    public boolean login(String username, String password){
        User user = mapper.searchByName(username);
        if(user!= null){
            if(user.getUsername().equals(username)&&user.getPassword().equals(password))
                return true;
        }
        return false;
    }
}

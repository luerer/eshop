package com.luerer.service;

import com.luerer.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by luerer on 09/07/2017.
 */
@Service("AdminService")
public class AdminServiceImp implements AdminService {
    public boolean checkAdmin(User user) throws Exception{
        int id = user.getId();
        if (id!=0){
            throw new Exception("您没有权限。");
        }
        return true;
    }
}

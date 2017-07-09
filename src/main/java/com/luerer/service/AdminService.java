package com.luerer.service;

import com.luerer.dao.IUserDao;
import com.luerer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luerer on 03/07/2017.
 */
@Service("AdminService")
public interface AdminService {
    public boolean checkAdmin(User user) throws Exception;

}

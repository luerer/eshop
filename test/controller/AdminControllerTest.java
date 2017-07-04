package controller;

import com.luerer.dao.IUserDao;
import com.luerer.model.User;
import com.sun.org.apache.bcel.internal.generic.IUSHR;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.mockito.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by luerer on 03/07/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class AdminControllerTest {

    @Autowired
    IUserDao iUserDao;
    @Test
    public void adminDeleteUser(){
        iUserDao.deleteUser("二傻");
    }
}
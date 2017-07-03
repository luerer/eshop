package service;

import com.luerer.dao.IUserDao;
import com.luerer.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by luerer on 30/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})

public class LoginServiceTest {

    @Autowired
    IUserDao mapper;
    @Test
    public void login(){
        User user = mapper.searchByName("陆二二");
        System.out.println(user.toString());
        if(user!= null){
            if(user.getUsername().equals("陆二二")&&user.getPassword().equals("a"))
                System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }

}
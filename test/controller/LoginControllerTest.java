package controller;

import com.luerer.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

/**
 * Created by luerer on 30/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class LoginControllerTest {
    @Autowired
    LoginService loginservice;
    @Test
    public void logincheck(){
        boolean code = loginservice.login("陆二二","admin");
        if(code){
            System.out.println("成功！");
        }else{
            System.out.println("用户名或密码错误！");
        }
    }
}
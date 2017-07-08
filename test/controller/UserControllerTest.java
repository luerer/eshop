package controller;

import com.luerer.dao.IUserDao;
import com.luerer.dao.IcartDao;
import com.luerer.model.Cart;
import com.sun.org.apache.bcel.internal.generic.IUSHR;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by luerer on 05/07/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class UserControllerTest {
    @Autowired
    IUserDao iUserDao;
    @Autowired
    IcartDao icartDao;

    @Test
    public void changePassword(){
        String username = "1";
        String password = "12345";
        iUserDao.changePassword(username,password);

    }

    @Test
    public void testCart(){
        String username = "622";
        List<Cart> cartList=icartDao.searchByCustom(username);
        for(Cart cart:cartList)
            System.out.println(cart.toString());
    }
}
package controller;

import com.luerer.controller.IndexController;
import com.luerer.dao.IUserDao;
import com.luerer.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by luerer on 28/06/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})

public class IndexControllerTest {


    @Test
    public void helloworld(){
        System.out.println("hello world!");
    }

    @Autowired
    private IUserDao iUserDao;
    @Test
    public void controllerTest(){
        User user = iUserDao.search("柳二二");
        System.out.println(user.toString());
    }

    @Test
    public void testRegisterPage() throws Exception{
        IndexController controller = new IndexController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/home/register/")).andExpect(view().name("register"));
    }



}
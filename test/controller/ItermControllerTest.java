package controller;

import com.luerer.dao.ItermDao;
import com.luerer.model.Iterm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by luerer on 29/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})

public class ItermControllerTest {

    @Autowired
    private ItermDao itermdao;
    @Test
    public void Iterms(){
        List<Iterm> itermList=itermdao.searchByName("衣服");
        for(Iterm iterm:itermList)
            System.out.println(iterm.toString());
    }

    @Autowired
    private  ItermDao itermDao;
    @Test
    public void oneiterm()
    {
        Iterm iterm = itermdao.searchone(2);
        System.out.println(iterm.toString());
    }

    @Test
    public void listalliterms()
    {
        List<Iterm> itermList = itermdao.listall();
        for(Iterm iterm:itermList)
            System.out.println(iterm.toString());
    }


}
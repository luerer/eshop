package controller;

import com.luerer.dao.IitemDao;
import com.luerer.model.Item;
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
    private IitemDao iitemDao;
    @Test
    public void Iterms(){
        List<Item> itermList=iitemDao.searchByName("衣服");
        for(Item item:itermList)
            System.out.println(item.toString());
    }

    @Test
    public void oneiterm()
    {
        Item iterm = iitemDao.searchById(2);
        System.out.println(iterm.toString());
    }

    @Test
    public void listalliterms()
    {
        List<Item> itemList = iitemDao.listall();
        for(Item item:itemList)
            System.out.println(item.toString());
    }


}
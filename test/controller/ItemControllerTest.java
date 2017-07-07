package controller;

import com.luerer.dao.IitemDao;
import com.luerer.dao.IorderDao;
import com.luerer.dao.ItypeDao;
import com.luerer.model.Item;
import com.luerer.model.Type;
import com.luerer.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by luerer on 06/07/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class ItemControllerTest {

    @Autowired
    IitemDao iitemDao;
    @Autowired
    ItypeDao itypeDao;
    @Autowired
    IorderDao iorderDao;

    @Test
    public void showItemByType(){
        List<Item> itemList = null;
        String type_name = itypeDao.searchById(1);
        String item_type = type_name;
        itemList = iitemDao.searchByType(item_type);
        for(Item item:itemList)
            System.out.println(item.toString());

    }

    @Test
    public void testGetType(){
        Type type = null;
        type = itypeDao.searchByName("下装");
        System.out.println(type.toString());
    }

    @Test
    public void buyItem(){
        String custom = "622";
        int item_id=2;
        int item_num=1;

        Item item = null;
        Type type = null;

        item = iitemDao.searchById(item_id);

        int stock = item.getItem_stock();
        if(stock<item_num){
            System.out.println("对不起，商品库存不足。");
        }

        item.setItem_stock(stock-item_num);
        type = itypeDao.searchByName(item.getItem_type());
        int sum = type.getType_sum();
        type.setType_sum(sum-item_num);
        iitemDao.updateItem(item);
        itypeDao.updateType(type);


    }

}
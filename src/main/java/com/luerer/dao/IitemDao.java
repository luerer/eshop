package com.luerer.dao;

import com.luerer.model.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by luerer on 29/06/2017.
 */
@Component
public interface IitemDao {
    public List<Item> searchByName(String item_name);
    public List<Item> listall();
    public Item searchById(int item_id);
    public List<Item> searchByType(@Param("item_type") String item_type);
    public void updateItem(Item item);
    public void deleteItem(@Param("item_id") int item_id);
}

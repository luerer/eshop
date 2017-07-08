package com.luerer.dao;

import com.luerer.model.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by luerer on 08/07/2017.
 */
@Component
public interface IcartDao {
    public List<Cart> searchByCustom(@Param("custom_name") String custom_name);
    public void confirmCart(@Param("custom_name") String custom_name);
    public void deleteCart(@Param("custom_name") String custom_name,
                           @Param("item_id") int item_id);
    public void addCart(Cart cart);

}

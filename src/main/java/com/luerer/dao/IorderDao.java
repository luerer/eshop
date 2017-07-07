package com.luerer.dao;

import com.luerer.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by luerer on 06/07/2017.
 */
@Component
public interface IorderDao {
    public List<Order> listAll();
    public Order searchById(@Param("id") long id);
    public List<Order> searchByCustom(@Param("custom") String custom);
    public void send(@Param("id") long id);
    public void receive(@Param("id") long id);
    public void addOrder(Order order);
    public void setStatus(@Param("id") long id,
                          @Param("status") int status,
                          @Param("msg") String msg);
}

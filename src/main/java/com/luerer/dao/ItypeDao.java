package com.luerer.dao;

import com.luerer.model.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by luerer on 05/07/2017.
 */
@Component
public interface ItypeDao {
    public List<Type> listAll();
    public Type searchByName(@Param("type_name") String type_name);
    public String searchById(@Param("type_id") int type_id);
    public void updateType(Type type);
    public void addType(@Param("type_name") String type_name);
}

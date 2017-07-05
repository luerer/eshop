package com.luerer.dao;

import com.luerer.model.Type;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by luerer on 05/07/2017.
 */
@Component
public interface ItypeDao {
    public List<Type> listAll();
    public int sumOfType(String type_name);
}

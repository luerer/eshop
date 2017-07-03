package com.luerer.dao;

import com.luerer.model.Iterm;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by luerer on 29/06/2017.
 */
@Component
public interface ItermDao {
    public List<Iterm> searchByName(String iterm_name);
    public List<Iterm> listall();
    public Iterm searchById(int iterm_id);
}

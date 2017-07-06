package com.luerer.model;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

/**
 * Created by luerer on 05/07/2017.
 */
@Component
public class Type {
    @NotNull
    private String type_name;

    private int type_sum;

    private int type_id;

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getType_sum() {
        return type_sum;
    }

    public void setType_sum(int type_sum) {
        this.type_sum = type_sum;
    }

    @Override
    public String toString() {
        return "Type{" +
                "type_name='" + type_name + '\'' +
                ", type_sum=" + type_sum +
                '}';
    }
}

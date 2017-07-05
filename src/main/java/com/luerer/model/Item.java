package com.luerer.model;

/**
 * Created by luerer on 29/06/2017.
 */
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class Item {
    @NotNull
    private int item_id;
    @NotNull
    private String item_name;
    @NotNull
    private float item_price;
    @NotNull
    private int item_stock;

    private String item_info;
    @NotNull
    private String item_type;

    private String item_pic;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public float getItem_price() {
        return item_price;
    }

    public void setItem_price(float item_price) {
        this.item_price = item_price;
    }

    public int getItem_stock() {
        return item_stock;
    }

    public void setItem_stock(int item_stock) {
        this.item_stock = item_stock;
    }

    public String getItem_info() {
        return item_info;
    }

    public void setItem_info(String item_info) {
        this.item_info = item_info;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getItem_pic() {
        return item_pic;
    }

    public void setItem_pic(String item_pic) {
        this.item_pic = item_pic;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", item_name='" + item_name + '\'' +
                ", item_price=" + item_price +
                ", item_stock=" + item_stock +
                ", item_info='" + item_info + '\'' +
                ", item_type='" + item_type + '\'' +
                ", item_pic='" + item_pic + '\'' +
                '}';
    }
}

package com.luerer.model;

/**
 * Created by luerer on 29/06/2017.
 */
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

import java.util.*;
public class Iterm {
    @NotNull
    private int iterm_id;
    @NotNull
    private String iterm_name;
    @NotNull
    private float iterm_price;
    @NotNull
    private int iterm_stock;
    private String iterm_info;
    @NotNull
    private String iterm_type;


    public String getIterm_info() {
        return iterm_info;
    }

    public void setIterm_info(String iterm_info) {
        this.iterm_info = iterm_info;
    }

    public String getIterm_type() {
        return iterm_type;
    }

    public void setIterm_type(String iterm_type) {
        this.iterm_type = iterm_type;
    }

    public int getIterm_id() {
        return iterm_id;
    }

    public void setIterm_id(int iterm_id) {
        this.iterm_id = iterm_id;
    }

    public String getIterm_name() {
        return iterm_name;
    }

    public void setIterm_name(String iterm_name) {
        this.iterm_name = iterm_name;
    }

    public float getIterm_price() {
        return iterm_price;
    }

    public void setIterm_price(float iterm_price) {
        this.iterm_price = iterm_price;
    }

    public int getIterm_stock() {
        return iterm_stock;
    }

    public void setIterm_stock(int iterm_stock) {
        this.iterm_stock = iterm_stock;
    }

    @Override
    public String toString() {
        return "Iterm{" +
                "iterm_id=" + iterm_id +
                ", iterm_name='" + iterm_name + '\'' +
                ", iterm_price=" + iterm_price +
                ", iterm_stock=" + iterm_stock +
                '}';
    }
}

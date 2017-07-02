package com.luerer.model;

/**
 * Created by luerer on 29/06/2017.
 */
import java.util.*;
public class Iterm {

    private int iterm_id;
    private String iterm_name;
    private float iterm_price;
    private int iterm_stock;

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

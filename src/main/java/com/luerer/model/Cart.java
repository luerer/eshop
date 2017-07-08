package com.luerer.model;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

/**
 * Created by luerer on 08/07/2017.
 */
@Component
public class Cart {
    @NotNull
    private int id;
    @NotNull
    private String custom_name;
    @NotNull
    private int item_id;
    @NotNull
    private String item_name;
    @NotNull
    private float item_price;
    @NotNull
    private int item_num;
    @NotNull
    private boolean state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustom_name() {
        return custom_name;
    }

    public void setCustom_name(String custom_name) {
        this.custom_name = custom_name;
    }

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

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", custom_name='" + custom_name + '\'' +
                ", item_id=" + item_id +
                ", item_name='" + item_name + '\'' +
                ", item_price=" + item_price +
                ", item_num=" + item_num +
                ", state=" + state +
                '}';
    }

    public Cart() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

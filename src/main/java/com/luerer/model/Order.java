package com.luerer.model;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

/**
 * Created by luerer on 06/07/2017.
 */
@Component
public class Order {
    @NotNull
    private long id;
    @NotNull
    private String custom;
    @NotNull
    private int item_id;
    @NotNull
    private int item_num;
    @NotNull
    private boolean send;
    @NotNull
    private boolean receive;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public boolean isReceive() {
        return receive;
    }

    public void setReceive(boolean receive) {
        this.receive = receive;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", custom='" + custom + '\'' +
                ", item_id=" + item_id +
                ", item_num=" + item_num +
                ", send=" + send +
                ", receive=" + receive +
                '}';
    }
}

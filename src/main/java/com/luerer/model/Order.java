package com.luerer.model;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by luerer on 06/07/2017.
 */
@Component
public class Order {
    @NotNull
    private long id;
    @NotNull
    private String custom_name;
    @NotNull
    private String custom_address;
    @NotNull
    private long custom_phone;
    @NotNull
    private String item_name;
    @NotNull
    private String item_type;
    @NotNull
    private float item_price;
    @NotNull
    private int item_num;
    @NotNull
    private Timestamp time;
    @NotNull
    private int status;
    @NotNull
    private boolean send;
    @NotNull
    private boolean receive;
    @NotNull
    private String msg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustom_name() {
        return custom_name;
    }

    public void setCustom_name(String custom_name) {
        this.custom_name = custom_name;
    }

    public String getCustom_address() {
        return custom_address;
    }

    public void setCustom_address(String custom_address) {
        this.custom_address = custom_address;
    }

    public long getCustom_phone() {
        return custom_phone;
    }

    public void setCustom_phone(long custom_phone) {
        this.custom_phone = custom_phone;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", custom_name='" + custom_name + '\'' +
                ", custom_address='" + custom_address + '\'' +
                ", custom_phone=" + custom_phone +
                ", item_name='" + item_name + '\'' +
                ", item_type='" + item_type + '\'' +
                ", item_price=" + item_price +
                ", item_num=" + item_num +
                ", timestamp=" + time +
                ", status=" + status +
                ", send=" + send +
                ", receive=" + receive +
                ", msg='" + msg + '\'' +
                '}';
    }


    public Order() {
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

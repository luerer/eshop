package com.luerer.model;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

import static javax.swing.text.StyleConstants.Size;

/**
 * Created by luerer on 28/06/2017.
 */
@Component
public class User {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private int id;
    private String gender;

    private String address;

    private long phone;

    private boolean is_default;

    public boolean isIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", gender='" + gender + '\'' +
                '}';
    }
}
